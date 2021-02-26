package com.example.ad340.api

import com.example.ad340.Location
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun createOpenWeatherMapService():OpenWeatherMapService
{
    val retrofit=Retrofit.Builder()
        .baseUrl("http://api.openweather.org")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    return retrofit.create(OpenWeatherMapService::class.java)
}





interface OpenWeatherMapService {

    /*
    retrofit endpoint method
    we are going to define the path

     */
    @GET("/data/2.5/weather")

    fun currentWeather(
//        query parameteres present in the documentation
        @Query("zip")zipcode: String,
        @Query("units") units:String,
        @Query("appid") apiKey:String

    ):Call<CurrentWeather>

}