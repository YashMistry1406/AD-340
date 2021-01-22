package com.example.ad340

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_dailly_forecast.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val forecastRepository=ForecastRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val zipcodeEdittext : EditText =findViewById(R.id.zipcodeEdittext)
        val enterButton :Button =findViewById(R.id.enterButton)

    
        enterButton.setOnClickListener {

            Toast.makeText(this,"Button clicked ",Toast.LENGTH_SHORT).show()
            val zipcode : String= zipcodeEdittext.text.toString()
            if (zipcode.length!=6)
            {
            
                Toast.makeText(this,"Please enter a valid pincode ",Toast.LENGTH_SHORT).show()
            }
            else {
                forecastRepository.loadForecast(zipcode)

            }


        }
        val forecastList:RecyclerView=findViewById(R.id.forecastList)
        forecastList.layoutManager=LinearLayoutManager(this)
        val dailyForecastAdapter=DailyForecastAdapter(){

            //Toast.makeText(this,"Clicked Item ",Toast.LENGTH_SHORT).show()

            val msg= getString(R.string.forecast_clicked_format,it.temp,it.description)

            Toast.makeText(this,msg ,Toast.LENGTH_SHORT).show()


        }
        forecastList.adapter=dailyForecastAdapter

        val weeklyForecastObserver=Observer<List<DailyForecast>>{forecastitems->
            // update list adapter
            //Toast.makeText(this,"Loaded items",Toast.LENGTH_SHORT).show()
            dailyForecastAdapter.submitList(forecastitems)



        }

        forecastRepository.weeklyForecast.observe(this,weeklyForecastObserver)


    }

//    override fun onStart() {
//        super.onStart()
//
//    }
//    override fun onResume(){
//        super.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//    }
//    override fun onStop(){
//        super.onStop()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//    }
}