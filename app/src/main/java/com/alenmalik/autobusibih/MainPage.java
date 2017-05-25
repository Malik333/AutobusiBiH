package com.alenmalik.autobusibih;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView reklameList;
    Button probabuton;

    DatabaseReference mReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        probabuton = (Button) findViewById(R.id.probabuton);
        reklameList = (RecyclerView) findViewById(R.id.autobusi_recycler_view_reklame);
        reklameList.setHasFixedSize(false);
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
    protected void onStart() {

        super.onStart();

        mReference = FirebaseDatabase.getInstance().getReference().child("Reklame");
        FirebaseRecyclerAdapter<ReklameModel, ReklameViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ReklameModel, ReklameViewHolder>(
                ReklameModel.class,
                R.layout.reklame_row,
                ReklameViewHolder.class,
                mReference
        ) {
            @Override
            protected void populateViewHolder(ReklameViewHolder viewHolder, ReklameModel model, int position) {
                viewHolder.setPrijevoznik(model.getPrijevoznik());
                viewHolder.setGalerija1(getApplicationContext(),model.getGalerija1());
                viewHolder.setGalerija2(getApplicationContext(),model.getGalerija2());
                viewHolder.setGalerija3(getApplicationContext(),model.getGalerija3());
                viewHolder.setGalerija4(getApplicationContext(),model.getGalerija4());
                viewHolder.setSamojednaslika(getApplicationContext(),model.getSamojednaslika());
            }
        };
        reklameList.setAdapter(firebaseRecyclerAdapter);


    }

    public static class ReklameViewHolder extends RecyclerView.ViewHolder {

        View mView;
        RelativeLayout layoutforgalerija;
        RelativeLayout layoutforjednaslika;

        public ReklameViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            layoutforgalerija = (RelativeLayout) mView.findViewById(R.id.layout_for_galerija);
            layoutforjednaslika = (RelativeLayout) mView.findViewById(R.id.layoutforsamojednaslika);
        }

        public void setPrijevoznik(String prijevoznik) {
            if (prijevoznik != null) {
                TextView probaPrijevoznik = (TextView) mView.findViewById(R.id.prijevoznik_ime);
                probaPrijevoznik.setText(prijevoznik);
            }
        }

        public void setGalerija1(Context ctx, String galerija1) {
            if (galerija1 != null) {
                layoutforgalerija.setVisibility(View.VISIBLE);
                ImageView galerija1ImageView = (ImageView) mView.findViewById(R.id.slika1_za_galeriju);
                Picasso.with(ctx).load(galerija1).into(galerija1ImageView);


            } else {
                layoutforgalerija.setVisibility(View.GONE);
            }

        }

        public void setGalerija2(Context ctx, String galerija2) {

            if (galerija2 != null) {
                layoutforgalerija.setVisibility(View.VISIBLE);
                ImageView galerija2ImageView = (ImageView) mView.findViewById(R.id.slika2_za_galeriju);
                Picasso.with(ctx).load(galerija2).into(galerija2ImageView);


            } else {
                layoutforgalerija.setVisibility(View.GONE);
            }
        }

        public void setGalerija3(Context ctx, String galerija3) {

            if (galerija3 != null) {
                layoutforgalerija.setVisibility(View.VISIBLE);
                ImageView galerija3ImageView = (ImageView) mView.findViewById(R.id.slika3_za_galeriju);
                Picasso.with(ctx).load(galerija3).into(galerija3ImageView);


            } else {
                layoutforgalerija.setVisibility(View.GONE);
            }
        }

        public void setGalerija4(Context ctx, String galerija4) {
            if (galerija4 != null) {
                layoutforgalerija.setVisibility(View.VISIBLE);
                ImageView galerija4ImageView = (ImageView) mView.findViewById(R.id.slika4_za_galeriju);
                Picasso.with(ctx).load(galerija4).into(galerija4ImageView);


            } else {
                layoutforgalerija.setVisibility(View.GONE);
            }

        }

        public void setSamojednaslika(Context ctx, String samojednaslika) {
        if (samojednaslika != null){

            layoutforjednaslika.setVisibility(View.VISIBLE);
            ImageView samojednaslikaImageView = (ImageView) mView.findViewById(R.id.samojednaslika);
            Picasso.with(ctx).load(samojednaslika).into(samojednaslikaImageView);
        }else {
            layoutforjednaslika.setVisibility(View.GONE);
        }

        }


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
            Intent intent = new Intent(MainPage.this, CityTraffic.class);
            startActivity(intent);
        } else if (id == R.id.nav_medjunarodni) {
            Intent intent = new Intent(MainPage.this, InternacionalTraffic.class);
            startActivity(intent);
        } else if (id == R.id.nav_stanice) {
            Intent intent = new Intent(MainPage.this, BusStateListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_prijevoznici) {
            Intent intent = new Intent(MainPage.this, BusTransport.class);
            startActivity(intent);
        } else if (id == R.id.nav_kontakt_informacije) {
            Intent intent = new Intent(MainPage.this, ContactInfo.class);
            startActivity(intent);

        } else if (id == R.id.nav_o_aplikaciji) {

            Intent intent = new Intent(MainPage.this, AboutApplication.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
