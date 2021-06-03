package com.raushan.apisql;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("?results=10")
    Call<Stru> getData();
}