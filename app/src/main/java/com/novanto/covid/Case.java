package com.novanto.covid;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Case {

    @SerializedName("attributes")
    public Attributes attributes = null;

    public Attributes getAttributes() {
        return attributes;
    }

    public class Attributes{
        @SerializedName("FID")
        private String FID;
        @SerializedName("Kode_Provi")
        private  String Kode_Provi;
        @SerializedName("Provinsi")
        private  String Provinsi;
        @SerializedName("Kasus_Posi")
        private String Kasus_Posi;
        @SerializedName("Kasus_Semb")
        private  String Kasus_Semb;
        @SerializedName("Kasus_Meni")
        private  String Kasus_Meni;



        public Attributes(String FID, String kode_Provi, String provinsi, String kasus_Posi, String kasus_Semb, String kasus_Meni) {
            this.FID = FID;
            Kode_Provi = kode_Provi;
            Provinsi = provinsi;
            Kasus_Posi = kasus_Posi;
            Kasus_Semb = kasus_Semb;
            Kasus_Meni = kasus_Meni;
        }

        public String getFID() {
            return FID;
        }

        public String getKode_Provi() {
            return Kode_Provi;
        }

        public String getProvinsi() {
            return Provinsi;
        }

        public String getKasus_Posi() {
            return Kasus_Posi;
        }

        public String getKasus_Semb() {
            return Kasus_Semb;
        }

        public String getKasus_Meni() {
            return Kasus_Meni;
        }
    }


}
