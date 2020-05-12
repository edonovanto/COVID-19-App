package com.novanto.covid.cases;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KawalCoronaApi {

    String BASE_URL = "https://api.kawalcorona.com/";

    @GET("indonesia/provinsi")
    Call<List<ProvinceCase>> getListProvinsi();

    @GET("indonesia")
    Call<List<IndonesiaCase>> getListIndonesia();
}
