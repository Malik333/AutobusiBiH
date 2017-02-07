package com.alenmalik.autobusibih;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class PriceInfoActivitiy extends AppCompatActivity {

    TextView dnevna, povratna;
    Intent intent;
    ProgressDialog dialog;

    static String cityFrom;
    static String cityto;

    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_info_activitiy);

        dnevna = (TextView) findViewById(R.id.dnevna_txt);
        povratna = (TextView) findViewById(R.id.povratna_txt);

        mAdView = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        dialog = new ProgressDialog(this);

        intent = getIntent();

        cityFrom = intent.getStringExtra("fromcity");
        cityto = intent.getStringExtra("tocity");
        dialog.setMessage("Please wait");
        dialog.show();
        if (MainActivity.cityActivityActive == true){
            city();
        }else {
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Cijene");
            query.whereEqualTo("fromCity", cityFrom);
            query.whereEqualTo("toCity", cityto);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    if (e == null) {

                        for (ParseObject object : list) {

                            dnevna.setText("U jednom smijeru: " + object.get("jedanSmijer").toString());
                            povratna.setText("Povratna: " + object.get("povratna").toString());

                            dialog.dismiss();
                        }
                    }
                }
            });
        }
    }

    public void city (){
        Intent tt = getIntent();
        String fromCity = tt.getStringExtra("fromCity");
        String toCity = tt.getStringExtra("toCity");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cijene");
        query.whereEqualTo("fromCity", fromCity);
        query.whereEqualTo("toCity", toCity);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null){

                    for (ParseObject object : list){

                        dnevna.setText("U jednom smijeru: " +object.get("jedanSmijer").toString());
                        povratna.setText("Povratna: "+object.get("povratna").toString());

                        dialog.dismiss();
                    }
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    public void onPause() {

        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();

        super.onPause();
        overridePendingTransition(0, 0);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
}
