package com.khalej.hoguzat.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.khalej.hoguzat.R;
import com.khalej.hoguzat.model.Apiclient_home;
import com.khalej.hoguzat.model.apiinterface_home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import me.anwarshahriar.calligrapher.Calligrapher;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fatoura extends AppCompatActivity {
    TextView name, phone, address, details, getfinal, charge, price;
    Intent intent;
    Toolbar toolbar;
    Button cunti;
    private apiinterface_home apiinterface;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatoura);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);
        cunti=findViewById(R.id.cunti);
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
        sharedpref = getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();

        intent = getIntent();
        name = findViewById(R.id.name);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);

        details = findViewById(R.id.details);
        price = findViewById(R.id.price);
        name.setText(sharedpref.getString("name",""));
        phone.setText(sharedpref.getString("phone",""));
        address.setText(sharedpref.getString("address",""));
         price.setText(sharedpref.getFloat("TotalPrice",0)+"");

        String a=intent.getStringExtra("title")+"\n"+"معاد الحجز :"+"\n"+
                intent.getStringExtra("date")+"  "+intent.getStringExtra("time");
        details.setText(a);


        cunti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Fatoura.this)
                        .setTitle(R.string.app_name)
                        .setMessage(R.string.ask)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                fetchdata();
                                cunti.setClickable(false);
                                edt.putFloat("totalprice", 0);
                                edt.putInt("number",0);
                                edt.putString("tager","");
                                edt.apply();
                            }
                        }).setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
    public void fetchdata() {
        progressDialog = ProgressDialog.show(Fatoura.this, "جاري تسجيل طلبك", "Please wait...", false, false);
        progressDialog.show();


        //  String currentTime = Calendar.getInstance().getTime().toString();
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<ResponseBody> call = apiinterface.getcontacts_order(name.getText().toString(),
                address.getText().toString(), phone.getText().toString()
                , details.getText().toString(), sharedpref.getFloat("TotalPrice",0));
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                // Toast.makeText(Shopping_car.this,details,Toast.LENGTH_LONG).show();
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(Fatoura.this);
                dlgAlert.setMessage(R.string.success );
                dlgAlert.setTitle(R.string.app_name);
                dlgAlert.setPositiveButton(android.R.string.yes, null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                cunti.setClickable(true);
                edt.putFloat("TotalPrice", 0);
                edt.apply();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //   Toast.makeText(get_order.this,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
