package com.alenmalik.autobusibih;

import android.*;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class BustStateInfo extends AppCompatActivity implements View.OnClickListener {

    TextView name, address, phone;
    static String nameCity;
    Intent inte;
    Button openMap;

    ProgressDialog dialog;

    private AdView mAdView;

    Vibrator vibe;
    Animation anim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bust_state_info);

        mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        name = (TextView) findViewById(R.id.cityName_txt);
        address = (TextView) findViewById(R.id.address_txt);
        phone = (TextView) findViewById(R.id.phone_number);
        dialog = new ProgressDialog(this);
        vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        anim = AnimationUtils.loadAnimation(this, R.anim.anim_click_button);

        openMap = (Button) findViewById(R.id.openMapStateBus);

        openMap.setOnClickListener(this);

        inte = getIntent();

        nameCity = inte.getStringExtra("city");

        name.setText(nameCity);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("BusAddress");
        query.whereEqualTo("CityName", nameCity);
        dialog.setMessage("Searching...");
        dialog.show();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    for (ParseObject object : list) {

                        address.setText(object.get("Address").toString());

                        phone.setText(object.get("PhoneNumber").toString());

                        phone.setOnClickListener(BustStateInfo.this);

                    }

                    dialog.dismiss();

                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.openMapStateBus){
            Intent intent = new Intent(BustStateInfo.this, BusMapAct.class);
            vibe.vibrate(150);
            openMap.startAnimation(anim);
            startActivity(intent);

        }else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:+" + phone.getText().toString().trim()));
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                return;
            }

            startActivity(callIntent);
        }
    }


}
