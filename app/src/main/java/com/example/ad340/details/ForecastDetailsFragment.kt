package com.example.ad340.details
//package com.example.ad340.details


import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.ad340.R
import com.example.ad340.formatTempForDisplay
import com.example.ad340.showTempDisplaySettingsDialog

class ForecastDetailsFragment : Fragment() {
    private val args:ForecastDetailsFragmentArgs by navArgs()
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager // this a variaable that will be assinged a value but is not being assigned right now
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_forecast_details, container, false)
        tempDisplaySettingManager =
            TempDisplaySettingManager(requireContext())// invoking the constructor refer line 17 18 19


        val tempText = layout.findViewById<TextView>(R.id.textView3)
        val descriptionText = layout.findViewById<TextView>(R.id.descriptionText)
        //val temp=intent.getFloatExtra("key_map",0f)


//        val temp = intent.getFloatExtra("key_temp", 0f)
//        tempText.text = formatTempForDisplay(temp,tempDisplaySettingManager.getTempDisplaySetting())
//        descriptionText.text = intent.getStringExtra("key_description")
        // using args plugin
        tempText.text = formatTempForDisplay(args.temp,tempDisplaySettingManager.getTempDisplaySetting())
        descriptionText.text =args.description

        return layout
    }
}


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.fragment_forecast_details)
//
//     tempDisplaySettingManager=TempDisplaySettingManager(this)// invoking the constructor refer line 17 18 19
//
//
//
//        setTitle(R.string.forecast_details)

//
//        val tempText = findViewById<TextView>(R.id.textView3)
//        val descriptionText = findViewById<TextView>(R.id.descriptionText)
//        //val temp=intent.getFloatExtra("key_map",0f)
//
//
//        val temp = intent.getFloatExtra("key_temp", 0f)
//
//
//
//
//        tempText.text = formatTempForDisplay(temp,tempDisplaySettingManager.getTempDisplaySetting())
//        descriptionText.text = intent.getStringExtra("key_description")
//
/*the following code is commented or deleted beacause we are now using fragments instead of activity
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val inflater: MenuInflater = menuInflater
//        inflater.inflate(R.menu.settings_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.tempDisplaySetting -> {
//                showTempDisplaySettingsDialog(this,tempDisplaySettingManager)
//                true
//
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
*/
//    private fun showTempDisplaySettingsDialog() {
//        val dialogBuilder = AlertDialog.Builder(this)
//            .setTitle("Choose Display Units")
//            .setMessage("Choose which temperature unit to use for temperature display ")
//            .setPositiveButton(" \u2109"){_,_->
//                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit) //we are referencing to te TempDisplaySettingManager class
//                // where the TempDisplaySetting is enum class
//
//            }
//            .setNeutralButton("\u2103") {_,_->
//                tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)
//
//            }
//            .setOnDismissListener {
//            Toast.makeText(this,"the changes will take place after restarting the app",Toast.LENGTH_SHORT).show()}
//                dialogBuilder.show()//if we put this here it will not sow the above celcius and fahrenheit
//
//      }
//        dialogBuilder.show() // if we put this here it will show the celsius and fahreneit and the changes toast both
//    }



