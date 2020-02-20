package com.khalej.hoguzat.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.khalej.hoguzat.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import me.anwarshahriar.calligrapher.Calligrapher;

public class SelectDate extends AppCompatActivity {
    RelativeLayout c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12;
    String dateString,time;
    Button cunti;
    double Price;
    Intent intent;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date);
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, 0);
        intent=getIntent();
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        sharedpref = getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        Price=sharedpref.getFloat("TotalPrice",0);

        cunti=findViewById(R.id.cunti);
        c1=findViewById(R.id.c1);
        c2=findViewById(R.id.c2);
        c3=findViewById(R.id.c3);
        c4=findViewById(R.id.c4);
        c5=findViewById(R.id.c5);
        c6=findViewById(R.id.c6);
        c7=findViewById(R.id.c7);
        c8=findViewById(R.id.c8);
        c9=findViewById(R.id.c9);
        c10=findViewById(R.id.c10);
        c11=findViewById(R.id.c11);
        c12=findViewById(R.id.c12);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
  time="11:00 AM";
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="12:00 AM";
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="01:00 PM";
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="02:00 PM";
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="03:00 PM";
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="04:00 PM";
            }
        });
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="05:00 PM";
            }
        });
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="06:00 PM";
            }
        });
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="07:00 PM";
            }
        });
        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="08:00 PM";
            }
        });
        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="09:00 PM";
            }
        });
        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time="10:00 PM";
            }
        });



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
        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                .range(startDate, endDate)
                .datesNumberOnScreen(5)
                .build();

        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMM   yyyy");
                dateString= String.valueOf(dateFormat.format(date.getTime()));

            }
        });

        cunti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dateString==null||dateString.equals("")){
                    Toast.makeText(SelectDate.this,"من فضلك حدد تاريخ للطلب" ,Toast.LENGTH_LONG).show();
                    return;
                }
                if(time==null||time.equals("")){
                    Toast.makeText(SelectDate.this,"من فضلك حدد تاريخ للطلب" ,Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i=new Intent(SelectDate.this,Fatoura.class);
                i.putExtra("time",time);
                i.putExtra("date",dateString);
                i.putExtra("price",Price);
                i.putExtra("title",intent.getStringExtra("title"));
                startActivity(i);
            }
        });
    }
}
