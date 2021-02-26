package com.example.ad340.location /*this class is resposible for telling the activity*/

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.ad340.Location
import com.example.ad340.LocationRepository
//import com.example.ad340.AppNavigator
import com.example.ad340.R

class LocationEntryFragment : Fragment() {

    private lateinit var locationRepository: LocationRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        locationRepository= LocationRepository(requireContext())

        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_location_entry, container, false)

        /*
        week 5 - adding fragments to the ui

         */

        //update ui  get view refernce
        val zipcodeEdittext: EditText = view.findViewById(R.id.zipcodeEdittext)
        val enterButton: Button = view.findViewById(R.id.enterButton)  /*here activities have a metod called findviewby ID but fragments do not
                                                                    ->to do this on a fragment we need to get a refernce to the view because that it ts the parent that was inflated
                                                                    ->replace    findViewById(R.id.zipcodeEdittext)
                                                                        BY


                                                                         */

//
        /* below we have implemented the setOnclicklistener that is invoked or called when on the main screen the
        an temperature is clicked
        */
        enterButton.setOnClickListener {

//            Toast.makeText(this, "Button clicked ", Toast.LENGTH_SHORT).show()
            val zipcode: String = zipcodeEdittext.text.toString()
            if (zipcode.length != 6) {

                Toast.makeText(requireContext(),R.string.zipcode_entry_error,Toast.LENGTH_SHORT).show()
            } else {
//                forecastRepository.loadForecast(zipcode)
//                Toast.makeText(requireContext(),"pincode entered",Toast.LENGTH_SHORT).show()
//                appNavigator.navigateToCurrentForecast(zipcode)

                   locationRepository.saveLocation(Location.Zipcode(zipcode))
                    findNavController().navigateUp()
            }


        }
        return view

    }
}

