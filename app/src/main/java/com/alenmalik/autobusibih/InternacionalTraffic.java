package com.alenmalik.autobusibih;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class InternacionalTraffic extends AppCompatActivity implements View.OnClickListener {
    Spinner stateSpinner;
    Spinner daySpinner;
    Spinner fromCitySpinner;
    Spinner toCitySpinner;
    ScrollView infoScroll;

    ArrayList<String> stateList;
    ArrayList<String> daysList;
    ArrayList<String> fromCityList;
    ArrayList<String> toCityList;

    HashSet<String> hashSet = new HashSet<String>();
    HashSet<String> hashSet2 = new HashSet<String>();
    HashSet<String> hashSet3 = new HashSet<>();
    HashSet<String> hashSet4 = new HashSet<>();

    ArrayAdapter<String> stateAdapter;
    ArrayAdapter<String> fromCityAdapter;
    ArrayAdapter<String> toCityAdapter;
    ArrayAdapter<String> dayAdapter;

    Button informacije;
    TextView relacijaTextView;
    TextView vrijemepolaskaTextView;
    TextView danTextView;
    TextView duzinaputTextView;
    TextView linijaTextView;
    TextView cijenaTextView;
    TextView prijevoznikTextView;


    String country, citySelected, toCity, daySelect;
    Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internacional_traffic);

        stateSpinner = (Spinner) findViewById(R.id.izaberDrzavuSpinner);
        daySpinner = (Spinner) findViewById(R.id.internationalSpinnerDan);
        fromCitySpinner = (Spinner) findViewById(R.id.internationalSpinnerOdGrada);
        toCitySpinner = (Spinner) findViewById(R.id.internationalSpinnerDoGrada);
        infoScroll = (ScrollView) findViewById(R.id.infoScroll);

        informacije = (Button) findViewById(R.id.internationalInfoBtn);
        relacijaTextView = (TextView) findViewById(R.id.internationalRelacija);
        vrijemepolaskaTextView = (TextView) findViewById(R.id.internationalVrijemePolaska);
        danTextView = (TextView) findViewById(R.id.internationalDan);
        duzinaputTextView = (TextView) findViewById(R.id.internationalDuzinaPuta);
        linijaTextView = (TextView) findViewById(R.id.internationalLinija);
        cijenaTextView = (TextView) findViewById(R.id.internationalCijena);
        prijevoznikTextView = (TextView) findViewById(R.id.internationalPrijevoznik);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        stateList = new ArrayList<>();
        toCityList = new ArrayList<>();
        fromCityList = new ArrayList<>();
        daysList = new ArrayList<>();

        fromCityAdapter = new ArrayAdapter<String>(InternacionalTraffic.this, android.R.layout.simple_spinner_dropdown_item, fromCityList);
        fromCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        toCityAdapter = new ArrayAdapter<String>(InternacionalTraffic.this, android.R.layout.simple_spinner_dropdown_item, toCityList);
        toCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        informacije.setOnClickListener(this);


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
                // chooseDayInternationalTraffic(citySelected, toCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                vibrator.vibrate(100);
                daySelect = String.valueOf(adapterView.getItemAtPosition(i));
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
                        vrijemepolaskaTextView.setText(String.valueOf(object.get("Satnica")));
                        duzinaputTextView.setText(String.valueOf(object.get("DuzinaPuta")));
                        linijaTextView.setText(String.valueOf(object.get("Linija")));
                        cijenaTextView.setText(String.valueOf(object.get("Cijena")));
                        prijevoznikTextView.setText(String.valueOf(object.get("Prijevoznik")));
                    }
                }

                relacijaTextView.setText(citySelected + " - " + toCity);
                danTextView.setText(daySelect);
            }
        });
    }

    public void chooseCountry() {
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
                        stateAdapter = new ArrayAdapter<String>(InternacionalTraffic.this, android.R.layout.simple_spinner_dropdown_item, stateList);
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

                    }

                }
            }
        });
    }

    public void chooseDayInternationalTraffic(String fromCiy, String toCity) {

        ParseQuery<ParseObject> chooseDayQuery = new ParseQuery<ParseObject>("MedjunarodneRelacije");
        chooseDayQuery.addAscendingOrder("createdAt");
        chooseDayQuery.setLimit(10000);
        chooseDayQuery.whereEqualTo("odGrada", fromCiy);
        chooseDayQuery.whereEqualTo("doGrada", toCity);
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
                        dayAdapter = new ArrayAdapter<String>(InternacionalTraffic.this, android.R.layout.simple_spinner_dropdown_item, daysList);


                        daySpinner.setAdapter(dayAdapter);

                        dayAdapter.notifyDataSetChanged();


                    }


                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(InternacionalTraffic.this, MainActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.internationalInfoBtn) {
            vibrator.vibrate(100);
            infoScroll.setVisibility(View.VISIBLE);
            ispisInfoInternacional();
        }
    }
}
