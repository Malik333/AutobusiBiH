package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CityTraffic extends AppCompatActivity implements View.OnClickListener{
    Spinner fromCitySpinner;
    Spinner toCitySpinner;
    Spinner daysSpinner;
    ArrayList<String> fromCityList;
    ArrayList<String> toCityList;
    ArrayList<String> daysList;
    ArrayAdapter<String> fromCityAdapter;
    ArrayAdapter<String> toCityAdapter;
    ArrayAdapter<String> daysAdapter;
    HashSet<String> hashSet = new HashSet<String>();
    HashSet<String> hashSet2 = new HashSet<String>();
    HashSet<String> hashSet3 = new HashSet<>();
    TextView relacijaTextView;
    String odGrada;
    String doGrada;
    String dan;
    TextView danTextView;
    TextView duzinaPutaTextView;
    TextView satnicaTextview;
    TextView prijevoznikTextView;
    TextView stanicaTextview;
    TextView linijaTextView;
    TextView cijenaTextView;
    Button ispisBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_traffic);
        fromCitySpinner = (Spinner) findViewById(R.id.spinnerOdGrada);
        toCitySpinner = (Spinner) findViewById(R.id.spinnerDoGrada);
        daysSpinner = (Spinner) findViewById(R.id.spinnerDan);
        fromCityList = new ArrayList<>();
        toCityList = new ArrayList<>();
        daysList = new ArrayList<>();

        relacijaTextView = (TextView) findViewById(R.id.relacijaIspis);
        danTextView = (TextView) findViewById(R.id.danIspis);
        duzinaPutaTextView = (TextView) findViewById(R.id.duzinaPutaIspis);
        prijevoznikTextView = (TextView) findViewById(R.id.prijevoznikIspis);
        ispisBtn = (Button) findViewById(R.id.informacijeBtn);
        satnicaTextview = (TextView) findViewById(R.id.vrijemePolaskaIspis);
        stanicaTextview = (TextView) findViewById(R.id.adresaStaniceIspis);
        linijaTextView = (TextView) findViewById(R.id.linijaIspis);
        cijenaTextView = (TextView) findViewById(R.id.cijenaIspis);

        ispisBtn.setOnClickListener(this);
        fromCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                odGrada = (String) adapterView.getItemAtPosition(i);

                Log.i("toCity", odGrada);
                hashSet2.clear();
                toCityList.clear();
                toCity(odGrada);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });

        toCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                doGrada = String.valueOf(adapterView.getItemAtPosition(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        daysSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dan = (String) adapterView.getItemAtPosition(i);
                Log.i("dan", dan);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        fromCity();
        chooseDay();





    }

    public void ispis(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Relacija");
        query.whereEqualTo("odGrada", odGrada);
        query.whereEqualTo("doGrada", doGrada);
        query.whereEqualTo("Dan", dan);

        query.setLimit(10000);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                            duzinaPutaTextView.setText(String.valueOf(object.get("DuzinaPuta")));
                            prijevoznikTextView.setText(String.valueOf(object.get("Prijevoznik")));
                            satnicaTextview.setText(String.valueOf(object.get("Satnica")));
                            cijenaTextView.setText(String.valueOf(object.get("Cijena")));
                            linijaTextView.setText(String.valueOf(object.get("Linija")));

                        }

                    }
                    relacijaTextView.setText(String.valueOf(odGrada +" - "+ doGrada));
                    danTextView.setText(dan);
                }
            }
        });

        ParseQuery<ParseObject> stanicaquery = ParseQuery.getQuery("BusAddress");
        stanicaquery.whereEqualTo("CityName", odGrada);
        stanicaquery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (ParseObject object : list){
                        stanicaTextview.setText(String.valueOf(object.get("Address")));
                    }
                }
            }
        });
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
                        fromCityAdapter = new ArrayAdapter<String>(CityTraffic.this, android.R.layout.simple_spinner_dropdown_item, fromCityList);
                        fromCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                        fromCitySpinner.setAdapter(fromCityAdapter);

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
                        toCityAdapter = new ArrayAdapter<String>(CityTraffic.this, android.R.layout.simple_spinner_dropdown_item, toCityList);
                        toCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

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
                        daysAdapter = new ArrayAdapter<String>(CityTraffic.this, android.R.layout.simple_spinner_dropdown_item, daysList);


                        daysSpinner.setAdapter(daysAdapter);

                        daysAdapter.notifyDataSetChanged();


                    }


                }
            }
        });

    }

        @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(CityTraffic.this, MainActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.informacijeBtn){

            ispis();

        }
    }




}
