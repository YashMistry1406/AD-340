package com.example.ad340


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.util.rangeTo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {                              //repo=loading data from repo and providing it to the activity

    private val _weeklyForecast =
        MutableLiveData<List<DailyForecast>>()         //it will hold the value of all the data from week
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast


    fun loadForecast(zipcode: String) {
        val randomValues = List(7) { Random.nextFloat().rem(100) * 100 }
        val forecastItems =
            randomValues.map { temp ->           //a map function allows us to convert one type to another type

                DailyForecast(temp, getTempDescription(temp))     //it stands for all the values
            }
        _weeklyForecast.setValue(forecastItems)


    }

    private fun getTempDescription(temp: Float): String {
//       return if (temp<75) "Its to cold " else "its great"
        return when (temp) {
//            in Float.MIN_VALUE.rangeTo(0f)->"Too cold to live "
            in 32f.rangeTo(55f) -> "noice"
            in 55f.rangeTo(75f) -> "wanna kill me"
            else -> "it doesnt even matter"
        }
    }
}



