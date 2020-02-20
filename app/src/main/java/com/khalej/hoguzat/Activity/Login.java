package com.khalej.hoguzat.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.khalej.hoguzat.R;
import com.khalej.hoguzat.model.Apiclient_home;
import com.khalej.hoguzat.model.apiinterface_home;
import com.khalej.hoguzat.model.contact_userinfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    AppCompatButton Naccount;
    AppCompatButton login,textViewLinkguest;
    TextInputEditText textInputEditTextphone,textInputEditTextpassword;
    TextInputLayout textInputLayoutphone,textInputLayoutpassword;
    private List<contact_userinfo> contactList;
    private apiinterface_home apiinterface;
        private SharedPreferences sharedpref;
        private SharedPreferences.Editor edt;
    ProgressDialog progressDialog;
    String codee;
    LoginButton loginButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        Calligrapher calligrapher = new Calligrapher(this);
        calligrapher.setFont(this, "Droid.ttf", true);

         callbackManager = CallbackManager.Factory.create();
         loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
               //
                useLoginInformation(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(Login.this,"Cansel",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(Login.this,exception+"",Toast.LENGTH_LONG).show();

            }
        });

        textInputLayoutphone=(TextInputLayout)findViewById(R.id.textInputLayoutphone);
        textInputLayoutpassword=(TextInputLayout)findViewById(R.id.textInputLayoutPassword);
        textInputEditTextphone=(TextInputEditText)findViewById(R.id.textInputEditTextphone);

        textViewLinkguest=findViewById(R.id.textViewLinkguest);


        sharedpref = getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();

        if(sharedpref.getString("remember","").trim().equals("yes")){
            edt.putFloat("totalprice",0);
            edt.apply();
            startActivity(new Intent(Login.this,MainActivity.class));

            finish();
        }
        textInputEditTextpassword=(TextInputEditText)findViewById(R.id.textInputEditTextPassword);
        Naccount=(AppCompatButton)findViewById(R.id.textViewLinkRegister);
        Naccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registration.class));
            }
        });
        login=(AppCompatButton)findViewById(R.id.appCompatButtonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               fetchInfo();
                //startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
        textViewLinkguest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",1);
                edt.apply();
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resulrCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resulrCode, data);
        super.onActivityResult(requestCode, resulrCode, data);
    }
    public void fetchInfo(){
        progressDialog = ProgressDialog.show(Login.this,"جاري تسجيل الدخول","Please wait...",false,false);
        progressDialog.show();
        String phone=textInputEditTextphone.getText().toString();
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_userinfo>> call= apiinterface.getcontacts_login(phone,
                textInputEditTextpassword.getText().toString());
        call.enqueue(new Callback<List<contact_userinfo>>() {
            @Override
            public void onResponse(Call<List<contact_userinfo>> call, Response<List<contact_userinfo>> response) {
                if(response.isSuccessful()){

                    contactList = response.body();
                    try {
                        progressDialog.dismiss();
                        edt.putInt("id",contactList.get(0).getId());
                        edt.putString("name",contactList.get(0).getName());
                        edt.putString("phone",contactList.get(0).getPhone());
                        edt.putString("address",contactList.get(0).getmaddress());
                        edt.putString("password",contactList.get(0).getPassword());
                        edt.putString("remember","yes");
                        edt.apply();

                        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Login.this);
                        dlgAlert.setMessage("تم تسجيل الدخول بنجاح");
                        dlgAlert.setTitle("Hugozat");
                        dlgAlert.setPositiveButton("OK", null);
                        dlgAlert.setCancelable(true);
                        dlgAlert.create().show();
                        selectCounty();}
                    catch (Exception e){
                        Toast.makeText(Login.this,"هناك خطأ فى الهاتف او الرقم السري /",Toast.LENGTH_LONG).show();

                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<contact_userinfo>> call, Throwable t) {
                Toast.makeText(Login.this,"هناك خطأ فى الهاتف او الرقم السري",Toast.LENGTH_LONG).show();

                progressDialog.dismiss();
            }
        });
    }
    private void useLoginInformation(AccessToken accessToken) {
        /**
         Creating the GraphRequest to fetch user details
         1st Param - AccessToken
         2nd Param - Callback (which will be invoked once the request is successful)
         **/
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            //OnCompleted is invoked once the GraphRequest is successful
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("name");
                    String email = object.getString("email");
                    String image = object.getJSONObject("picture").getJSONObject("data").getString("url");
                    edt.putString("name",name);
                    edt.putString("phone","00000000");
                    edt.putString("address",email);
                    edt.putString("image",image);
                    edt.putString("remember","yes");
                    edt.apply();

                    AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Login.this);
                    dlgAlert.setMessage("تم تسجيل الدخول بنجاح");
                    dlgAlert.setTitle("Hugozat");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.setCancelable(true);
                    dlgAlert.create().show();
                    selectCounty();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        // We set parameters to the GraphRequest using a Bundle.
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(parameters);
        // Initiate the GraphRequest
        request.executeAsync();
    }

    public void selectCounty(){
        final Dialog dialog = new Dialog(Login.this);
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
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",2);
                edt.apply();
                dialog.dismiss();
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",3);
                edt.apply();
                dialog.dismiss();
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",4);
                edt.apply();
                dialog.dismiss();
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",5);
                edt.apply();
                dialog.dismiss();
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.putInt("country",6);
                edt.apply();
                dialog.dismiss();
                startActivity(new Intent(Login.this,MainActivity.class));
            }
        });
        dialog.show();
    }
}
