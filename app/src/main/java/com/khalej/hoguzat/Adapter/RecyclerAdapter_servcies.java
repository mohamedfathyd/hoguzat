package com.khalej.hoguzat.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.khalej.hoguzat.R;
import com.khalej.hoguzat.model.content_servcies;
import com.khalej.hoguzat.model.content_stuff;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter_servcies extends RecyclerView.Adapter<RecyclerAdapter_servcies.MyViewHolder> {
    Typeface myTypeface;
    private Context context;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    List<content_servcies> contactslist;
    double Total=0.0;
    public RecyclerAdapter_servcies(Context context, List<content_servcies> contactslist){
        this.contactslist=contactslist;
        this.context=context;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_radio_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        myTypeface = Typeface.createFromAsset(context.getAssets(), "Droid.ttf");
        holder.Name.setText(contactslist.get(position).getName());
        holder.Name.setTypeface(myTypeface);

        holder.Price.setText(contactslist.get(position).getPrice());
        holder.Price.setTypeface(myTypeface);
        sharedpref = context.getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        holder.radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Total+=Double.parseDouble(contactslist.get(position).getPrice());

                    edt.putFloat("TotalPrice", (float) Total);
                    edt.apply();
                } else {
                    Total-=Double.parseDouble(contactslist.get(position).getPrice());
                    edt.putFloat("TotalPrice", (float) Total);
                    edt.apply();
                    //  onItemClick.onItemUncheck(currentItem);
                }

            }
        });

    }
    @Override
    public int getItemCount() {
        return contactslist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Price;
        AppCompatCheckBox radio;
        RelativeLayout relativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            Name=(TextView)itemView.findViewById(R.id.name);
            Price=(TextView)itemView.findViewById(R.id.price);
            radio =itemView.findViewById(R.id.radio);
        }
    }
}
