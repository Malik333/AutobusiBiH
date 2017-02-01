package com.alenmalik.autobusibih;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by korisnik on 01/02/2017.
 */

public class PinObject {

    public void pinAllobject(){

        ParseGeoPoint location = new ParseGeoPoint(0, 0);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("CityLocation");

        query.whereNear("Location", location);
        query.whereEqualTo("Name", CityActivity.chooseCityName);
        // query.setLimit(10);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground(list);


                    if (list.size() > 0) {

                        for (ParseObject object : list) {

                        }

                    }

                }
            }
        });

        ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Cities");
        query2.whereEqualTo("fromCity", CityActivity.chooseCityName);
        query2.setLimit(10000);


        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground(list);

                    if (list.size() > 0) {


                        for (ParseObject object : list) {

                        }


                    }

                }
            }
        });

        ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Cities");
        query3.setLimit(10000);

        query3.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                ParseObject.pinAllInBackground(list);
                if (e == null) {
                    if (list.size() > 0) {

                    }
                }

            }
        });

        ParseGeoPoint location2 = new ParseGeoPoint(0, 0);
        ParseQuery<ParseObject> query4 = ParseQuery.getQuery("CityLocation");

        query4.whereNear("Location", location2);

        //query.setLimit(10);
        query4.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground(list);
                    if (list.size() > 0) {
                    }
                }
            }
        });


        ParseQuery<ParseObject> query5 = ParseQuery.getQuery("Cities");
        query5.whereEqualTo("fromCity", CityActivity.chooseCityName);
        query5.whereEqualTo("toCity", DetailsActivity.toCityString);

        query5.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> listt, ParseException e) {

                ParseObject.pinAllInBackground(listt);
                if (e == null) {
                    }
                }
        });

        ParseQuery<ParseObject> query6 = ParseQuery.getQuery("BusAddress");
        query6.whereEqualTo("CityName", BustStateInfo.nameCity);

        query6.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                ParseObject.pinAllInBackground(list);
                if (e == null) {
                }
            }
        });

        ParseGeoPoint location3 = new ParseGeoPoint(0, 0);
        ParseQuery<ParseObject> query7 = ParseQuery.getQuery("BusAddress");
        query7.whereNear("Location", location3);
        query7.setLimit(10);
        query7.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    ParseObject.pinAllInBackground(list);
                    if (list.size() > 0) {

                        for (ParseObject object : list) {
                        }
                    }
                }
            }
        });

        ParseQuery<ParseObject> query8 = ParseQuery.getQuery("BusAddress");

        query8.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {

                    ParseObject.pinAllInBackground(list);


                }
            }
        });

        ParseGeoPoint fromCityLocation = new ParseGeoPoint(0, 0);
        ParseQuery<ParseObject> fromCityQuery = ParseQuery.getQuery("CityLocation");
        fromCityQuery.whereNear("Location", fromCityLocation);
        fromCityQuery.whereEqualTo("Name", RouteActivity.fromCityString);
        fromCityQuery.setLimit(10);
        fromCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground(list);
                }
            }
        });


        ParseGeoPoint toCityLocation = new ParseGeoPoint(0, 0);
        ParseQuery<ParseObject> toCityQuery = ParseQuery.getQuery("CityLocation");
        toCityQuery.whereNear("Location", toCityLocation);
        toCityQuery.whereEqualTo("Name", RouteActivity.toCityString);
        toCityQuery.setLimit(10);
        toCityQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject.pinAllInBackground(list);

                }
            }
        });
        ParseQuery<ParseObject> query9 = ParseQuery.getQuery("Cities");
        query9.whereEqualTo("fromCity", RouteActivity.fromCityString);
        query9.whereEqualTo("toCity", RouteActivity.toCityString);

        query9.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                ParseObject.pinAllInBackground(list);

            }
        });

    }
}
