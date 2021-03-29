package com.kg.intern_assignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Visibility;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kg.intern_assignment.Room.CountryEntity;
import com.kg.intern_assignment.Room.DatabaseCountry;
import com.kg.intern_assignment.Room.LanguageEntity;
import com.kg.intern_assignment.Room.RoomAdapter;
import com.kg.intern_assignment.Room.RoomViewModel;
import com.kg.intern_assignment.data.Adapter;
import com.kg.intern_assignment.data.ViewModel;
import com.kg.intern_assignment.model.Country;
import com.kg.intern_assignment.model.Languages;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private static final int NUMBER_OF_THREADS = 2;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private Adapter adapter;
    LinearLayoutManager manager;
    private ViewModel viewModel;
    private RoomViewModel roomViewModel;

    String encodedImage;
    Button button_retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        button_retry=findViewById(R.id.button_retry);


        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.hasFixedSize();
        viewModel=new ViewModelProvider(this).get(ViewModel.class);
        roomViewModel=new ViewModelProvider(this).get(RoomViewModel.class);

        fillData();

        button_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNetworkAvailable())
                {
                    fill();
                    fillData();

                }
                else
                {
                    Toast.makeText(MainActivity.this,"No internet",Toast.LENGTH_LONG).show();

                }
            }
        });











    }

    private void fillData()
    {

        roomViewModel.getData().observe(MainActivity.this, new Observer<List<CountryEntity>>() {
            @Override
            public void onChanged(List<CountryEntity> countryEntities) {



                if(countryEntities==null || countryEntities.size()<=0)
                {
                    button_retry.setVisibility(View.VISIBLE);
                }
                else {

                    button_retry.setVisibility(View.INVISIBLE);
                    adapter = new Adapter(MainActivity.this, countryEntities);
                    recyclerView.setAdapter(adapter);

                }

            }
        });


    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void fill()
    {

        viewModel.getData().observe(MainActivity.this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                Log.d("kitty", "onChanged: "+countries.size());

                for(int i=0;i<countries.size();i++)
                {


                    Country country = countries.get(i);
                    List<Languages> languages=country.getLanguages();
                    List<LanguageEntity> languageEntities = new ArrayList<>();
                    for (int j = 0; j < languages.size(); j++) {
                        Languages language = languages.get(j);
                        LanguageEntity entity = new LanguageEntity(language.getIso639_1(), language.getIso639_2(), language.getName(), language.getNativeName());
                        languageEntities.add(entity);

                    }


                    String byteArray= null;
                    try {
                        byteArray = getByte(country.getAlpha2Code());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    CountryEntity countryEntity = new CountryEntity(country.getName(), country.getCapital(), country.getAlpha2Code(), country.getRegion(), byteArray, country.getSubregion(), country.getPopulation(), country.getBorders(), languageEntities);
                    roomViewModel.insert(countryEntity);



                }
            }
        });



    }



    private String getByte(String uri) throws InterruptedException, ExecutionException
    {


        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() {
                String BASE_URL_FLAG = "https://raw.githubusercontent.com/emcrisostomo/flags/master/png/256/";
                Bitmap bitmap= null;
                try {
                    bitmap = Picasso.with(MainActivity.this).load(BASE_URL_FLAG+uri+".png").get();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//                result[0] = stream.toByteArray();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);




                return encodedImage;
            }
        };
        Future<String> future = databaseWriteExecutor.submit(callable);
         return future.get();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                roomViewModel.delete();
                Adapter adapt=new Adapter(MainActivity.this,null);
                recyclerView.setAdapter(adapt);
                button_retry.setVisibility(View.VISIBLE);



            default:
                return super.onOptionsItemSelected(item);
        }

    }


}