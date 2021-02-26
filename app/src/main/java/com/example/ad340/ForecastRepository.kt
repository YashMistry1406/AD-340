package com.example.ad340

/*
in week 7 we add different class for weekly and cureent forecast unlike previously where we were only using one classs to repreesnt both
the forecast
 */
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.util.rangeTo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.BuildConfig
import com.example.ad340.BuildConfig.OPEN_WEATHER_MAP_API_KEY
import com.example.ad340.api.CurrentWeather
import com.example.ad340.api.createOpenWeatherMapService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.random.Random

class ForecastRepository {                              //repo=loading data from repo and providing it to the activity



    /*
    week 7
     */
    private val _currentWeather= MutableLiveData<CurrentWeather>()
     val currentWeather: LiveData<CurrentWeather> = _currentWeather


    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()         //it will hold the value of all the data from week
    val weeklyForecast: LiveData<List<DailyForecast>> = _weeklyForecast


    fun loadWeeklyForecast(zipcode: String) {
        val randomValues = List(7) { Random.nextFloat().rem(100) * 100 }
        val forecastItems =
            randomValues.map { temp ->           //a map function allows us to convert one type to another type

                DailyForecast(temp, getTempDescription(temp))     //it stands for all the values
            }
        _weeklyForecast.setValue(forecastItems)


    }


    fun loadCurrentForecast(zipcode: String)
    {
        /*
            call class that represents that request to end point we want

            from the openWeatherMapService we are using the query values
            and form here we are going to pass the data

         */
        val call = createOpenWeatherMapService().currentWeather(zipcode,"imperial", com.example.ad340.BuildConfig.OPEN_WEATHER_MAP_API_KEY)
        /*
        to get the response back we use the following code

         */
        call.enqueue(object : Callback<CurrentWeather> {
            override fun onResponse(
                call: Call<CurrentWeather>,
                response: Response<CurrentWeather>
            ) {
                val weatherResponse=response.body()
                if(weatherResponse!= null)
                {
                    _currentWeather.value=weatherResponse
                }
            }

            override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                Log.e(ForecastRepository::class.java.simpleName,"error loading current weather")

            }

        })


    }




    private fun getTempDescription(temp: Float): String {
//       return if (temp<75) "Its to cold " else "its great"
        return when (temp) {
//            in Float.MIN_VALUE.rangeTo(0f)->"Too cold to live "
            in 32f.rangeTo(55f) -> "noice"
            in 55f.rangeTo(75f) -> "wanna kill me"
            in 80f.rangeTo(95f)->"okay"
            else -> "it doesnt even matter"
        }
    }
}



