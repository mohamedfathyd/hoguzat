package com.khalej.hoguzat.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.khalej.hoguzat.Adapter.MyAdapter;
import com.khalej.hoguzat.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import me.anwarshahriar.calligrapher.Calligrapher;

public class Salon extends AppCompatActivity {
    RelativeLayout relativelayout2,relativelayout1;
    AppCompatTextView selectCountry;
    Intent intent;
    int id;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    int country=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salon);
        relativelayout1=findViewById(R.id.relativelayout);
        relativelayout2=findViewById(R.id.relativelayout2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        intent=getIntent();
        sharedpref = getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();

        TextView toolbar_title=findViewById(R.id.toolbar_title);
        toolbar_title.setText(intent.getStringExtra("name"));
        id=intent.getIntExtra("id",0);
        setSupportActionBar(toolbar);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);
        this.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );


        relativelayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Salon.this,CardView.class);
                intent.putExtra("id",id);
                intent.putExtra("country",sharedpref.getInt("country",1));
                intent.putExtra("category",1);
                startActivity(intent);
            }
        });
        relativelayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Salon.this,CardView.class);
                intent.putExtra("id",id);
                intent.putExtra("country",sharedpref.getInt("country",1));
                intent.putExtra("category",2);
                startActivity(intent);
            }
        });
    }
}
