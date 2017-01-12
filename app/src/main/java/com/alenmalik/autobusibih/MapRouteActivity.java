package com.alenmalik.autobusibih;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapRouteActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_route);
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

        // Add a marker in Sydney and move the camera

    /*    LatLngBounds.Builder builder = new LatLngBounds.Builder();

        ArrayList<Marker> markers = new ArrayList<Marker>();

        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(CityActivity.newLat, CityActivity.newLng)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title("Rider Location")));

        markers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(ListToCityActivity.secondCityLAT, ListToCityActivity.secondCityLNG)).title("Your Location")));


        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }

        LatLngBounds bounds = builder.build();

        int padding = 100;
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

        mMap.animateCamera(cu);*/


        LatLngBounds.Builder routeBuilder = new LatLngBounds.Builder();

        ArrayList<Marker> routeMarkers = new ArrayList<Marker>();

        routeMarkers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(RouteActivity.routeLatFromCIty, RouteActivity.routeLngFromCity)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)).title("Rider Location")));

         routeMarkers.add(mMap.addMarker(new MarkerOptions().position(new LatLng(RouteActivity.routeLatToCIty, RouteActivity.routeLngToCity)).title("Your Location")));


        for (Marker routeMarker : routeMarkers) {
            routeBuilder.include(routeMarker.getPosition());
        }

        LatLngBounds newBounds = routeBuilder.build();

        int padding1 = 100;
        CameraUpdate cu1 = CameraUpdateFactory.newLatLngBounds(newBounds, padding1);

        mMap.animateCamera(cu1);

//beze komentaer

    }

    public void routeDirection(View view){

        /*String uri = "http://maps.google.com/maps?f=d&hl=en&saddr="+CityActivity.newLat+","+CityActivity.newLng+"&daddr="+ListToCityActivity.secondCityLAT+","+ListToCityActivity.secondCityLNG;
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);*/

        String uri1 = "http://maps.google.com/maps?f=d&hl=en&saddr="+RouteActivity.routeLngFromCity+","+RouteActivity.routeLatFromCIty+"&daddr="+RouteActivity.routeLatToCIty+","+RouteActivity.routeLngToCity;
        Intent intent1 = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri1));
        startActivity(intent1);



    }
}
