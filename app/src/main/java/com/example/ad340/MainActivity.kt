package com.example.ad340

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ad340.details.ForecastDetailsActivity
import com.example.ad340.details.TempDisplaySetting
import com.example.ad340.details.TempDisplaySettingManager
import kotlinx.android.synthetic.main.item_dailly_forecast.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val forecastRepository = ForecastRepository()  //creating instance of the class ForecastRepository


    private  lateinit var tempDisplaySettingManager: TempDisplaySettingManager/*late init is variable that is used to assign a variable a value
                                                                                in the later stages of the program we dont have to assign it at the
                                                                                time of creation */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tempDisplaySettingManager=TempDisplaySettingManager(this) //isntance of the TempDisplaySettingManager refering to this class

        val zipcodeEdittext: EditText = findViewById(R.id.zipcodeEdittext)
        val enterButton: Button = findViewById(R.id.enterButton)


    /* below we have implemented the setOnclicklistener that is invoked or called when on the main screen the
    an temperature is clicked

        */
        enterButton.setOnClickListener {

//            Toast.makeText(this, "Button clicked ", Toast.LENGTH_SHORT).show()
            val zipcode: String = zipcodeEdittext.text.toString()
            if (zipcode.length != 6) {

                Toast.makeText(this, "Please enter a valid pincode ", Toast.LENGTH_SHORT).show()
            } else {
                forecastRepository.loadForecast(zipcode)

            }


        }
        /*the following is the implementation of recyclerview

         */

        val forecastList: RecyclerView = findViewById(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(this)
        val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) {forecast ->

            //Toast.makeText(this,"Clicked Item ",Toast.LENGTH_SHORT).show()
            //
            //            val msg= getString(R.string.forecast_clicked_format,it.temp,it.description)
            //
            //            Toast.makeText(this,msg ,Toast.LENGTH_SHORT).show()
            showforecastDetails(forecast)

        }
        forecastList.adapter = dailyForecastAdapter
        //week 3 displaying the list from the live data
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            // update list adapter
            //Toast.makeText(this,"Loaded items",Toast.LENGTH_SHORT).show()
            dailyForecastAdapter.submitList(forecastItems)




        }


        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)
    }
    /*here in the next function we do call the next activity using the intent function
    where we pass the temp and its description in putextra method which holds the extra information
    required to accomplish the requested action
    other than extra the other thingd that are passed in the intent are
    1.component name
    2.action
    3.data-URI uniform resource identifier it tells the tyoe of data recieved in the intent
    4.category
    5.flags
     */
    private fun showforecastDetails(forecast:DailyForecast) {
        val forecastDetailsIntent=Intent(this,ForecastDetailsActivity::class.java)
        forecastDetailsIntent.putExtra("key_temp",forecast.temp)
        forecastDetailsIntent.putExtra("key_description",forecast.description)

        startActivity(forecastDetailsIntent)


    }
    /*the following function is to add the menu option in the main activity
    similar to the one in the forecast details activity

     */


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.settings_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.tempDisplaySetting -> {
                showTempDisplaySettingsDialog(this,tempDisplaySettingManager)

                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }
//
//    private fun showTempDisplaySettingsDialog() {
//        val dialogBuilder = AlertDialog.Builder(this)
//            .setTitle("Choose Display Units")
//            .setMessage("Choose which temperature unit to use for temperature display ")
//            .setPositiveButton(" \u2109") { _, _ ->
//                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit) //we are referencing to te TempDisplaySettingManager class
//                // where the TempDisplaySetting is enum class
//
//            }
//            .setNeutralButton("\u2103") { _, _ ->
//                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)
//
//            }
//            .setOnDismissListener {
//                Toast.makeText(
//                    this,
//                    "the changes will take place after restarting the app",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        dialogBuilder.show()//if we put this here it will not sow the above celcius and fahrenheit
//
////            }
//        dialogBuilder.show() // if we put this here it will show the celsius and fahreneit and the changes toast both
//    }


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
