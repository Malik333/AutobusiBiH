package com.alenmalik.autobusibih;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by korisnik on 18/02/2017.
 */

public class RelacijaTab extends Fragment {
    Spinner fromCitySpinner;
    Spinner toCitySpinner;
    ArrayList<String> fromCityList;
    ArrayList<String> toCityList;
    ArrayAdapter<String> fromCityAdapter;
    ArrayAdapter<String> toCityAdapter;
    HashSet<String> hashSet = new HashSet<String>();
    HashSet<String> hashSet2 = new HashSet<String>();
    String toCitiName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = getActivity().getLayoutInflater().inflate(R.layout.relacija_city_traffic, container, false);
        fromCitySpinner = (Spinner) rootView.findViewById(R.id.odGradaSpinner);
        toCitySpinner = (Spinner) rootView.findViewById(R.id.doGradaSpinner);


        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        fromCityList = new ArrayList<>();


       fromCitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               toCitiName = (String) adapterView.getItemAtPosition(i);

               hashSet2.clear();
               toCityList.clear();
               toCity(toCitiName);

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
        fromCity();

        super.onActivityCreated(savedInstanceState);

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
                        fromCityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, fromCityList);
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
                        toCityAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, toCityList);
                        toCityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                        toCitySpinner.setAdapter(toCityAdapter);

                        toCityAdapter.notifyDataSetChanged();

                    }

                }
            }
        });
}
}
