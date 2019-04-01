package com.example.aqeb_.shopifyapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL ="https://shopicruit.myshopify.com/admin/";

    @GET("products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<JSONResponse> getJSON();
}
