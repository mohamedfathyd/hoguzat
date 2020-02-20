package com.khalej.hoguzat.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.khalej.hoguzat.Adapter.RecyclerAdapter;
import com.khalej.hoguzat.R;
import com.khalej.hoguzat.model.Apiclient_home;
import com.khalej.hoguzat.model.apiinterface_home;
import com.khalej.hoguzat.model.contact_category;
import com.khalej.hoguzat.model.contact_category_realm;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.realm.Realm;
import io.realm.RealmResults;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter recyclerAdapter;
    private List<contact_category> contactList;
    private apiinterface_home apiinterface;
    ProgressBar progressBar;
    ImageView car;
    Realm realm;
    TextView textView;
    private SharedPreferences sharedpref;
    Typeface myTypeface;
    Intent intent;
    int secondry_id;
    String name;
    TextView textCartItemCount;
    int mCartItemCount = 10;
    EditText search;
    Button search_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        realm = Realm.getDefaultInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        this.setTitle("");
        intent=getIntent();
        secondry_id=intent.getIntExtra("id",0);
        name="salonMan";
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);
        sharedpref = getSharedPreferences("Education", Context.MODE_PRIVATE);
        search=findViewById(R.id.search);
        search_button=findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search.getText().toString().equals("")||search.getText().toString()==null){
                    Toast.makeText(CardView.this,"ادخل اسم المنطقة التى تبحث عنها" , Toast.LENGTH_LONG).show();
                }
                fetchInfo_search(search.getText().toString());
            }
        });
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        progressBar=(ProgressBar)findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);
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

        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        if (!isNetworkAvailable(CardView.this)) {
            showdata();
            progressBar.setVisibility(View.GONE);
        } else {
            fetchInfo();
        }

    }
    public void fetchInfo(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_category>> call = apiinterface.getSalonCategry
                (intent.getIntExtra("id",0),
                  intent.getIntExtra("country",0),
                   intent.getIntExtra("category",0));
        call.enqueue(new Callback<List<contact_category>>() {
            @Override
            public void onResponse(Call<List<contact_category>> call, Response<List<contact_category>> response) {
                contactList = response.body();
                progressBar.setVisibility(View.GONE);
                WriteTodatabase(contactList);

            }

            @Override
            public void onFailure(Call<List<contact_category>> call, Throwable t) {

            }
        });
    }
    public void fetchInfo_search(String search){
        progressBar.setVisibility(View.VISIBLE);
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_category>> call = apiinterface.getSalonCategrySearch
                (intent.getIntExtra("id",0),
                        intent.getIntExtra("country",0),
                        intent.getIntExtra("category",0),search);
        call.enqueue(new Callback<List<contact_category>>() {
            @Override
            public void onResponse(Call<List<contact_category>> call, Response<List<contact_category>> response) {
                contactList = response.body();
                progressBar.setVisibility(View.GONE);
                WriteTodatabase(contactList);

            }

            @Override
            public void onFailure(Call<List<contact_category>> call, Throwable t) {

            }
        });
    }
    public void WriteTodatabase(List<contact_category> contactList){
//             realm.delete(subject_content_realm.class);
        // Create a new object
        deletedata();
        if(!(contactList==null)){
            for(int i=0;i<contactList.size();i++){
                realm.beginTransaction();
                contact_category_realm images = realm.createObject(contact_category_realm.class);

                images.setId(contactList.get(i).getId());
                images.setName(contactList.get(i).getName());
                images.setDetail(contactList.get(i).getDetail());
                images.setOffer(contactList.get(i).getOffer());
                images.setImage(contactList.get(i).getImage());
                images.setLat(contactList.get(i).getLat());
                images.setLng(contactList.get(i).getLng());
                images.setCity(contactList.get(i).getCity());
                images.setNameca(name);
                realm.commitTransaction();
            }
            showdata();}
        else{}
    }
    public void showdata(){

        RealmResults<contact_category_realm> content_realms = realm.where(contact_category_realm.class).equalTo("nameca", name).findAll();
        if (content_realms.isEmpty() || content_realms.equals(null)) {

        } else {    // realm.beginTransaction();
            List<contact_category_realm> result = content_realms;


            recyclerAdapter=new RecyclerAdapter(CardView.this,result);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

    public void deletedata(){
        realm.beginTransaction();
        RealmResults<contact_category_realm> content_realms=realm.where(contact_category_realm.class).equalTo("nameca", name).findAll();
        content_realms.deleteAllFromRealm();
        realm.commitTransaction();
    }
    public boolean isNetworkAvailable(Context ctx) {
        if (ctx == null)
            return false;

        ConnectivityManager cm =
                (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }



}

