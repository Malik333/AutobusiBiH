package com.alenmalik.autobusibih;

/**
 * Created by Nane on 24.5.2017.
 */

public class ReklameModel {
    String prijevoznik;
    String adresa;
    String h2;
    String h3;
    String h4;
    String mainObavezni;
    String mainH2;
    String mainH3;
    String slika;
    String donjiObavezni;
    String donjiNaglasava;
    String donjiDodatni;
    String donjiDodatni2;

    public ReklameModel() {
    }

    public ReklameModel(String prijevoznik, String adresa, String h2, String h3, String h4, String mainObavezni, String mainH2, String mainH3, String slika, String donjiObavezni, String donjiNaglasava, String donjiDodatni, String donjiDodatni2) {
        this.prijevoznik = prijevoznik;
        this.adresa = adresa;
        this.h2 = h2;
        this.h3 = h3;
        this.h4 = h4;
        this.mainObavezni = mainObavezni;
        this.mainH2 = mainH2;
        this.mainH3 = mainH3;
        this.slika = slika;
        this.donjiObavezni = donjiObavezni;
        this.donjiNaglasava = donjiNaglasava;
        this.donjiDodatni = donjiDodatni;
        this.donjiDodatni2 = donjiDodatni2;
    }

    public String getPrijevoznik() {
        return prijevoznik;
    }

    public void setPrijevoznik(String prijevoznik) {
        this.prijevoznik = prijevoznik;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getH3() {
        return h3;
    }

    public void setH3(String h3) {
        this.h3 = h3;
    }

    public String getH4() {
        return h4;
    }

    public void setH4(String h4) {
        this.h4 = h4;
    }

    public String getMainObavezni() {
        return mainObavezni;
    }

    public void setMainObavezni(String mainObavezni) {
        this.mainObavezni = mainObavezni;
    }

    public String getMainH2() {
        return mainH2;
    }

    public void setMainH2(String mainH2) {
        this.mainH2 = mainH2;
    }

    public String getMainH3() {
        return mainH3;
    }

    public void setMainH3(String mainH3) {
        this.mainH3 = mainH3;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getDonjiObavezni() {
        return donjiObavezni;
    }

    public void setDonjiObavezni(String donjiObavezni) {
        this.donjiObavezni = donjiObavezni;
    }

    public String getDonjiNaglasava() {
        return donjiNaglasava;
    }

    public void setDonjiNaglasava(String donjiNaglasava) {
        this.donjiNaglasava = donjiNaglasava;
    }

    public String getDonjiDodatni() {
        return donjiDodatni;
    }

    public void setDonjiDodatni(String donjiDodatni) {
        this.donjiDodatni = donjiDodatni;
    }

    public String getDonjiDodatni2() {
        return donjiDodatni2;
    }

    public void setDonjiDodatni2(String donjiDodatni2) {
        this.donjiDodatni2 = donjiDodatni2;
    }
}