package com.alenmalik.autobusibih;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdView;

public class AboutApplication extends AppCompatActivity {


    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_application);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AboutApplication.this, MainPage.class));
        finish();
    }
}
