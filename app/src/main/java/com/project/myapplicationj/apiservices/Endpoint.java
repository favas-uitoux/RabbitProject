package com.project.myapplicationj.apiservices;

import com.project.myapplicationj.apiservices.pojos.fetch_employs.Response;
import com.project.myapplicationj.apiservices.pojos.fetch_employs.ResponseItem;


import java.util.List;

import retrofit2.Call;

import retrofit2.http.GET;


public interface Endpoint {



    @GET("v2/5d565297300000680030a986")
    Call<List<ResponseItem>> fetch_employees();

//    @GET("v2/5d565297300000680030a986")
//    Call<Response> fetch_employees();


}
