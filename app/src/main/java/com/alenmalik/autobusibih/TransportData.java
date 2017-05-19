/*
package com.alenmalik.autobusibih;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by korisnik on 16/05/2017.
 *//*


public class TransportData {

    static ArrayList name;// = {"name1", "name2", "name3"};
    static ArrayList adresa; // = {"adresa1", "adresa2", "adresa3"};
    static ArrayList telefon; // = {"br1", "br2", "br3"};
    static ArrayList website; // = {"web1", "web2", "web3"};


    public static List<Transport> getAlldata() {

        final List<Transport> data = new ArrayList<>();
        /*for (int x = 0; x < 3; x++) {
            for (int i = 0; i < name.length && i < adresa.length && i < telefon.length && i < website.length; i++) {
                Transport item = new Transport();
                item.setName(name[i]);
                item.setAddress(adresa[i]);
                item.setPhoneNumber(telefon[i]);
                item.setWebsite(website[i]);

                data.add(item);
            }

        }
        /*
        name = new ArrayList();
        adresa = new ArrayList();
        telefon = new ArrayList();
        website = new ArrayList();
        final int count;
        final Transport listItem = new Transport();
        ParseQuery<ParseObject> prijevoznici = new ParseQuery<ParseObject>("Prijevoznici"); */

 /*
        prijevoznici.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null) {

                    for (ParseObject object : list) {

                        String nameS = String.valueOf(object.get("Prijevoznik"));
                        String adr = String.valueOf(object.get("Adresa"));
                        String tel = String.valueOf(object.get("Telefon"));
                        String web = String.valueOf(object.get("webAdresa"));
                        Log.i("name", nameS);
                        Log.i("adrsa", adr);
                        Log.i("tel",tel);
                        Log.i("web", web);

                        name.add(nameS);
                        adresa.add(adr);
                        telefon.add(tel);
                        website.add(web);

                       /* listItem.setName(String.valueOf(object.get("Prijevoznik")));
                        listItem.setAddress(String.valueOf(object.get("Adresa")));
                        listItem.setPhoneNumber(String.valueOf(object.get("Telefon")));
                        listItem.setWebsite(String.valueOf(object.get("webAdresa")));





                    } for (int i = 0; i < name.size() && i < adresa.size() && i < telefon.size() && i < website.size(); i++) {
                        Transport item = new Transport();
                        item.setName((String) name.get(i));
                        item.setAddress((String) adresa.get(i));
                        item.setPhoneNumber((String) telefon.get(i));
                        item.setWebsite((String) website.get(i));

                        data.add(item);
                    }
                }




            }
        });
        Log.i("namesize", String.valueOf(name.size()));
        Log.i("adrsasize", String.valueOf(adresa.size()));
        Log.i("telsize", String.valueOf(telefon.size()));
        Log.i("websize", String.valueOf(website.size()));

        for (int i = 0; i < name.size() && i < adresa.size() && i < telefon.size() && i < website.size(); i++) {
            Transport item = new Transport();
            item.setName((String) name.get(i));
            item.setAddress((String) adresa.get(i));
            item.setPhoneNumber((String) telefon.get(i));
            item.setWebsite((String) website.get(i));

            data.add(item);
        }


        return data;
    }
}
*/
