package com.khalej.hoguzat.Activity;


import android.os.Bundle;

import android.view.View;

import com.khalej.hoguzat.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import me.anwarshahriar.calligrapher.Calligrapher;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
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

        Element versionElement = new Element();
        versionElement.setTitle("Version 1.0");

        Element adsElement = new Element();
        adsElement.setTitle("Advertise with us");

        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .addItem(versionElement)
                .addItem(adsElement)
                .setDescription(" العلامه الرائدة في مجال الصحه والجمال هي شركة تتخصص بتقديم خدمات سريعة ومريحة مع صالونات وأخصائيات تجميل محترفات في مختلف مدن المنطقة من خلال موقعها الإلكتروني وتطبيقات الهواتف الجوّالة الذكية. تتميز الخدمة بالمرونة والراحة والأمان، كما تتيح لزبائنها من الأفراد والمؤسسات طلب حجز موعد مع احدى أخصائيات التجميل في العديد من الصوالين لتحصلي على موعد التجميل إما فوراً أو في وقت لاحق، وذلك من خلال موقع الشركة الإلكتروني، أو عبر تطبيق الهاتف الذكي الخاص بك كما ذكرنا، بالإضافة الى توفر انواع من الحجوزات المتنوعه والصحيه ، والى إمكانية تقيييم مستوى الخدمه والدفع بسهولة نقداً أو بواسطة بطاقة الائتمان.")
                .addGroup("Connect with us")
                .addEmail("hoguzat@gmail.com")
                .addFacebook("hoguzat.app.77")
                .addTwitter("hoguzat")
                .addPlayStore("com.khalej.hoguzat")
                .addInstagram("hoguzat.co")
                .create();

        setContentView(aboutPage);
    }
}
