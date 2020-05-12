package com.novanto.covid.cases;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Covid19Api {

    String BASE_URL = "https://api.covid19api.com/";

    @GET("summary")
    Call<Covid19Case> getAllCovid19Cases();
}
