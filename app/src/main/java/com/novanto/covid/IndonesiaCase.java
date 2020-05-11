package com.novanto.covid;

import com.google.gson.annotations.SerializedName;

public class IndonesiaCase {
    @SerializedName("name")
    public String name;
    @SerializedName("positif")
    public String positif;
    @SerializedName("sembuh")
    public String sembuh;
    @SerializedName("meninggal")
    public String meninggal;


    public IndonesiaCase(String name, String positif, String sembuh, String meninggal) {
        this.name = name;
        this.positif = positif;
        this.sembuh = sembuh;
        this.meninggal = meninggal;
    }

    public String getName() {
        return name;
    }

    public String getPositif() {
        return positif;
    }

    public String getSembuh() {
        return sembuh;
    }

    public String getMeninggal() {
        return meninggal;
    }

}
