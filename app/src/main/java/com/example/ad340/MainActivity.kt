 package com.example.ad340

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.ad340.details.TempDisplaySettingManager
import com.example.ad340.forecast.CurrentForecastFragmentDirections
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView


 class MainActivity : AppCompatActivity() {             /*we need to implement the Appnavigator or create a abstract class
                                                        make activity abstarct or implement the Appnavigator
                                                        */







    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager/*late init is variable that is used to assign a variable a value
                                                                                in the later stages of the program we dont have to assign it at the
                                                                                time of creation */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tempDisplaySettingManager =
            TempDisplaySettingManager(this)      //isntance of the TempDisplaySettingManager refering to this class

        val navController=findNavController(R.id.nav_host_fragment)
        val appBarConfiguration=AppBarConfiguration(navController.graph)

        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setTitle(R.string.app_name)
        findViewById<BottomNavigationView>(R.id.bottomNavigationView).setupWithNavController(navController)

//
        /*the following is the implementation of recyclerview

         */


        /*week 5

         */
        /*supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer, LocationEntryFragment()) /*begin a new fragment transaction so we can add a fragment then we
                                                       are going to specify add call and say createing a new locationentryfragment
                                                       and add it ot the rot vfiwe group and we are gin to commit the transcaction
                                                        */
            .commit()*/


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
                showTempDisplaySettingsDialog(this, tempDisplaySettingManager)

                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun navigateToCurrentForecast(zipcode: String) {
//        forecastRepository.loadForecast(zipcode)    /*we will cal this method from location entry forecast now we will sow the fragment jsut created From CurrentForecastFragment

//         supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragmentContainer,CurrentForecastFragment.newInstance(zipcode))
//            .commit()
//        /*the above code is commented because it is a old manualway of adding fragments and below  we are replacing it by NAVcontroller (navigation component */
//
//    }
//the below are used for navigation in the app

//    override fun navigateToLocationEntry() {
////        supportFragmentManager
////            .beginTransaction()
////            .replace(R.id.fragmentContainer,LocationEntryFragment())
////            .commit()
//
//        val action =CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
//        findNavController(R.id.nav_host_fragment).navigate(action)
//    }
//
//    override fun navigateToForecastDetails(forecast: DailyForecast) {
//        val action=CurrentForecastFragmentDirections.actionCurrentForecastFragmentToForecastDetailsFragment(forecast.temp,forecast.description)
//        findNavController(R.id.nav_host_fragment).navigate(action)
//    }

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
