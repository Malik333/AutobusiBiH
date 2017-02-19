package com.alenmalik.autobusibih;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korisnik on 18/02/2017.
 */

public class StanicaTab extends Fragment {

    ListView staniceLista;
    ArrayList<String> lista;
    ArrayAdapter adapter;
    ProgressDialog dialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stanica_city_traffic, container, false);

        staniceLista = (ListView) rootView.findViewById(R.id.list_busState);
        lista = new ArrayList<String>();
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, lista);
        staniceLista.setAdapter(adapter);

        dialog = new ProgressDialog(getContext());

        ParseQuery<ParseObject> query = ParseQuery.getQuery("BusAddress");
        query.addAscendingOrder("CityName");
        dialog.getProgress();
        dialog.setMessage("Searching...");
        dialog.show();

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {


                    lista.clear();
                    for (ParseObject object : list) {

                        lista.add(String.valueOf(object.get("CityName")));

                    }

                    adapter.notifyDataSetChanged();
                    dialog.dismiss();

                }
            }
        });

        return rootView;


    }
}
