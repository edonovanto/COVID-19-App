package com.novanto.covid.cases;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Covid19Api {

    String BASE_URL = "https://api.covid19api.com/";

    @GET("summary")
    Call<Covid19Api> getAllCovid19Cases();
}
