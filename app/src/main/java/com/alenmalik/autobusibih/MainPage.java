package com.alenmalik.autobusibih;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
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
            protected void populateViewHolder(final ReklameViewHolder viewHolder, ReklameModel model, int position) {
                viewHolder.setPrijevoznik(model.getPrijevoznik());
                viewHolder.setHeaderTextPrviObavezni(model.getHeaderTextPrviObavezni());
                viewHolder.setHeaderText2(model.getHeaderText2());
                viewHolder.setHeaderText3(model.getHeaderText3());
                viewHolder.setHeaderText4(model.getHeaderText4());
                viewHolder.setMainTextHeaderObavezni(model.getMainTextHeaderObavezni());
                viewHolder.setMainTextHeader2(model.getMainTextHeader2());
                viewHolder.setMainTextHeader3(model.getMainTextHeader3());
                viewHolder.setSlika(getApplicationContext(),model.getSlika());
                viewHolder.setDonjiTextObavezni(model.getDonjiTextObavezni());
                viewHolder.setDonjiTextKojiNaglasava(model.getDonjiTextKojiNaglasava());
                viewHolder.setDonjiTextDodatni(model.getDonjiTextDodatni());
                viewHolder.setDonjiTextDodatni2(model.getDonjitTextDodatni2());


            }
        };
        reklameList.setAdapter(firebaseRecyclerAdapter);


    }

    public static class ReklameViewHolder extends RecyclerView.ViewHolder {

        View mView;
        RelativeLayout layoutForObavezni;
        RelativeLayout layoutForDodatni1;
        RelativeLayout layoutForDodatni2;
        RelativeLayout layoutForDodatni3;
        RelativeLayout layoutForMainObavezni;
        RelativeLayout layoutForMainDodatni1;
        RelativeLayout layoutForMainDodatni2;
        RelativeLayout layoutForDonjiObavezni;
        RelativeLayout layoutForDonjiNaglasava;
        RelativeLayout layoutForDonjiDodatni;
        RelativeLayout layoutForDonjiDodatni2;
        RelativeLayout layoutForSlika;


        public ReklameViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            layoutForObavezni = (RelativeLayout) mView.findViewById(R.id.layout_header_text1);
            layoutForDodatni1 = (RelativeLayout) mView.findViewById(R.id.layout_header_text2);
            layoutForDodatni2 = (RelativeLayout) mView.findViewById(R.id.layout_header_text3);
            layoutForDodatni3 = (RelativeLayout) mView.findViewById(R.id.layout_header_text4);
            layoutForMainObavezni = (RelativeLayout) mView.findViewById(R.id.layout_for_main_header_text);
            layoutForMainDodatni1 = (RelativeLayout) mView.findViewById(R.id.layout_for_main_header_text2);
            layoutForMainDodatni2 = (RelativeLayout) mView.findViewById(R.id.layout_for_main_header_text3);
            layoutForDonjiObavezni = (RelativeLayout) mView.findViewById(R.id.layout_for_donji_text_jedan);
            layoutForDonjiNaglasava = (RelativeLayout) mView.findViewById(R.id.layout_for_text_koji_nesto_naglasi);
            layoutForDonjiDodatni = (RelativeLayout) mView.findViewById(R.id.layout_for_dodatni_text);
            layoutForDonjiDodatni2 = (RelativeLayout) mView.findViewById(R.id.layout_for_dodatni_text2);
            layoutForSlika = (RelativeLayout) mView.findViewById(R.id.container_for_image);
        }

        public void setPrijevoznik(String prijevoznik) {
            if (prijevoznik != null) {
                TextView probaPrijevoznik = (TextView) mView.findViewById(R.id.prijevoznik_ime);
                probaPrijevoznik.setText(prijevoznik);
            }
        }


        public void setHeaderTextPrviObavezni(String headerTextPrviObavezni) {

            if (headerTextPrviObavezni != null) {
                layoutForObavezni.setVisibility(View.VISIBLE);
                TextView obavezni_text_header = (TextView) mView.findViewById(R.id.grad_adresa_info);
                obavezni_text_header.setText(headerTextPrviObavezni);

            } else {
                layoutForObavezni.setVisibility(View.GONE);
            }

        }

        public void setHeaderText2(String headerText2) {
            if (headerText2 != null) {
                layoutForDodatni1.setVisibility(View.VISIBLE);
                TextView heaader_text_drugi = (TextView) mView.findViewById(R.id.header_dodatni_text);
                heaader_text_drugi.setText(headerText2);
            } else {
                layoutForDodatni1.setVisibility(View.GONE);
            }

        }

        public void setHeaderText3(String headerText3) {
            if (headerText3 != null) {
                layoutForDodatni2.setVisibility(View.VISIBLE);
                TextView heaader_text_treci = (TextView) mView.findViewById(R.id.header_dodatni_text2);
                heaader_text_treci.setText(headerText3);
            } else {
                layoutForDodatni2.setVisibility(View.GONE);
            }

        }

        public void setHeaderText4(String headerText4) {
            if (headerText4 != null) {
                layoutForDodatni3.setVisibility(View.VISIBLE);
                TextView heaader_text_cetvrti = (TextView) mView.findViewById(R.id.header_dodatni_text3);
                heaader_text_cetvrti.setText(headerText4);
            } else {
                layoutForDodatni3.setVisibility(View.GONE);
            }

        }

        public void setMainTextHeaderObavezni(String mainTextHeaderObavezni) {

            if (mainTextHeaderObavezni != null) {
                layoutForMainObavezni.setVisibility(View.VISIBLE);
                TextView main_obavezni_header = (TextView) mView.findViewById(R.id.main_text_header);
                main_obavezni_header.setText(mainTextHeaderObavezni);


            } else {
                layoutForMainObavezni.setVisibility(View.GONE);
            }

        }
        public void setMainTextHeader2(String mainTextHeader2){


            if (mainTextHeader2 != null) {
                layoutForMainDodatni1.setVisibility(View.VISIBLE);
                TextView main_dodatni_header = (TextView) mView.findViewById(R.id.main_text_header2);
                main_dodatni_header.setText(mainTextHeader2);


            } else {
                layoutForMainDodatni1.setVisibility(View.GONE);
            }
        }


        public void setMainTextHeader3(String mainTextHeader3){


            if (mainTextHeader3 != null) {
                layoutForMainDodatni2.setVisibility(View.VISIBLE);
                TextView main_dodatni2_header = (TextView) mView.findViewById(R.id.main_text_header3);
                main_dodatni2_header.setText(mainTextHeader3);


            } else {
                layoutForMainDodatni2.setVisibility(View.GONE);
            }
        }

        public void setSlika(Context ctx,String slika){
          if (slika != null){
              layoutForSlika.setVisibility(View.VISIBLE);
              ImageView slika_image_view = (ImageView) mView.findViewById(R.id.imageForReklame);
              Picasso.with(ctx).load(slika).into(slika_image_view);

          }else {
              layoutForSlika.setVisibility(View.GONE);
          }
        }

        public void setDonjiTextObavezni (String donjiTextObavezni){
            if (donjiTextObavezni != null){
                layoutForDonjiObavezni.setVisibility(View.VISIBLE);
                TextView donji_obavezni_textview = (TextView) mView.findViewById(R.id.donji_text_obavezni);
                donji_obavezni_textview.setText(donjiTextObavezni);
            }else {
                layoutForDonjiObavezni.setVisibility(View.GONE);
            }

        }


        public void setDonjiTextKojiNaglasava (String donjiTextKojiNaglasava){
            if (donjiTextKojiNaglasava != null){
                layoutForDonjiNaglasava.setVisibility(View.VISIBLE);
                TextView naglasava= (TextView) mView.findViewById(R.id.text_koji_naglasava);
                naglasava.setText(donjiTextKojiNaglasava);
            }else {
                layoutForDonjiNaglasava.setVisibility(View.GONE);
            }

        }

        public void setDonjiTextDodatni (String donjiTextDodatni){
            if (donjiTextDodatni != null){
                layoutForDonjiDodatni.setVisibility(View.VISIBLE);
                TextView donji_dodatni = (TextView) mView.findViewById(R.id.donji_dodatni_text);
                donji_dodatni.setText(donjiTextDodatni);
            }else {
                layoutForDonjiDodatni.setVisibility(View.GONE);
            }

        }

        public void setDonjiTextDodatni2 (String donjiTextDodatni2){
            if (donjiTextDodatni2 != null){
                layoutForDonjiDodatni2.setVisibility(View.VISIBLE);
                TextView donji_dodatni_dva= (TextView) mView.findViewById(R.id.donji_dodatni_text2);
                donji_dodatni_dva.setText(donjiTextDodatni2);
            }else {
                layoutForDonjiDodatni2.setVisibility(View.GONE);
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
