package com.khalej.hoguzat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.khalej.hoguzat.Activity.CardView;
import com.khalej.hoguzat.Activity.Show;
import com.khalej.hoguzat.R;
import com.khalej.hoguzat.model.contact_category_realm;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    Typeface myTypeface;
    private Context context;
    List<contact_category_realm> contactslist;


    public RecyclerAdapter(Context context, List<contact_category_realm> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewlist,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        myTypeface = Typeface.createFromAsset(context.getAssets(), "Droid.ttf");
        holder.Name.setText(contactslist.get(position).getName());
        holder.Name.setTypeface(myTypeface);
        Glide.with(context).load(contactslist.get(position).getImage()).error(R.drawable.logo).into(holder.image);
        holder.city.setText(contactslist.get(position).getCity());
        holder.city.setTypeface(myTypeface);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=contactslist.get(position).getId();
                String name=contactslist.get(position).getName();
                String image=contactslist.get(position).getImage();
               Intent intent=new Intent(context, Show.class);
                intent.putExtra("name",name);
                intent.putExtra("offers",contactslist.get(position).getOffer());
                intent.putExtra("details",contactslist.get(position).getDetail());
                intent.putExtra("image",image);
                intent.putExtra("lat",contactslist.get(position).getLat());
                intent.putExtra("lng",contactslist.get(position).getLng());
                intent.putExtra("id",id);

                context.startActivity(intent);

            }
        });

    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name,city;
        TextView Price;
        ImageView image;
        RelativeLayout relativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            Name=(TextView)itemView.findViewById(R.id.name);
            city=itemView.findViewById(R.id.city);
            image=(ImageView)itemView.findViewById(R.id.photo);
            relativeLayout=itemView.findViewById(R.id.relativelayout);
        }
    }
}
