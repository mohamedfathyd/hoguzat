package com.khalej.hoguzat.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.khalej.hoguzat.R;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import me.anwarshahriar.calligrapher.Calligrapher;

public class Show extends AppCompatActivity {

    TabLayout MyTabs;
    ViewPager MyPage;
    Intent i;
    Button cuntinue;
    ImageView imageView;
    TextView title;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        imageView=findViewById(R.id.a);
        cuntinue=findViewById(R.id.cunti);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);
        sharedpref = getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        i=getIntent();
        Glide.with(this).load(i.getStringExtra("image")).error(R.drawable.logo).into(imageView);
        edt.putString("details",i.getStringExtra("details"));
        edt.putString("offers",i.getStringExtra("offers"));
        edt.putFloat("lat", (float) i.getDoubleExtra("lat",0));
        edt.putFloat("lng", (float) i.getDoubleExtra("lng",0));
        edt.putString("marketName",i.getStringExtra("name"));
        edt.putInt("categoryId",i.getIntExtra("id",0));
        edt.apply();

        title=findViewById(R.id.toolbar_title);
        title.setText(i.getStringExtra("name"));
        MyTabs = (TabLayout)findViewById(R.id.MyTabs);
        MyPage = (ViewPager)findViewById(R.id.MyPage);
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
        cuntinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Show.this,SelectDate.class);
                intent.putExtra("title",i.getStringExtra("name"));
                startActivity(intent);
            }
        });
        MyTabs.setupWithViewPager(MyPage);
        SetUpViewPager(MyPage);
    }
    public void SetUpViewPager (ViewPager viewpage){
        MyViewPageAdapter Adapter = new MyViewPageAdapter(getSupportFragmentManager());

        Adapter.AddFragmentPage(new staff(),getString(R.string.staff));
        Adapter.AddFragmentPage(new services(), getString(R.string.servcies));
        Adapter.AddFragmentPage(new Offers(), getString(R.string.Offers));
        Adapter.AddFragmentPage(new info(), getString(R.string.info));
        //We Need Fragment class now

        viewpage.setAdapter(Adapter);

    }

    public class MyViewPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> MyFragment = new ArrayList<>();
        private List<String> MyPageTittle = new ArrayList<>();

        public MyViewPageAdapter(FragmentManager manager){
            super(manager);
        }

        public void AddFragmentPage(Fragment Frag, String Title){
            MyFragment.add(Frag);
            MyPageTittle.add(Title);
        }

        @Override
        public Fragment getItem(int position) {
            return MyFragment.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return MyPageTittle.get(position);
        }

        @Override
        public int getCount() {
            return 4;
        }
    }


}
