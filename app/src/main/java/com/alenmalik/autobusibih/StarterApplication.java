package com.alenmalik.autobusibih;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by korisnik on 17/02/2017.
 */

public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("8Tcjpkpz8DG6jVyG8nlI0veWySCeTkJamXha97U1")
                .clientKey("JfAt9jhLWz3cU1sXNgTjSoSGaEIC8neD33DXpdM1")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
