package com.kg.intern_assignment.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.kg.intern_assignment.R;
import com.kg.intern_assignment.Room.CountryEntity;
import com.kg.intern_assignment.model.Country;
import com.squareup.picasso.Picasso;


import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<CountryEntity> countries=new ArrayList<>();


    public Adapter(Context context,List<CountryEntity> countries) {
        this.context = context;
        this.countries=countries;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final CountryEntity countryEntity = countries.get(position);

        String image=countryEntity.getFlag();
      byte[] data= android.util.Base64.decode(image, android.util.Base64.DEFAULT);
       Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);


        holder.imageView.setImageBitmap(bitmap);
        holder.name.setText(countryEntity.getName());
        String capital="Capital:"+countryEntity.getCapital();
        holder.capital.setText(capital);
        String population="Population:"+ countryEntity.getPopulation().toString();
        holder.population.setText(population);
        String region="Region:"+countryEntity.getRegion();
        holder.region.setText(region);
        String subregion="SubRegion:"+countryEntity.getSubRegion();
        holder.subregion.setText(subregion);
        StringBuilder sb=new StringBuilder();
        sb.append("Languages:");
        for(int i=0;i<countryEntity.getLanguages().size();i++)
        {
            sb.append(countryEntity.getLanguages().get(i).getName());
            sb.append(",");
        }

        holder.languages.setText(sb.toString());

        StringBuilder sbb=new StringBuilder();
        sbb.append("Borders:");
        for(int i=0;i<countryEntity.getBorders().size();i++)
        {
            sbb.append(countryEntity.getBorders().get(i));
            sbb.append(",");
        }

        holder.borders.setText(sbb.toString());


    }

    @Override
    public int getItemCount() {
        if(countries==null)return  0;

        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView imageView;
        TextView capital,population,region,subregion,languages,borders;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);

            imageView = itemView.findViewById(R.id.image);
            cardView = itemView.findViewById(R.id.cardView);
            capital=itemView.findViewById(R.id.Capital);
            population=itemView.findViewById(R.id.population);
            region=itemView.findViewById(R.id.region);
            subregion=itemView.findViewById(R.id.subregion);
            languages=itemView.findViewById(R.id.language);
            borders=itemView.findViewById(R.id.borders);


        }
    }



}
