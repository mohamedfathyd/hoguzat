package com.khalej.hoguzat.Adapter;

import android.content.Context;
import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.khalej.hoguzat.R;
import com.khalej.hoguzat.model.content_stuff;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter_stuff extends RecyclerView.Adapter<RecyclerAdapter_stuff.MyViewHolder> {
    Typeface myTypeface;
    private Context context;
    List<content_stuff> contactslist;


    public RecyclerAdapter_stuff(Context context, List<content_stuff> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_circle_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        myTypeface = Typeface.createFromAsset(context.getAssets(), "Droid.ttf");
        holder.Name.setText(contactslist.get(position).getName());
        holder.Name.setTypeface(myTypeface);
        Glide.with(context).load(contactslist.get(position).getImage()).error(R.drawable.profile).into(holder.image);



    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Price;
        CircleImageView image;
        RelativeLayout relativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            Name=(TextView)itemView.findViewById(R.id.name);
            image=(CircleImageView)itemView.findViewById(R.id.photo);
            relativeLayout=itemView.findViewById(R.id.relativelayout);
        }
    }
}
