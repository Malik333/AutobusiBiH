package com.alenmalik.autobusibih;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
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

public class CityTraffic extends AppCompatActivity implements View.OnClickListener{
    SearchableSpinner fromCitySpinner;
    SearchableSpinner toCitySpinner;
    SearchableSpinner daysSpinner;
    SearchableSpinner prijevoznikSpinner;
    ArrayList<String> fromCityList;
    ArrayList<String> toCityList;
    ArrayList<String> daysList;
    ArrayList<String> TransportList;
    ArrayAdapter<String> fromCityAdapter;
    ArrayAdapter<String> toCityAdapter;
    ArrayAdapter<String> daysAdapter;
    ArrayAdapter<String> TransportAdapter;
    HashSet<String> hashSet = new HashSet<String>();
    HashSet<String> hashSet2 = new HashSet<String>();
    HashSet<String> hashSet3 = new HashSet<>();
    HashSet<String> hashSet4 = new HashSet<>();
    static String odGrada;
    String doGrada;
    String dan;
    String prijevoznik;
    TextView prijevoznikTextView;
    Button ispisBtn;
    HorizontalScrollView infoLayout;
    RecyclerView details;
    List<MedjugradskiIspisModel> detailsList;
    MedjugradskiIspisAdapter adapter;
    ImageView goBack;

    static double busLat, busLng;

