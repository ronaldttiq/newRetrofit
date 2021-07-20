package com.bs.newretrofitbasic.interfaces;

import com.bs.newretrofitbasic.data.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataInterfaces {


    @GET("posts")
    Call<List<Data>> getPost();



}
