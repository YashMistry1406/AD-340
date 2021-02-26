package com.example.ad340.forecast

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ad340.*
import com.example.ad340.details.ForecastDetailsFragment
import com.example.ad340.details.TempDisplaySettingManager
import com.google.android.material.floatingactionbutton.FloatingActionButton


class WeeklyForecastFragment : Fragment() {

    private lateinit var locationRepository: LocationRepository



    private val forecastRepository =
        ForecastRepository()  //creating instance of the class ForecastRepository


    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val zipcode =arguments?.getString(KEY_ZIPCODE) ?:""
        tempDisplaySettingManager = TempDisplaySettingManager(requireContext())





        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_weekly_forecast, container, false)  //




        val forecastList: RecyclerView = view.findViewById(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(requireContext())
        val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) { it ->

            //Toast.makeText(this,"Clicked Item ",Toast.LENGTH_SHORT).show()
            //
            //            val msg= getString(R.string.forecast_clicked_format,it.temp,it.description)
            //
            //            Toast.makeText(this,msg ,Toast.LENGTH_SHORT).show()
            showforecastDetails(it)

        }
        forecastList.adapter = dailyForecastAdapter
        //week 3 displaying the list from the live data
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->
            // update list adapter
            //Toast.makeText(this,"Loaded items",Toast.LENGTH_SHORT).show()
            dailyForecastAdapter.submitList(forecastItems)


        }

        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)


        val locationEntryButton:FloatingActionButton=view.findViewById(R.id.locationEntryButton)
        locationEntryButton.setOnClickListener {
            showLocationEntry()   // we ar creating a click listener for the icon at the bottom of the screen


        }

        locationRepository= LocationRepository((requireContext()))
        val savedLocationObserver= Observer<Location> {savedLocation ->
            /*
            when can enforce that we handle all types wothin sealed class hierarchy
             */
            when(savedLocation)
            {
                is Location.Zipcode-> forecastRepository.loadWeeklyForecast(savedLocation.zipcode)

            }
        }
        locationRepository.savedLocation.observe(viewLifecycleOwner,savedLocationObserver)
        return view
    }



    private fun showLocationEntry()
    {
        val action =CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
        findNavController().navigate(action)
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
    private fun showforecastDetails(forecast: DailyForecast) {
// the following part has been commented as now we are using fragments where as previously we were using avtivity and fragment do have intents like activity

//        val forecastDetailsIntent = Intent(requireContext(), ForecastDetailsFragment::class.java)
//        forecastDetailsIntent.putExtra("key_temp", forecast.temp)
//        forecastDetailsIntent.putExtra("key_description", forecast.description)

//        startActivity(forecastDetailsIntent)
//        appNavigator.navigateToForecastDetails(forecast) // this to say that fragment does not know whta is the details but it knows that is going to sow forecast details
        val action=CurrentForecastFragmentDirections.actionCurrentForecastFragmentToForecastDetailsFragment(forecast.temp,forecast.description)
        findNavController().navigate(action)


    }
    companion object{ //static method in java
//        we can replicate the behaviour that of staticmetod in java
        const val KEY_ZIPCODE="key_zipcode" // we will pass a keyvalue pair

        fun newInstance(zipcode:String):WeeklyForecastFragment{   //new isntance of the above class
           val fragment=WeeklyForecastFragment()
            val args= Bundle()     // defined to store key value paris in kotlin

            args.putString(KEY_ZIPCODE,zipcode)
            fragment.arguments=args

            return fragment

        }

    }


}