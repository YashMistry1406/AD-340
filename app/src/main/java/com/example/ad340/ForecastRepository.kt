package com.example.ad340


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.util.rangeTo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {

    private val _weeklyForecast= MutableLiveData<List<DailyForecast>>()         //it will hold the value of all the data from week
    val weeklyForecast:LiveData<List<DailyForecast>> = _weeklyForecast


    fun loadForecast (zipcode:String){
        val randomValues=List(size = 7){ Random.nextFloat().rem(100)*100}
        val forecastItems= randomValues.map{temp->           //a map function allows us to convert one type to another type

           DailyForecast(temp,"partly cloudy")     //it stands for all the values
        }
        _weeklyForecast.setValue(forecastItems)


    }
//    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getTempDescription(temp:Float): String{
//        return if (temp<75)"It's too cold " else "great
        return when (temp){
//            in Float.MIN_VAL.rangeTo(0f)->"too cold "
            in 0f.rangeTo(32f)->"NOICE "
            else ->"it doesn't even mmatter"

        }
    }

}