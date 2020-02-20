package com.khalej.hoguzat.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.khalej.hoguzat.R;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class info extends Fragment {
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;

    //Constructor default
    public info() {
    }

    ;
    String detail;
    TextView detailView;
    ImageView mMapView;
    private GoogleMap googleMapm;
    private FragmentActivity myContext;
    float lat;
    float lng;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageTwo = inflater.inflate(R.layout.activity_info, container, false);
        sharedpref = getActivity().getSharedPreferences("Education", Context.MODE_PRIVATE);
        edt = sharedpref.edit();
        detail = sharedpref.getString("details", "");
        lat=sharedpref.getFloat("lat",0);
        lng=sharedpref.getFloat("lng",0);
        detailView = PageTwo.findViewById(R.id.detail);
        detailView.setText(detail);

        mMapView = PageTwo.findViewById(R.id.map);

        mMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MapsActivity.class));
            }
        });
        return PageTwo;
    }



}