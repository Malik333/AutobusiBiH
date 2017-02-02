package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class PriceInfoActivitiy extends AppCompatActivity {

    TextView dnevna, povratna;
    Intent intent;

    static String cityFrom;
    static String cityto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_info_activitiy);

        dnevna = (TextView) findViewById(R.id.dnevna_txt);
        povratna = (TextView) findViewById(R.id.povratna_txt);

        intent = getIntent();

        cityFrom = intent.getStringExtra("fromcity");
        cityto = intent.getStringExtra("tocity");

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Cijene");
        query.whereEqualTo("fromCity", cityFrom);
        query.whereEqualTo("toCity", cityto);
        //query.fromLocalDatastore();

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null){

                    for (ParseObject object : list){

                        Log.i("cdnevna:", String.valueOf(object.get("jedanSmijer")));
                        Log.i("cpovratna:", String.valueOf(object.get("povratna")));

                        dnevna.setText("Dnevna: " +object.get("jedanSmijer").toString());
                        povratna.setText("Povratna: "+object.get("povratna").toString());
                    }
                }
            }
        });
    }
}
