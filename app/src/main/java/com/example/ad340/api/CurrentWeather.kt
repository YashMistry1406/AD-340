package com.example.ad340.api
/*
we are going to define the models needed to model the response from the weather map api


 */

import  com.squareup.moshi.Json
data class Forecast(val temp:Float)
data class Coordinate(val lat:Float , val lan :Float)  //will be useful for one call api for 7 day coordinate


/*

 */
data class CurrentWeather(

    val name:String,
    val coord:Coordinate,
    /*
    the api response for this class does not have a forecast property its name is named main
   api variable type

   we will annotate this property that will helo us to call it any thing we want and retrofit will look after

   what the next line of code means that there will be a file named called json from retrofit which we will annote or map to
   the variable forecast
     */
    @field:Json(name="main") val forecast:Forecast
)
