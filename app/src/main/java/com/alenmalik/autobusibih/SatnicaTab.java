package com.alenmalik.autobusibih;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by korisnik on 18/02/2017.
 */

public class SatnicaTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.satnica_city_traffic, container, false);
        return rootView;
    }
}