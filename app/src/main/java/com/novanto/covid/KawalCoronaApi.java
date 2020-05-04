package com.novanto.covid;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KawalCoronaApi {

    String BASE_URL = "https://api.kawalcorona.com/";

    @GET("indonesia/provinsi")
    Call<List<Case>> getListProvinsi();
}
