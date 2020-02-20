package com.khalej.hoguzat.Adapter;

import android.content.ContentProviderOperation;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.khalej.hoguzat.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * Created by reale on 4/20/2017.
 */

public class MyAdapter  extends PagerAdapter {

    List<Integer> lstImages;
    Context context;
    LayoutInflater layoutInflater;

    public MyAdapter(List<Integer> lstImages, Context context) {
        this.lstImages = lstImages;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lstImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.card_item,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
        imageView.setImageResource(lstImages.get(position));
        container.addView(view);
        return view;
    }
}
