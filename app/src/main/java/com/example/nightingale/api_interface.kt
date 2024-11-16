package com.example.nightingale

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface api_interface {
    @Headers("x-rapidapi-key:7deae3648fmsha3c41976a0646c9p137c79jsn2e7c8e77e905",
        "x-rapidapi-host:deezerdevs-deezer.p.rapidapi.com")
    @GET("search")
    fun getData(@Query("q")query:String):Call<my_music_data>
}