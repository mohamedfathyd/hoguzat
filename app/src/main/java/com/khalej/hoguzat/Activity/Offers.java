package com.khalej.hoguzat.Activity;

import android.content.Context;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khalej.hoguzat.R;

import androidx.fragment.app.Fragment;

public class Offers extends Fragment {
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
    //Constructor default
    public Offers(){};
      String offer;
      TextView offerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageTwo = inflater.inflate(R.layout.activity_offers, container, false);
        sharedpref = getActivity().getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        offer=sharedpref.getString("offers","");
        offerView=PageTwo.findViewById(R.id.offer);
        offerView.setText(offer);
        return PageTwo;
    }
}