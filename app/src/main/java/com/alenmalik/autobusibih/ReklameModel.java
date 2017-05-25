package com.alenmalik.autobusibih;

/**
 * Created by Nane on 24.5.2017.
 */

public class ReklameModel  {
    String prijevoznik;
    String samojednaslika;
    String galerija1;
    String galerija2;
    String galerija3;
    String galerija4;


    public ReklameModel() {
    }

    public ReklameModel(String prijevoznik, String samojednaslika, String galerija1, String galerija2, String galerija3, String galerija4) {
        this.prijevoznik = prijevoznik;
        this.samojednaslika = samojednaslika;
        this.galerija1 = galerija1;
        this.galerija2 = galerija2;
        this.galerija3 = galerija3;
        this.galerija4 = galerija4;
    }

    public String getPrijevoznik() {
        return prijevoznik;
    }

    public void setPrijevoznik(String prijevoznik) {
        this.prijevoznik = prijevoznik;
    }

    public String getSamojednaslika() {
        return samojednaslika;
    }

    public void setSamojednaslika(String samojednaslika) {
        this.samojednaslika = samojednaslika;
    }

    public String getGalerija1() {
        return galerija1;
    }

    public void setGalerija1(String galerija1) {
        this.galerija1 = galerija1;
    }

    public String getGalerija2() {
        return galerija2;
    }

    public void setGalerija2(String galerija2) {
        this.galerija2 = galerija2;
    }

    public String getGalerija3() {
        return galerija3;
    }

    public void setGalerija3(String galerija3) {
        this.galerija3 = galerija3;
    }

    public String getGalerija4() {
        return galerija4;
    }

    public void setGalerija4(String galerija4) {
        this.galerija4 = galerija4;
    }
}
