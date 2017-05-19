package com.alenmalik.autobusibih;

/**
 * Created by korisnik on 19/05/2017.
 */

public class MedjugradskiIspisModel {

    String relacija;
    String vrijemePolaska;
    String dan;
    String duzinaPuta;
    String linija;
    String cijena;
    String prijevoznik;
    String stanica;

    public MedjugradskiIspisModel() {
    }

    public MedjugradskiIspisModel(String relacija, String vrijemePolaska, String dan, String duzinaPuta, String linija, String cijena, String prijevoznik, String stanica) {
        this.relacija = relacija;
        this.vrijemePolaska = vrijemePolaska;
        this.dan = dan;
        this.duzinaPuta = duzinaPuta;
        this.linija = linija;
        this.cijena = cijena;
        this.prijevoznik = prijevoznik;
        this.stanica = stanica;
    }

    public String getRelacija() {
        return relacija;
    }

    public void setRelacija(String relacija) {
        this.relacija = relacija;
    }

    public String getVrijemePolaska() {
        return vrijemePolaska;
    }

    public void setVrijemePolaska(String vrijemePolaska) {
        this.vrijemePolaska = vrijemePolaska;
    }

    public String getDan() {
        return dan;
    }

    public void setDan(String dan) {
        this.dan = dan;
    }

    public String getDuzinaPuta() {
        return duzinaPuta;
    }

    public void setDuzinaPuta(String duzinaPuta) {
        this.duzinaPuta = duzinaPuta;
    }

    public String getLinija() {
        return linija;
    }

    public void setLinija(String linija) {
        this.linija = linija;
    }

    public String getCijena() {
        return cijena;
    }

    public void setCijena(String cijena) {
        this.cijena = cijena;
    }

    public String getPrijevoznik() {
        return prijevoznik;
    }

    public void setPrijevoznik(String prijevoznik) {
        this.prijevoznik = prijevoznik;
    }

    public String getStanica() {
        return stanica;
    }

    public void setStanica(String stanica) {
        this.stanica = stanica;
    }
}
