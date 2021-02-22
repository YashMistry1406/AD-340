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


class CurrentForecastFragment : Fragment() {
    private val forecastRepository =
        ForecastRepository()  //creating instance of the class ForecastRepository

//    private lateinit var appNavigator: AppNavigator
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        appNavigator=context as AppNavigator // we cast a variable as another type appnavigator has the refernce to appNavigator
//    }

    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view= inflater.inflate(R.layout.fragment_current_forecast, container, false)

        val zipcode =arguments?.getString(KEY_ZIPCODE) ?:""

        tempDisplaySettingManager = TempDisplaySettingManager(requireContext()) // Inflate the layout for this fragment

        val locationEntryButton:FloatingActionButton=view.findViewById(R.id.locationEntryButton)

        val forecastList: RecyclerView = view.findViewById(R.id.forecastList)

        val dailyForecastAdapter = DailyForecastAdapter(tempDisplaySettingManager) { forecast ->

            //Toast.makeText(this,"Clicked Item ",Toast.LENGTH_SHORT).show()
            //
            //            val msg= getString(R.string.forecast_clicked_format,it.temp,it.description)
            //
            //            Toast.makeText(this,msg ,Toast.LENGTH_SHORT).show()
            showForecastDetails(forecast)

        }
        forecastList.adapter = dailyForecastAdapter





        forecastList.layoutManager = LinearLayoutManager(requireContext())


        //week 3 displaying the list from the live data
        val weeklyForecastObserver = Observer<List<DailyForecast>> { forecastItems ->

            dailyForecastAdapter.submitList(forecastItems)


        }


        forecastRepository.weeklyForecast.observe(this, weeklyForecastObserver)


        locationEntryButton.setOnClickListener {
            showLocationEntry() // we ar creating a click listener for the icon at the bottom of the screen


        }


        forecastRepository.loadForecast((zipcode))
        return view
    }


    private fun showLocationEntry(){
        val action=CurrentForecastFragmentDirections.actionCurrentForecastFragmentToLocationEntryFragment()
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
    private fun showForecastDetails(forecast: DailyForecast) {
// the following part has been commented as now we are using fragments where as previously we were using avtivity and fragment do have intents like activity

//        val forecastDetailsIntent = Intent(requireContext(), ForecastDetailsFragment::class.java)
//        forecastDetailsIntent.putExtra("key_temp", forecast.temp)
//        forecastDetailsIntent.putExtra("key_description", forecast.description)

//        startActivity(forecastDetailsIntent)
//        appNavigator.navigateToForecastDetails(forecast) // this to say that fragment does not know whta is the details but it knows that is going to sow forecast details
        val action= CurrentForecastFragmentDirections.actionCurrentForecastFragmentToForecastDetailsFragment(forecast.temp,forecast.description)
        findNavController().navigate(action)

    }
    companion object{ //static method in java
//        we can replicate the behaviour that of staticmetod in java
        const val KEY_ZIPCODE="key_zipcode" // we will pass a keyvalue pair

        fun newInstance(zipcode:String):CurrentForecastFragment{   //new isntance of the above class
           val fragment=CurrentForecastFragment()
            val args= Bundle()     // defined to store key value paris in kotlin

            args.putString(KEY_ZIPCODE,zipcode)
            fragment.arguments=args

            return fragment

        }

    }


}
