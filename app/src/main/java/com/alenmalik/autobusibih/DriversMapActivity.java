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
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
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
import com.google.android.gms.maps.CameraUpdate;
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
    Marker lastOpenned = null;
    RelativeLayout mapLayout;

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
        mapLayout = (RelativeLayout) findViewById(R.id.relativeLayoutMap);


    }


    public void retriveLocation() {
        Intent getInfo = getIntent();
        final String prijevoznik = getInfo.getStringExtra("prijevoznik");

        ref = database.getReference().child("Pracenje").child(prijevoznik);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Double> latArray = new ArrayList<Double>();
                ArrayList<Double> lngArray = new ArrayList<Double>();
                ArrayList<LatLng> locations = new ArrayList<LatLng>();
                ArrayList<String> gradovi = new ArrayList<String>();
                ArrayList<String> adrese = new ArrayList<String>();
                LatLngBounds.Builder builder = new LatLngBounds.Builder();

                ArrayList<Marker> markers = new ArrayList<Marker>();
                latArray.clear();
                lngArray.clear();
                locations.clear();
                gradovi.clear();
                adrese.clear();
                Log.i("broj keyeva", String.valueOf(dataSnapshot.getChildrenCount()));
                int numberKey = (int) dataSnapshot.getChildrenCount();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (dataSnapshot1.exists()) {


                        if (dataSnapshot1.child("latitude").exists() && dataSnapshot1.child("longitude").exists()) {
                            Double latitude = Double.parseDouble(String.valueOf(dataSnapshot1.child("latitude").getValue()));
                            if (latitude != null) {
                                latArray.add(latitude);
                            }
                            Double longitude = Double.parseDouble(String.valueOf(dataSnapshot1.child("longitude").getValue()));
                            if (longitude != null) {
                                lngArray.add(longitude);
                            }

                            String adresa = String.valueOf(dataSnapshot1.child("adresa").getValue());
                            adrese.add(adresa);
                            String grad = String.valueOf(dataSnapshot1.child("grad").getValue());
                            gradovi.add(grad);

                            mMap.clear();

                            // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 16));
                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.busbus);

                            for (int i = 0; i < latArray.size() && i < lngArray.size() && i < gradovi.size() && i < adrese.size(); i++) {

                                markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(latArray.get(i), lngArray.get(i))).title(prijevoznik).snippet("Trenutni grad: " + gradovi.get(i) + "\n" + "Trenutna adresa: " + adrese.get(i)).icon(icon)));
                                Log.i("novi marker", String.valueOf(true));

                            }
                            for (Marker marker : markers) {
                                builder.include(marker.getPosition());

                            }

                            LatLngBounds bounds = builder.build();

                            int padding = 100;
                            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);


                            mMap.animateCamera(cu);
                        }

                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.infow_window,null);
                TextView prijevoznikTitle = (TextView) v.findViewById(R.id.ime_prijevoznika);
                TextView grad = (TextView) v.findViewById(R.id.grad);
                TextView adresa = (TextView) v.findViewById(R.id.adresa);

                prijevoznikTitle.setText(marker.getTitle());
                grad.setText(marker.getSnippet());
                return v;
            }
        });



        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                // Check if there is an open info window
                if (lastOpenned != null) {
                    // Close the info window
                    lastOpenned.hideInfoWindow();

                    // Is the marker the same marker that was already open
                    if (lastOpenned.equals(marker)) {
                        // Nullify the lastOpenned object
                        lastOpenned = null;
                        // Return so that the info window isn't openned again
                        return true;
                    }
                }

                // Open the info window for the marker
                marker.showInfoWindow();
                // Re-assign the last openned such that we can close it later
                lastOpenned = marker;

                // Event was handled by our code do not launch default behaviour.
                return true;
            }
        });
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
