package com.alenmalik.autobusibih;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class InternacionalTraffic extends AppCompatActivity implements View.OnClickListener {

    SearchableSpinner stateSpinner;
    SearchableSpinner fromCitySpinner;
    SearchableSpinner toCitySpinner;

    HorizontalScrollView infoScroll;
    ProgressDialog dialog;

    ArrayList<String> stateList;
    ArrayList<String> daysList;
    ArrayList<String> fromCityList;
    ArrayList<String> toCityList;

    HashSet<String> hashSet = new HashSet<String>();
    HashSet<String> hashSet2 = new HashSet<String>();

    HashSet<String> hashSet4 = new HashSet<>();

    ArrayAdapter<String> stateAdapter;
    ArrayAdapter<String> fromCityAdapter;
    ArrayAdapter<String> toCityAdapter;


    RecyclerView details;
    List<MedjunarodniIspisModel> detailsList;
    MedjunarodniIspisAdapter adapter;


    Button informacije;
    TextView relacijaTextView;
    TextView vrijemepolaskaTextView;

    TextView duzinaputTextView;
    TextView linijaTextView;
    TextView cijenaTextView;
    TextView prijevoznikTextView;


    String country, citySelected, toCity;
    Vibrator vibrator;
     ImageView goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internacional_traffic);

        stateSpinner = (SearchableSpinner) findViewById(R.id.izaberDrzavuSpinner);
        fromCitySpinner = (SearchableSpinner) findViewById(R.id.internationalSpinnerOdGrada);
        toCitySpinner = (SearchableSpinner) findViewById(R.id.internationalSpinnerDoGrada);
        infoScroll = (HorizontalScrollView) findViewById(R.id.horizontal_layout_scroll_international);

        informacije = (Button) findViewById(R.id.ispis_international);
        relacijaTextView = (TextView) findViewById(R.id.relacijaIspis_international);
        vrijemepolaskaTextView = (TextView) findViewById(R.id.vrijemePolaskaIspis_international);
        duzinaputTextView = (TextView) findViewById(R.id.duzinaPutaIspis_international);
        linijaTextView = (TextView) findViewById(R.id.linijaIspis_international);
        cijenaTextView = (TextView) findViewById(R.id.cijenaIspis_international);
        prijevoznikTextView = (TextView) findViewById(R.id.prijevoznikIspis_international);
        goBack = (ImageView) findViewById(R.id.backGoArrow);
        details = (RecyclerView) findViewById(R.id.rec_international);
        details.setLayoutManager(new LinearLayoutManager(this));
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        dialog = new ProgressDialog(this,R.style.AppTheme_Dark);
        stateList = new ArrayList<>();
        toCityList = new ArrayList<>();
        fromCityList = new ArrayList<>();
        daysList = new ArrayList<>();
        detailsList = new ArrayList<>();

        fromCityAdapter = new ArrayAdapter<String>(InternacionalTraffic.this, R.layout.my_spinner, fromCityList);
        fromCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        toCityAdapter = new ArrayAdapter<String>(InternacionalTraffic.this, R.layout.my_spinner, toCityList);
        toCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        informacije.setOnClickListener(this);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InternacionalTraffic.this,MainPage.class);
                startActivity(intent);
            }
        });

        stateSpinner.setTitle("IZABERITE DRŽAVU");
        fromCitySpinner.setTitle("IZABERITE OD GRADA");
        toCitySpinner.setTitle("IZABERITE DO GRADA");


        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(100);
                country = (String) adapterView.getItemAtPosition(i);
                fromCityInternacionalTraffic(country);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        fromCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(100);
                citySelected = (String) adapterView.getItemAtPosition(i);
                
                toCityInternationalTraffic(citySelected, country);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        toCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(100);
                toCity = String.valueOf(adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        chooseCountry();

    }

    public void ispisInfoInternacional() {
        ParseQuery<ParseObject> infoQuery = ParseQuery.getQuery("MedjunarodneRelacije");
        infoQuery.whereEqualTo("drzava", country);
        infoQuery.whereEqualTo("odGrada", citySelected);
        infoQuery.whereEqualTo("doGrada", toCity);

        infoQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list) {
                        MedjunarodniIspisModel item = new MedjunarodniIspisModel();
                        item.vrijemePolaska = String.valueOf(object.get("Satnica"));
                        item.duzinaPuta = String.valueOf(object.get("DuzinaPuta"));
                        item.linija = String.valueOf(object.get("Linija"));
                        item.cijena = String.valueOf(object.get("Cijena"));
                        item.relacija = String.valueOf(object.get("odGrada"))+" - "+ String.valueOf(object.get("doGrada"));
                        item.prijevoznik = String.valueOf(object.get("Prijevoznik"));
                        item.dan = String.valueOf(object.get("Dan"));


                        detailsList.add(item);
                        adapter = new MedjunarodniIspisAdapter(detailsList, InternacionalTraffic.this);
                        details.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    public void chooseCountry() {
        dialog.setMessage("Loading...");
        dialog.show();
        ParseQuery<ParseObject> fromCityQuery = new ParseQuery<ParseObject>("MedjunarodneRelacije");
        fromCityQuery.setLimit(10000);
        fromCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            stateList.add(String.valueOf(object.get("drzava")));

                        }
                        hashSet4.addAll(stateList);
                        stateList.clear();
                        stateList.addAll(hashSet4);
                        stateAdapter = new ArrayAdapter<String>(InternacionalTraffic.this, R.layout.my_spinner, stateList);
                        stateAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        Collections.sort(stateList);
                        stateSpinner.setAdapter(stateAdapter);

                        stateAdapter.notifyDataSetChanged();


                    }


                }
            }
        });

    }

    public void fromCityInternacionalTraffic(String countryName) {

        ParseQuery<ParseObject> fromCityQuery = new ParseQuery<ParseObject>("MedjunarodneRelacije");
        fromCityQuery.whereEqualTo("drzava", countryName);
        fromCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        fromCityList.clear();
                        for (ParseObject object : list) {
                            fromCityList.add(String.valueOf(object.get("odGrada")));

                        }
                        hashSet.addAll(fromCityList);
                        fromCityList.clear();
                        fromCityList.addAll(hashSet);
                        hashSet.clear();
                        Collections.sort(fromCityList);
                        fromCitySpinner.setAdapter(fromCityAdapter);

                        fromCityAdapter.notifyDataSetChanged();


                    }


                }
            }
        });
    }


    public void toCityInternationalTraffic(String name, String countryName) {

        ParseQuery<ParseObject> toCityQuery = new ParseQuery<ParseObject>("MedjunarodneRelacije");
        toCityQuery.whereEqualTo("odGrada", name);
        toCityQuery.whereEqualTo("drzava", countryName);
        toCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {
                        toCityList.clear();
                        for (ParseObject object : list) {
                            toCityList.add(String.valueOf(object.get("doGrada")));

                        }
                        hashSet2.addAll(toCityList);
                        toCityList.clear();
                        toCityList.addAll(hashSet2);
                        hashSet2.clear();
                        Collections.sort(toCityList);
                        toCitySpinner.setAdapter(toCityAdapter);

                        toCityAdapter.notifyDataSetChanged();
                        dialog.dismiss();

                    }

                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(InternacionalTraffic.this, MainPage.class));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ispis_international) {
            vibrator.vibrate(100);
            detailsList.clear();
            ispisInfoInternacional();
        }
    }
}