    static boolean stanicaCity = false;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_traffic);
        fromCitySpinner = (SearchableSpinner) findViewById(R.id.spinnerOdGrada);
        toCitySpinner = (SearchableSpinner) findViewById(R.id.spinnerDoGrada);
        prijevoznikSpinner = (SearchableSpinner) findViewById(R.id.spinnerPrijevoznik);
        daysSpinner = (SearchableSpinner) findViewById(R.id.spinnerDan);
        fromCityList = new ArrayList<>();
        toCityList = new ArrayList<>();
        daysList = new ArrayList<>();
        TransportList = new ArrayList<>();
        details = (RecyclerView) findViewById(R.id.ispis_medjugradski_rec);
        details.setLayoutManager(new LinearLayoutManager(this));
        detailsList = new ArrayList<>();
        goBack = (ImageView) findViewById(R.id.goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CityTraffic.this,MainPage.class);
                startActivity(intent);
            }
        });


        prijevoznikTextView = (TextView) findViewById(R.id.prijevoznikIspis);
        ispisBtn = (Button) findViewById(R.id.ispis);

       infoLayout = (HorizontalScrollView) findViewById(R.id.horizontal_layout_scroll);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        ispisBtn.setOnClickListener(this);


        fromCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(100);

                odGrada = (String) adapterView.getItemAtPosition(i);

                Log.i("toCity", odGrada);
                hashSet2.clear();
                toCityList.clear();
                toCity(odGrada);
                TransportList.clear();

                detailsList.clear();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });
        fromCitySpinner.setTitle("IZABERI OD GRADA");

        fromCitySpinner.setPositiveButton("OK");



        toCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(100);
                doGrada = String.valueOf(adapterView.getItemAtPosition(i));

                spinnerPrijevoznik(odGrada, doGrada);
                TransportList.clear();
                detailsList.clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        toCitySpinner.setTitle("IZABERI DO GRADA");
        toCitySpinner.setPositiveButton("OK");


        daysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(100);
                dan = (String) adapterView.getItemAtPosition(i);
                Log.i("dan", dan);
                detailsList.clear();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        daysSpinner.setTitle("IZABERI DAN");
        daysSpinner.setPositiveButton("OK");


        prijevoznikSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(100);
                prijevoznik = (String) adapterView.getItemAtPosition(i);
                detailsList.clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        prijevoznikSpinner.setTitle("IZABERI PRIJEVOZNIKA");
        prijevoznikSpinner.setPositiveButton("OK");
        fromCity();
        chooseDay();





    }

    public void ispis(){

        String prijevoznikSelected = prijevoznik;

        if (prijevoznikSelected.equals("Svi")){

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Relacija");
            query.whereEqualTo("odGrada", odGrada);
            query.whereEqualTo("doGrada", doGrada);
            query.whereEqualTo("Dan", dan);
            query.setLimit(10000);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {

                       for ( ParseObject object : list) {
                           final MedjugradskiIspisModel item = new MedjugradskiIspisModel();
                           item.vrijemePolaska = String.valueOf(object.get("Satnica"));
                           item.dan = String.valueOf(object.get("Dan"));
                           item.duzinaPuta = String.valueOf(object.get("DuzinaPuta"));
                           item.linija = String.valueOf(object.get("Linija"));
                           item.cijena = String.valueOf(object.get("Cijena"));
                           item.relacija = String.valueOf(object.get("odGrada"))+" - "+ String.valueOf(object.get("doGrada"));
                           item.prijevoznik = String.valueOf(object.get("Prijevoznik"));



                           ParseQuery<ParseObject> stanicaquery = ParseQuery.getQuery("BusAddress");
                           stanicaquery.whereEqualTo("CityName", odGrada);
                           stanicaquery.findInBackground(new FindCallback<ParseObject>() {
                               @Override
                               public void done(List<ParseObject> list, ParseException e) {
                                   if (e == null) {
                                       for (ParseObject object1 : list) {

                                           item.stanica = String.valueOf(object1.get("Address"));
                                           detailsList.add(item);

                                       }
                                   }

                               }
                           });
                       }
                    }

                    adapter = new MedjugradskiIspisAdapter(detailsList, CityTraffic.this);
                    details.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            });


        } else {


            ParseQuery<ParseObject> query = ParseQuery.getQuery("Relacija");
            query.whereEqualTo("odGrada", odGrada);
            query.whereEqualTo("doGrada", doGrada);
            query.whereEqualTo("Dan", dan);
            query.whereEqualTo("Prijevoznik", prijevoznik);

            query.setLimit(10000);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {

                        for ( ParseObject object : list) {
                            final MedjugradskiIspisModel item = new MedjugradskiIspisModel();
                            item.vrijemePolaska = String.valueOf(object.get("Satnica"));
                            item.dan = String.valueOf(object.get("Dan"));
                            item.duzinaPuta = String.valueOf(object.get("DuzinaPuta"));
                            item.linija = String.valueOf(object.get("Linija"));
                            item.cijena = String.valueOf(object.get("Cijena"));
                            item.relacija = String.valueOf(object.get("odGrada"))+" - "+ String.valueOf(object.get("doGrada"));
                            item.prijevoznik = String.valueOf(object.get("Prijevoznik"));


                            ParseQuery<ParseObject> stanicaquery = ParseQuery.getQuery("BusAddress");
                            stanicaquery.whereEqualTo("CityName", odGrada);
                            stanicaquery.findInBackground(new FindCallback<ParseObject>() {
                                @Override
                                public void done(List<ParseObject> list, ParseException e) {
                                    if (e == null) {
                                        for (ParseObject object1 : list) {
                                            item.stanica = String.valueOf(object1.get("Address"));
                                            detailsList.add(item);
                                        }
                                    }
                                    adapter = new MedjugradskiIspisAdapter(detailsList, CityTraffic.this);
                                    details.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                }
            });
        }
    }
    public void fromCity() {

        ParseQuery<ParseObject> fromCityQuery = new ParseQuery<ParseObject>("Relacija");
        fromCityQuery.setLimit(10000);
        fromCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            fromCityList.add(String.valueOf(object.get("odGrada")));

                        }
                        hashSet.addAll(fromCityList);
                        fromCityList.clear();
                        fromCityList.addAll(hashSet);
                        fromCityAdapter = new ArrayAdapter<String>(CityTraffic.this, R.layout.my_spinner,fromCityList);
                        fromCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);



                    fromCitySpinner.setAdapter(fromCityAdapter);
                        Collections.sort(fromCityList);
                        fromCityAdapter.notifyDataSetChanged();


                    }


                }
            }
        });


    }


    public void toCity(String name) {

        ParseQuery<ParseObject> toCityQuery = new ParseQuery<ParseObject>("Relacija");
        toCityQuery.whereEqualTo("odGrada", name);
        toCityQuery.setLimit(10000);
        toCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            toCityList.add(String.valueOf(object.get("doGrada")));

                        }
                        hashSet2.addAll(toCityList);
                        toCityList.clear();
                        toCityList.addAll(hashSet2);
                        toCityAdapter = new ArrayAdapter<String>(CityTraffic.this, R.layout.my_spinner, toCityList);
                        toCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        Collections.sort(toCityList);
                        toCitySpinner.setAdapter(toCityAdapter);

                        toCityAdapter.notifyDataSetChanged();

                    }

                }
            }
        });
    }

    public void chooseDay() {


        ParseQuery<ParseObject> chooseDayQuery = new ParseQuery<ParseObject>("Relacija");
        chooseDayQuery.addAscendingOrder("createdAt");
        chooseDayQuery.setLimit(10000);
        chooseDayQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            daysList.add(String.valueOf(object.get("Dan")));

                        }
                        hashSet3.addAll(daysList);
                        daysList.clear();
                        daysList.addAll(hashSet3);
                        daysAdapter = new ArrayAdapter<String>(CityTraffic.this, R.layout.my_spinner, daysList);


                        daysSpinner.setAdapter(daysAdapter);

                        daysAdapter.notifyDataSetChanged();


                    }


                }
            }
        });

    }

    public void spinnerPrijevoznik(String name, String toCity){
        ParseQuery<ParseObject> prijevoznikQuery = new ParseQuery<ParseObject>("Relacija");
        prijevoznikQuery.setLimit(10000);
        prijevoznikQuery.whereEqualTo("odGrada", name);
        prijevoznikQuery.whereEqualTo("doGrada", toCity);
        prijevoznikQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {

                            TransportList.add(String.valueOf(object.get("Prijevoznik")));

                        }

                        hashSet4.addAll(TransportList);
                        TransportList.clear();
                        TransportList.add("Svi");
                        TransportList.set(0, "Svi");
                        TransportList.addAll(hashSet4);
                        TransportAdapter = new ArrayAdapter<String>(CityTraffic.this, R.layout.my_spinner, TransportList);
                        TransportAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        prijevoznikSpinner.setAdapter(TransportAdapter);
                        hashSet4.clear();
                        TransportAdapter.notifyDataSetChanged();


                    }


                }
            }
        });


    }

        @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(CityTraffic.this, MainPage.class));
    }


@Override
    public void onClick(View view) {
        if (view.getId() == R.id.ispis){

            if (!TextUtils.isEmpty(odGrada) && !TextUtils.isEmpty(doGrada) && !TextUtils.isEmpty(dan) && !TextUtils.isEmpty(prijevoznik)) {
                vibrator.vibrate(100);
                ispis();
            }


        }
        if (view.getId() == R.id.prijevoznikIspis){
            vibrator.vibrate(100);
            Intent intent = new Intent(CityTraffic.this,PopUpActivitiy.class);
            startActivity(intent);
        }
    }






}
