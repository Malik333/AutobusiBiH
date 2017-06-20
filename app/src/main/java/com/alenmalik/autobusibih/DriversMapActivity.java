package com.alenmalik.autobusibih;

import android.*;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Interpolator;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;


import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.GeoQuery;
import com.firebase.geofire.GeoQueryEventListener;
import com.firebase.geofire.LocationCallback;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DriversMapActivity extends FragmentActivity implements OnMapReadyCallback {



    private Map<String, Marker> markers;
    private GoogleMap mMap;
    DatabaseReference ref;
    FirebaseDatabase database;

    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
  database = FirebaseDatabase.getInstance();


        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                retriveLocation();
            }

        }.start();

    }



    public void retriveLocation(){
        Intent getInfo = getIntent();
        final String prijevoznik = getInfo.getStringExtra("prijevoznik");
        String linija = getInfo.getStringExtra("linija");
        Log.i("valuePrijevoznik", prijevoznik);
        Log.i("valueLinija", linija);

        ref = database.getReference().child("Pracenje").child(prijevoznik).child(linija);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Double latitude = Double.parseDouble(String.valueOf(dataSnapshot1.child("latitude").getValue()));
                    Double longitude = Double.parseDouble(String.valueOf(dataSnapshot1.child("longitude").getValue()));
                    Float speed = Float.parseFloat(String.valueOf(dataSnapshot1.child("brzina").getValue()));
                    String city = String.valueOf(dataSnapshot1.child("grad").getValue());
                   mMap.clear();

                    mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title(prijevoznik + speed.toString() + city));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude,longitude),16));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       /* DatabaseReference retriveRef = ref.child("Pracenje").child(prijevoznik).child(linija);
       retriveRef.addChildEventListener(new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               try {
                   Map<String, Object> value = (Map<String, Object>) dataSnapshot.getValue();
                   Double latitude = Double.parseDouble(String.valueOf(value.get("latitude")));
                   Double longitude = Double.parseDouble(String.valueOf(value.get("longitude")));
                   Log.i("value", String.valueOf(value.get("latitude")));


                   mMap.clear();
                   Marker myMarker;
                   myMarker = mMap.addMarker(new MarkerOptions().position(new LatLng(latitude,longitude)).title(prijevoznik));
                   myMarker.setPosition(new LatLng(latitude, longitude));
                   mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myMarker.getPosition(),14));
               }catch (Exception e){
                   e.printStackTrace();
               }






           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {

           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });*/



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        new CountDownTimer(3000, 1000) {

            public void onTick(long millisUntilFinished) {
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                retriveLocation();
            }

        }.start();


    }



    @Override
    protected void onStop() {
        super.onStop();


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
