package com.alenmalik.autobusibih;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by korisnik on 16/05/2017.
 */

public class TransportData {

    static String[] name = {"name1", "name2", "name3"};
    static String[] adresa = {"adresa1", "adresa2", "adresa3"};
    static String[] telefon = {"br1", "br2", "br3"};
    static String[] website = {"web1", "web2", "web3"};


    public static List<Transport> getAlldata() {

        final List<Transport> data = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int i = 0; i < name.length && i < adresa.length && i < telefon.length && i < website.length; i++) {
                Transport item = new Transport();
                item.setName(name[i]);
                item.setAddress(adresa[i]);
                item.setPhoneNumber(telefon[i]);
                item.setWebsite(website[i]);

                data.add(item);
            }
        }
        ParseQuery<ParseObject> prijevoznici = new ParseQuery<ParseObject>("Prijevoznici");
        prijevoznici.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                Transport listItem = new Transport();
                if (e == null) {

                    for (ParseObject object : list) {

                        listItem.setName(String.valueOf(object.get("Prijevoznik")));
                        listItem.setAddress(String.valueOf(object.get("Adresa")));
                        listItem.setPhoneNumber(String.valueOf(object.get("Telefon")));
                        listItem.setWebsite(String.valueOf(object.get("webAdresa")));

                    }
                }
                    data.add(listItem);

            }
        });

        return data;
    }
}
