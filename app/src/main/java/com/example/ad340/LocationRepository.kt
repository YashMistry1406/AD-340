package com.example.ad340

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


sealed class Location     // to represent different tye of location
{
    data class Zipcode (val zipcode:String):Location()  // in future we can add location from gps so it gives a way to implement it easily
                                                          //it exteneds the Location class


}

private const val KEY_ZIPCODE="key_zipcode"  // creating a constant value to be reused within this file




class LocationRepository(context: Context) {     //we need context to get access to the shared prefernces





/*week 7  -Retrofit week
  Shared preferences:-https://www.geeksforgeeks.org/shared-preferences-in-android-with-examples/
  Sealed Classes:-https://www.journaldev.com/18719/kotlin-sealed-class
                  https://kotlinlang.org/docs/sealed-classes.html
*/

    private  val preferences =context.getSharedPreferences("settings",Context.MODE_PRIVATE)






    /*we have created two variable one that is,one that is private so that external body cannot manipulate the data and
    one public that is accessible to the external body and read the data from the internal or private library

     */
    private val _savedLocation: MutableLiveData<Location> = MutableLiveData()
    val savedLocation: LiveData<Location> = _savedLocation





    /*
        we still need to know that when the locaation changes so that we can notify other caller
        it can change due to the other instances

        we need to listen to changes so that we can alert the observer that are savedLocation



     */
    init {   //constructor
        preferences.registerOnSharedPreferenceChangeListener { sharedPreferences,keys ->
            if (keys != KEY_ZIPCODE) return@registerOnSharedPreferenceChangeListener
            broadcastSavedZipcode()


        }
        broadcastSavedZipcode()
    }



    fun saveLocation(location: Location)
    {
        when(location)
        {
            /* any time we call the fun saveLocation it going to save the location to shared prefernces
             */
            is Location.Zipcode->preferences.edit().putString("KEY_ZIPCODE",location.zipcode).apply()
        }
    }

    fun broadcastSavedZipcode(){
        val zipcode=preferences.getString(KEY_ZIPCODE, "")

        if(zipcode!= null  && zipcode.isNotBlank())
        {
            _savedLocation.value=Location.Zipcode(zipcode)
        }
    }
}

