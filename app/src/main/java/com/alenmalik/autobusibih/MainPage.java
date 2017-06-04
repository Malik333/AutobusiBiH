package com.alenmalik.autobusibih;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Parcelable;
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
import com.google.android.gms.awareness.snapshot.TimeIntervalsResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MainPage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView reklameList;
    Button probabuton;

    DatabaseReference mReference;

    private final String KEY_RECYCLER_STATE = "recycler_state";
    private static Bundle mBundleRecyclerViewState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        reklameList = (RecyclerView) findViewById(R.id.autobusi_recycler_view_reklame);
        reklameList.setHasFixedSize(false);LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);
        reklameList.setLayoutManager(linearLayoutManager);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Boolean isfirsttime = getSharedPreferences("PREFERENCES", MODE_PRIVATE).getBoolean("isfirsttiem", true);
        if (isfirsttime) {
            drawer.openDrawer(Gravity.LEFT);
            getSharedPreferences("PREFERENCES", MODE_PRIVATE).edit().putBoolean("isfirsttiem", false).commit();
        }

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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
                viewHolder.setAdresa(model.getAdresa());
                viewHolder.setH2(model.getH2());
                viewHolder.setH3(model.getH3());
                viewHolder.setH4(model.getH4());
                viewHolder.setMainObavezni(model.getMainObavezni());
                viewHolder.setMainH2(model.getMainH2());
                viewHolder.setMainH3(model.getMainH3());
                viewHolder.setSlika(getApplicationContext(), model.getSlika());
                viewHolder.setDonjiObavezni(model.getDonjiObavezni());
                viewHolder.setDonjiNaglasava(model.getDonjiNaglasava());
                viewHolder.setDonjiDodatni(model.getDonjiDodatni());
                viewHolder.setDonjiDodatni2(model.getDonjiDodatni2());
                viewHolder.setLgc(model.getLgc());
                viewHolder.setLdc(model.getLdc());
                viewHolder.setHmtc(model.getHmtc());
                viewHolder.setDvtc(model.getDvtc());
                viewHolder.setPhone(model.getPhone());
                viewHolder.setEmail(model.getEmail());
                viewHolder.setFacebook(model.getFacebook());
                viewHolder.setDnlc(model.getDnlc());
                viewHolder.setDntc(model.getDntc());
                viewHolder.setDdtx(model.getDdtx());
                viewHolder.setIlc(model.getIlc());
                viewHolder.setViber(model.getViber());
                viewHolder.setWifi(model.getWifi());
                viewHolder.setIt(model.getIt());
                viewHolder.setItc(model.getItc());
                viewHolder.setPtc(model.getPtc());
                viewHolder.setHgct(model.getHgct());

                viewHolder.slika_image_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent openFullScreen = new Intent(MainPage.this, FullScreenImage.class);
                        String tag = (String) viewHolder.slika_image_view.getTag();
                        openFullScreen.putExtra("imageURL", tag);
                        startActivity(openFullScreen);
                    }
                });

            }
        };
        reklameList.setAdapter(firebaseRecyclerAdapter);


    }

    @Override
    protected void onStart() {

        super.onStart();

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
        RelativeLayout gornjiLayoutHeaderColor;
        RelativeLayout gornjiLayoutMainColor;
        RelativeLayout imePrijevoznikaColor;
        RelativeLayout donjiLayoutColor;
        TextView mainObavezni;
        TextView mainHdrugi;
        TextView mainHtreci;
        TextView donjiVelikiText;
        ImageView slika_image_view;
        TextView donjiNaglasavaBoja;
        TextView donjiDodatniBoja;
        TextView donjiDodatniBojaDrugi;
        RelativeLayout informacijeLayoutColor;
        ImageView phoneImage;
        ImageView emailImage;
        ImageView fbImage;
        TextView infotextColor;
        TextView prijevoznikTextColor;
        TextView gornjitextColor1;
        TextView gornjitextColor2;
        TextView gornjitextColor3;
        TextView gornjitextColor4;


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
            gornjiLayoutHeaderColor = (RelativeLayout) mView.findViewById(R.id.container_for_header_texts);
            gornjiLayoutMainColor = (RelativeLayout) mView.findViewById(R.id.container_for_header_main_texts);
            imePrijevoznikaColor = (RelativeLayout) mView.findViewById(R.id.layout_for_ime_prijevoznik);
            donjiLayoutColor = (RelativeLayout) mView.findViewById(R.id.container_for_bottom_texts);
            mainObavezni = (TextView) mView.findViewById(R.id.main_text_header);
            mainHdrugi = (TextView) mView.findViewById(R.id.main_text_header2);
            mainHtreci = (TextView) mView.findViewById(R.id.main_text_header3);
            donjiVelikiText = (TextView) mView.findViewById(R.id.donji_text_obavezni);
            slika_image_view = (ImageView) mView.findViewById(R.id.imageForReklame);
            donjiNaglasavaBoja = (TextView) mView.findViewById(R.id.text_koji_naglasava);
            donjiDodatniBoja = (TextView) mView.findViewById(R.id.donji_dodatni_text);
            donjiDodatniBojaDrugi = (TextView) mView.findViewById(R.id.donji_dodatni_text2);
            informacijeLayoutColor = (RelativeLayout) mView.findViewById(R.id.layout_za_informacije);
            phoneImage = (ImageView) mView.findViewById(R.id.phoneImage);
            emailImage = (ImageView) mView.findViewById(R.id.emailImage);
            fbImage = (ImageView) mView.findViewById(R.id.facebookImage);
            infotextColor = (TextView) mView.findViewById(R.id.text_dodatne_info);
            prijevoznikTextColor = (TextView) mView.findViewById(R.id.prijevoznik_ime);
            gornjitextColor1 = (TextView) mView.findViewById(R.id.grad_adresa_info);
           gornjitextColor2 = (TextView) mView.findViewById(R.id.header_dodatni_text);
            gornjitextColor3 = (TextView) mView.findViewById(R.id.header_dodatni_text2);
            gornjitextColor4 = (TextView) mView.findViewById(R.id.header_dodatni_text3);


        }

        public void setPrijevoznik(String prijevoznik) {
            if (prijevoznik != null) {
                TextView probaPrijevoznik = (TextView) mView.findViewById(R.id.prijevoznik_ime);
                probaPrijevoznik.setText(prijevoznik);
            }
        }


        public void setAdresa(String adresa) {

            if (adresa != null) {
                layoutForObavezni.setVisibility(View.VISIBLE);
                TextView obavezni_text_header = (TextView) mView.findViewById(R.id.grad_adresa_info);
                obavezni_text_header.setText(adresa);

            } else {
                layoutForObavezni.setVisibility(View.GONE);
            }

        }

        public void setH2(String h2) {
            if (h2 != null) {
                layoutForDodatni1.setVisibility(View.VISIBLE);
                TextView heaader_text_drugi = (TextView) mView.findViewById(R.id.header_dodatni_text);
                heaader_text_drugi.setText(h2);
            } else {
                layoutForDodatni1.setVisibility(View.GONE);
            }

        }

        public void setH3(String h3) {
            if (h3 != null) {
                layoutForDodatni2.setVisibility(View.VISIBLE);
                TextView heaader_text_treci = (TextView) mView.findViewById(R.id.header_dodatni_text2);
                heaader_text_treci.setText(h3);
            } else {
                layoutForDodatni2.setVisibility(View.GONE);
            }

        }

        public void setH4(String h4) {
            if (h4 != null) {
                layoutForDodatni3.setVisibility(View.VISIBLE);
                TextView heaader_text_cetvrti = (TextView) mView.findViewById(R.id.header_dodatni_text3);
                heaader_text_cetvrti.setText(h4);
            } else {
                layoutForDodatni3.setVisibility(View.GONE);
            }

        }

        public void setMainObavezni(String mainObavezni) {

            if (mainObavezni != null) {
                layoutForMainObavezni.setVisibility(View.VISIBLE);
                TextView main_obavezni_header = (TextView) mView.findViewById(R.id.main_text_header);
                main_obavezni_header.setText(mainObavezni);


            } else {
                layoutForMainObavezni.setVisibility(View.GONE);
            }

        }

        public void setMainH2(String mainH2) {


            if (mainH2 != null) {
                layoutForMainDodatni1.setVisibility(View.VISIBLE);
                TextView main_dodatni_header = (TextView) mView.findViewById(R.id.main_text_header2);
                main_dodatni_header.setText(mainH2);


            } else {
                layoutForMainDodatni1.setVisibility(View.GONE);
            }
        }


        public void setMainH3(String mainH3) {


            if (mainH3 != null) {
                layoutForMainDodatni2.setVisibility(View.VISIBLE);
                TextView main_dodatni2_header = (TextView) mView.findViewById(R.id.main_text_header3);
                main_dodatni2_header.setText(mainH3);


            } else {
                layoutForMainDodatni2.setVisibility(View.GONE);
            }
        }

        public void setSlika(Context ctx, String slika) {
            if (slika != null) {
                layoutForSlika.setVisibility(View.VISIBLE);
                Picasso.with(ctx).load(slika).into(slika_image_view);
                slika_image_view.setTag(slika);

            } else {
                layoutForSlika.setVisibility(View.GONE);
            }
        }

        public void setDonjiObavezni(String donjiObavezni) {
            if (donjiObavezni != null) {
                layoutForDonjiObavezni.setVisibility(View.VISIBLE);
                TextView donji_obavezni_textview = (TextView) mView.findViewById(R.id.donji_text_obavezni);
                donji_obavezni_textview.setText(donjiObavezni);
            } else {
                layoutForDonjiObavezni.setVisibility(View.GONE);
            }

        }


        public void setDonjiNaglasava(String donjiNaglasava) {
            if (donjiNaglasava != null) {
                layoutForDonjiNaglasava.setVisibility(View.VISIBLE);
                TextView naglasava = (TextView) mView.findViewById(R.id.text_koji_naglasava);
                naglasava.setText(donjiNaglasava);
            } else {
                layoutForDonjiNaglasava.setVisibility(View.GONE);
            }

        }

        public void setDonjiDodatni(String donjiDodatni) {
            if (donjiDodatni != null) {
                layoutForDonjiDodatni.setVisibility(View.VISIBLE);
                TextView donji_dodatni = (TextView) mView.findViewById(R.id.donji_dodatni_text);
                donji_dodatni.setText(donjiDodatni);
            } else {
                layoutForDonjiDodatni.setVisibility(View.GONE);
            }

        }

        public void setDonjiDodatni2(String donjiDodatni2) {
            if (donjiDodatni2 != null) {
                layoutForDonjiDodatni2.setVisibility(View.VISIBLE);
                TextView donji_dodatni_dva = (TextView) mView.findViewById(R.id.donji_dodatni_text2);
                donji_dodatni_dva.setText(donjiDodatni2);
            } else {
                layoutForDonjiDodatni2.setVisibility(View.GONE);
            }

        }

        public void setLgc(String lgc) {

            if (lgc != null) {

                gornjiLayoutHeaderColor.setBackgroundColor(Color.parseColor(lgc));
                gornjiLayoutMainColor.setBackgroundColor(Color.parseColor(lgc));
                imePrijevoznikaColor.setBackgroundColor(Color.parseColor(lgc));
            }

        }

        public void setLdc(String ldc) {
            if (ldc != null) {

                donjiLayoutColor.setBackgroundColor(Color.parseColor(ldc));
            }
        }

        public void setHmtc(String hmtc) {

            if (hmtc != null) {
                mainObavezni.setTextColor(Color.parseColor(hmtc));
                mainHdrugi.setTextColor(Color.parseColor(hmtc));
                mainHtreci.setTextColor(Color.parseColor(hmtc));

            }
        }

        public void setDvtc(String dvtc) {
            if (dvtc != null) {

                donjiVelikiText.setTextColor(Color.parseColor(dvtc));
            }

        }



        public void setPhone(String phone) {
            TextView phoneNumber = (TextView) mView.findViewById(R.id.phoneNumber);
            phoneNumber.setText(phone);
        }



        public void setEmail(String email) {
            TextView emailtext = (TextView) mView.findViewById(R.id.email);
            emailtext.setText(email);
        }

        public void setFacebook(String facebook) {
            TextView facebooktext = (TextView) mView.findViewById(R.id.facebook);
            facebooktext.setText(facebook);
        }

        public void setDnlc(String dnlc) {

            if (dnlc != null) {
                layoutForDonjiNaglasava.setBackgroundColor(Color.parseColor(dnlc));

            }

        }

        public void setDntc(String dntc) {
            if (dntc != null) {
                donjiNaglasavaBoja.setTextColor(Color.parseColor(dntc));
            }
        }

        public void setDdtx(String ddtx) {
            if (ddtx != null) {
                donjiDodatniBoja.setTextColor(Color.parseColor(ddtx));
                donjiDodatniBojaDrugi.setTextColor(Color.parseColor(ddtx));
            }
        }

      public void setViber(String viber){
            if (viber != null){
               TextView viberText = (TextView) mView.findViewById(R.id.viber);
                viberText.setText(viber);
            }
        }

        public void setWifi(String wifi){
            if (wifi != null){
               TextView wifiText = (TextView) mView.findViewById(R.id.wiffi);
                wifiText.setText(wifi);
            }
        }


        public void setIt(String it){
            if (it != null){
                TextView infoText = (TextView) mView.findViewById(R.id.text_dodatne_info);
                infoText.setText(it);
            }
        }


        public void setItc(String itc){
            if (itc != null){
               infotextColor.setTextColor(Color.parseColor(itc));
            }
        }


        public void setPtc(String ptc){
            if (ptc != null){
               prijevoznikTextColor.setTextColor(Color.parseColor(ptc));
            }
        }


        public void setHgct(String hgct){
            if (hgct != null){
                gornjitextColor1.setTextColor(Color.parseColor(hgct));
                gornjitextColor2.setTextColor(Color.parseColor(hgct));
                gornjitextColor3.setTextColor(Color.parseColor(hgct));
                gornjitextColor4.setTextColor(Color.parseColor(hgct));
            }
        }


   public void setIlc (String ilc){
       if (ilc != null){
           informacijeLayoutColor.setBackgroundColor(Color.parseColor(ilc));
       }
   }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBundleRecyclerViewState != null) {
            Parcelable listState = mBundleRecyclerViewState.getParcelable(KEY_RECYCLER_STATE);
            reklameList.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        mBundleRecyclerViewState = new Bundle();
        Parcelable listState = reklameList.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable(KEY_RECYCLER_STATE, listState);

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


    public void changeColor() {

    }

}
