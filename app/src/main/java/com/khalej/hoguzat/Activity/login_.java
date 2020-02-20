package com.khalej.hoguzat.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.khalej.hoguzat.R;
import com.khalej.hoguzat.model.Apiclient_home;
import com.khalej.hoguzat.model.apiinterface_home;
import com.khalej.hoguzat.model.contact_userinfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login_ {
    private List<contact_userinfo> contactList;
    private apiinterface_home apiinterface;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    ProgressDialog progressDialog;
    public void fetchInfo(final Context context, String phone, String password){
        sharedpref = context.getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();

        progressDialog = ProgressDialog.show(context,"جاري تسجيل الدخول","Please wait...",false,false);
        progressDialog.show();
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_userinfo>> call= apiinterface.getcontacts_login(phone,
               password);
        call.enqueue(new Callback<List<contact_userinfo>>() {
            @Override
            public void onResponse(Call<List<contact_userinfo>> call, Response<List<contact_userinfo>> response) {
                contactList = response.body();
                progressDialog.dismiss();
            try{
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(context);
                dlgAlert.setMessage("تم تسجيل الدخول بنجاح");
                dlgAlert.setTitle("Hugozat");
                dlgAlert.setPositiveButton("OK", null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                edt.putInt("id",contactList.get(0).getId());
                edt.putFloat("totalprice",0);
                edt.putString("name",contactList.get(0).getName());
                edt.putString("phone",contactList.get(0).getPhone());
                edt.putString("address",contactList.get(0).getmaddress());
                edt.putString("password",contactList.get(0).getPassword());
                edt.putString("remember","yes");
                edt.apply();
                selectCounty(context);
            }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<List<contact_userinfo>> call, Throwable t) {
                Toast.makeText(context,t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void selectCounty(final Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_details);


        dialog.setTitle("اختر الدولة التى تريدها");
        LinearLayout a,b,c,d,e,f;
        a=dialog.findViewById(R.id.a);
        b=dialog.findViewById(R.id.b);
        c=dialog.findViewById(R.id.c);
        d=dialog.findViewById(R.id.d);
        e=dialog.findViewById(R.id.e);
        f=dialog.findViewById(R.id.f);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",1);
                edt.apply();
                dialog.dismiss();
                context.startActivity(new Intent(context,MainActivity.class));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",2);
                edt.apply();
                dialog.dismiss();
                context.startActivity(new Intent(context,MainActivity.class));
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",3);
                edt.apply();
                dialog.dismiss();
                context.startActivity(new Intent(context,MainActivity.class));
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",4);
                edt.apply();
                dialog.dismiss();
                context.startActivity(new Intent(context,MainActivity.class));
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",5);
                edt.apply();
                dialog.dismiss();
                context.startActivity(new Intent(context,MainActivity.class));
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",6);
                edt.apply();
                dialog.dismiss();
                context.startActivity(new Intent(context,MainActivity.class));
            }
        });
        dialog.show();
    }
}
