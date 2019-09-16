package com.example.bloodbank.data.api;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
// this class to bulid retrofit with url and converterfactory
public class ApiClient {
    public static String BASE_URL = "http://ipda3-tech.com/blood-bank/api/v1/";
    public static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if (retrofit==null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
