package com.alenmalik.autobusibih;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Vibrator;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView aboutApplication, contactInfo, cityTraffic, internationalTraffic, busTransports;
    TextView autobusi;

    ViewPropertyAnimator aboutAnim;
    ViewPropertyAnimator contactAnim;
    ViewPropertyAnimator cityAnim;
    ViewPropertyAnimator interAnim;
    ViewPropertyAnimator busAnim;
    Vibrator vibrator;

    RelativeLayout activtiy_main;

    float parentCenterX;
    float parentCenterY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutApplication = (ImageView) findViewById(R.id.about);
        contactInfo = (ImageView) findViewById(R.id.contact);
        cityTraffic = (ImageView) findViewById(R.id.cityTraffic);
        internationalTraffic = (ImageView) findViewById(R.id.international);
        busTransports = (ImageView) findViewById(R.id.transport);
        autobusi = (TextView) findViewById(R.id.textView);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        aboutApplication.setOnClickListener(this);
        contactInfo.setOnClickListener(this);
        cityTraffic.setOnClickListener(this);
        internationalTraffic.setOnClickListener(this);
        busTransports.setOnClickListener(this);

        activtiy_main = (RelativeLayout) findViewById(R.id.activity_main);

        parentCenterX = activtiy_main.getX() + activtiy_main.getWidth()/2;
        parentCenterY = activtiy_main.getY() + activtiy_main.getHeight()/2;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.about) {
            vibrator.vibrate(100);

            aboutAnim = aboutApplication.animate().translationX(parentCenterX - aboutApplication.getWidth()/2).translationY(parentCenterY + aboutApplication.getHeight()).scaleX(2).scaleY(2).setDuration(500);
            aboutAnim.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                    cityTraffic.setVisibility(View.INVISIBLE);
                    internationalTraffic.setVisibility(View.INVISIBLE);
                    contactInfo.setVisibility(View.INVISIBLE);
                    busTransports.setVisibility(View.INVISIBLE);
                    autobusi.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    Intent aboutIntent = new Intent(MainActivity.this, AboutApplication.class);
                    startActivity(aboutIntent);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });


        } else if (view.getId() == R.id.contact) {
            vibrator.vibrate(100);

            contactAnim = contactInfo.animate().translationX(parentCenterX - contactInfo.getWidth()/2).translationY(parentCenterY + contactInfo.getHeight()/2).scaleX(2).scaleY(2).setDuration(500);
            contactAnim.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                    cityTraffic.setVisibility(View.INVISIBLE);
                    aboutApplication.setVisibility(View.INVISIBLE);
                    internationalTraffic.setVisibility(View.INVISIBLE);
                    busTransports.setVisibility(View.INVISIBLE);
                    autobusi.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    Intent contactIntent = new Intent(MainActivity.this, ContactInfo.class);
                    startActivity(contactIntent);

                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

        } else if (view.getId() == R.id.cityTraffic) {
            vibrator.vibrate(100);
            cityAnim = cityTraffic.animate().translationX(parentCenterX - cityTraffic.getWidth()/1.7f).scaleX(2).scaleY(2).setDuration(500);
            cityAnim.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                    contactInfo.setVisibility(View.INVISIBLE);
                    aboutApplication.setVisibility(View.INVISIBLE);
                    internationalTraffic.setVisibility(View.INVISIBLE);
                    busTransports.setVisibility(View.INVISIBLE);
                    autobusi.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    Intent cityTrafficIntent = new Intent(MainActivity.this, CityTraffic.class);
                    startActivity(cityTrafficIntent);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });


        } else if (view.getId() == R.id.international) {
            vibrator.vibrate(100);
            interAnim = internationalTraffic.animate().translationX(parentCenterX - internationalTraffic.getWidth()/2).translationY(parentCenterY - internationalTraffic.getHeight()/2).scaleX(2).scaleY(2).setDuration(500);
            interAnim.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                    cityTraffic.setVisibility(View.INVISIBLE);
                    aboutApplication.setVisibility(View.INVISIBLE);
                    contactInfo.setVisibility(View.INVISIBLE);
                    busTransports.setVisibility(View.INVISIBLE);
                    autobusi.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    Intent internacionalTrafficIntent = new Intent(MainActivity.this, InternacionalTraffic.class);
                    startActivity(internacionalTrafficIntent);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });


        } else if (view.getId() == R.id.transport) {
            vibrator.vibrate(100);

            busAnim = busTransports.animate().translationX(parentCenterX - busTransports.getWidth()/2).translationY(parentCenterY - busTransports.getHeight()*1.5f).scaleX(2).scaleY(2).setDuration(500);
            busAnim.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    cityTraffic.setVisibility(View.INVISIBLE);
                    aboutApplication.setVisibility(View.INVISIBLE);
                    internationalTraffic.setVisibility(View.INVISIBLE);
                    contactInfo.setVisibility(View.INVISIBLE);
                    autobusi.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animator) {

                    Intent busTransportIntent = new Intent(MainActivity.this, BusTransport.class);
                    startActivity(busTransportIntent);
                }
                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finishAffinity();
    }
}
