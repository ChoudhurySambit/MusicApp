package com.example.myplayer

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface Apiinterface {

    @Headers("X-RapidAPI-Key: fe860dcd83msh557040dadb2b158p103b94jsn9bbb6495b8a5",
    "X-RapidAPI-Host: deezerdevs-deezer.p.rapidapi.com")

    @GET("search")

    fun get(@Query("q") query: String) : Call<MyData>

}