package com.khalej.hoguzat.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.google.android.material.navigation.NavigationView;
import com.khalej.hoguzat.Adapter.MyAdapter;
import com.khalej.hoguzat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    String lang;
    RelativeLayout relativelayout1,relativelayout2,relativelayout3,relativelayout4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpref = getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        lang=sharedpref.getString("language","").trim();
        if(lang.equals(null)){
            edt.putString("language","ar");
            lang="en";
            edt.apply();
        }
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle(getString(R.string.app_name));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,R.string.name, R.string.name);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        relativelayout1=findViewById(R.id.relativelayout1);
        relativelayout2=findViewById(R.id.relativelayout2);
        relativelayout3=findViewById(R.id.relativelayout3);
        relativelayout4=findViewById(R.id.relativelayout4);
        relativelayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Salon.class);
                intent.putExtra("name",getString(R.string.salon));
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        relativelayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CardView.class);
                intent.putExtra("id",2);
                intent.putExtra("country",sharedpref.getInt("country",1));
                intent.putExtra("category",1);
                startActivity(intent);

            }
        });
        relativelayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CardView.class);
                intent.putExtra("id",3);
                intent.putExtra("country",sharedpref.getInt("country",1));
                intent.putExtra("category",1);
                startActivity(intent);
            }
        });
        relativelayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CardView.class);
                intent.putExtra("id",4);
                intent.putExtra("country",sharedpref.getInt("country",1));
                intent.putExtra("category",1);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.main) {
           startActivity(new Intent(MainActivity.this,MainActivity.class));
        } else if (id == R.id.profile) {
            startActivity(new Intent(MainActivity.this,Profile.class));
        } else if (id == R.id.aboutus) {
            startActivity(new Intent(MainActivity.this,AboutUs.class));
        }else if(id==R.id.nav_item1){
            if(sharedpref.getString("language","").trim().equals("ar")){
                edt.putString("language","en");
                edt.apply();
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                finish();
            }
            else
            {
                edt.putString("language","ar");
                edt.apply();
                startActivity(new Intent(MainActivity.this,MainActivity.class));
                finish();
            }
        }
        else if (id == R.id.callus) {
            try{
                String url = "https://api.whatsapp.com/send?phone="+"+97333348098";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);}
            catch( Exception e){
                Toast.makeText(MainActivity.this, "غير متاحه الأن عاود المحاولة لاحقا " ,Toast.LENGTH_LONG).show();
            }
           // startActivity(new Intent(MainActivity.this,AboutUs.class));
        } else if (id == R.id.logout) {
            edt.putInt("id",0);
            edt.putFloat("totalprice",0);
            edt.putString("name","");
            edt.putString("phone","");
            edt.putString("address","");
            edt.putString("password","");
            edt.putString("remember","no");
            edt.apply();
            startActivity(new Intent(MainActivity.this,Login.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
