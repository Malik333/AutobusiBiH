package com.alenmalik.autobusibih;

/**
 * Created by Nane on 24.5.2017.
 */

public class ReklameModel {
    String prijevoznik;
    String headerTextPrviObavezni;
    String headerText2;
    String headerText3;
    String headerText4;
    String mainTextHeaderObavezni;
    String mainTextHeader2;
    String mainTextHeader3;
    String slika;
    String donjiTextObavezni;
    String donjiTextKojiNaglasava;
    String donjiTextDodatni;
    String donjitTextDodatni2;

    public ReklameModel() {
    }


    public ReklameModel(String prijevoznik, String headerTextPrviObavezni, String headerText2, String headerText3, String headerText4, String mainTextHeaderObavezni, String mainTextHeader2, String mainTextHeader3, String slika,String donjiTextObavezni,String donjiTextKojiNaglasava,String donjiTextDodatni,String donjitTextDodatni2) {
        this.prijevoznik = prijevoznik;
        this.headerTextPrviObavezni = headerTextPrviObavezni;
        this.headerText2 = headerText2;
        this.headerText3 = headerText3;
        this.headerText4 = headerText4;
        this.mainTextHeaderObavezni = mainTextHeaderObavezni;
        this.mainTextHeader2 = mainTextHeader2;
        this.mainTextHeader3 = mainTextHeader3;
        this.slika = slika;
        this.donjiTextObavezni = donjiTextObavezni;
        this.donjiTextKojiNaglasava = donjiTextKojiNaglasava;
        this.donjiTextDodatni = donjiTextDodatni;
        this.donjitTextDodatni2 = donjitTextDodatni2;
    }



    public String getPrijevoznik() {
        return prijevoznik;
    }

    public void setPrijevoznik(String prijevoznik) {
        this.prijevoznik = prijevoznik;
    }

    public String getHeaderTextPrviObavezni() {
        return headerTextPrviObavezni;
    }

    public void setHeaderTextPrviObavezni(String headerTextPrviObavezni) {
        this.headerTextPrviObavezni = headerTextPrviObavezni;
    }

    public String getHeaderText2() {
        return headerText2;
    }

    public void setHeaderText2(String headerText2) {
        this.headerText2 = headerText2;
    }

    public String getHeaderText3() {
        return headerText3;
    }

    public void setHeaderText3(String headerText3) {
        this.headerText3 = headerText3;
    }

    public String getHeaderText4() {
        return headerText4;
    }

    public void setHeaderText4(String headerText4) {
        this.headerText4 = headerText4;
    }

    public String getMainTextHeaderObavezni() {
        return mainTextHeaderObavezni;
    }

    public void setMainTextHeaderObavezni(String mainTextHeaderObavezni) {
        this.mainTextHeaderObavezni = mainTextHeaderObavezni;
    }

    public String getMainTextHeader2() {
        return mainTextHeader2;
    }

    public void setMainTextHeader2(String mainTextHeader2) {
        this.mainTextHeader2 = mainTextHeader2;
    }

    public String getMainTextHeader3() {
        return mainTextHeader3;
    }

    public void setMainTextHeader3(String mainTextHeader3) {
        this.mainTextHeader3 = mainTextHeader3;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getDonjiTextObavezni() {
        return donjiTextObavezni;
    }

    public void setDonjiTextObavezni(String donjiTextObavezni) {
        this.donjiTextObavezni = donjiTextObavezni;
    }

    public String getDonjiTextKojiNaglasava() {
        return donjiTextKojiNaglasava;
    }

    public void setDonjiTextKojiNaglasava(String donjiTextKojiNaglasava) {
        this.donjiTextKojiNaglasava = donjiTextKojiNaglasava;
    }

    public String getDonjiTextDodatni() {
        return donjiTextDodatni;
    }

    public void setDonjiTextDodatni(String donjiTextDodatni) {
        this.donjiTextDodatni = donjiTextDodatni;
    }

    public String getDonjitTextDodatni2() {
        return donjitTextDodatni2;
    }

    public void setDonjitTextDodatni2(String donjitTextDodatni2) {
        this.donjitTextDodatni2 = donjitTextDodatni2;
    }
}