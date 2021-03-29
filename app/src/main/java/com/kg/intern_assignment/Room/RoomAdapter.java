package com.kg.intern_assignment.Room;

import android.content.Context;
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
import com.kg.intern_assignment.data.Adapter;
import com.kg.intern_assignment.model.Country;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder>{

        Context context;
        List<CountryEntity> countries=new ArrayList<>();


public RoomAdapter(Context context,List<CountryEntity> countries){
        this.context=context;
        this.countries=countries;


        }

@NonNull
@Override
public  ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);


        }

@Override
public void onBindViewHolder(@NonNull final ViewHolder holder,int position){

final CountryEntity a=countries.get(position);


        String BASE_URL_FLAG="https://raw.githubusercontent.com/emcrisostomo/flags/master/png/256/";


        Picasso.with(context).load(BASE_URL_FLAG+a.getAlpha2code()+".png").into(holder.imageView);
       // Log.d("kitty","onBindViewHolder: "+a.getFlag());
        holder.name.setText(a.getName());


        }

@Override
public int getItemCount(){
        if(countries==null)return 0;

        return countries.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView name;
    ImageView imageView;
    CardView cardView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name);

        imageView = itemView.findViewById(R.id.image);
        cardView = itemView.findViewById(R.id.cardView);


    }
}


}


