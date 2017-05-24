package com.alenmalik.autobusibih;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.database.DatabaseReference;

public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

private RecyclerView reklameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

reklameList = (RecyclerView) findViewById(R.id.autobusi_recycler_view_reklame);
        reklameList.setHasFixedSize(true);
        reklameList.setLayoutManager(new LinearLayoutManager(this));

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        drawer.openDrawer(Gravity.LEFT);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_medjugradski) {
            // Handle the camera action
            Intent intent = new Intent(MainPage.this,CityTraffic.class);
            startActivity(intent);
        } else if (id == R.id.nav_medjunarodni) {
            Intent intent = new Intent(MainPage.this,InternacionalTraffic.class);
            startActivity(intent);
        } else if (id == R.id.nav_stanice) {
            Intent intent = new Intent(MainPage.this,BusStateListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_prijevoznici) {
            Intent intent = new Intent(MainPage.this,BusTransport.class);
            startActivity(intent);
        } else if (id == R.id.nav_kontakt_informacije) {
            Intent intent = new Intent(MainPage.this,ContactInfo.class);
            startActivity(intent);

        } else if (id == R.id.nav_o_aplikaciji) {

            Intent intent = new Intent(MainPage.this,AboutApplication.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
