package com.khalej.hoguzat.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.khalej.hoguzat.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

private GoogleMap mMap;
String lati,longg;
Intent in;
Handler mHandler;
    String detail;
    float lat;
    float lng;
    private SharedPreferences sharedpref;
    private SharedPreferences.Editor edt;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    sharedpref = getSharedPreferences("Education", Context.MODE_PRIVATE);
    edt = sharedpref.edit();
    detail = sharedpref.getString("details", "");

    lat=sharedpref.getFloat("lat",0);
    lng=sharedpref.getFloat("lng",0);
    // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);

}


/**
 * Manipulates the map once available.
 * This callback is triggered when the map is ready to be used.
 * This is where we can add markers or lines, add listeners or move the camera. In this case,
 * we just add a marker near Sydney, Australia.
 * If Google Play services is not installed on the device, the user will be prompted to install
 * it inside the SupportMapFragment. This method will only be triggered once the user has
 * installed Google Play services and returned to the app.
 */
@Override
public void onMapReady(GoogleMap googleMap) {
    mMap = googleMap;
    lat=sharedpref.getFloat("lat",0);
    lng=sharedpref.getFloat("lng",0);
    // For dropping a marker at a point on the Map
    LatLng sydney = new LatLng(lat, lng);
    mMap.addMarker(new MarkerOptions().position(sydney).title(sharedpref.getString("marketName", "")).snippet(""));

    // For zooming automatically to the location of the marker
    CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(sydney,16);
    mMap.animateCamera(cameraPosition);

}

@Override
public void onPointerCaptureChanged(boolean hasCapture) {

}
public void mylocation(final LatLng latLng){


}
}

