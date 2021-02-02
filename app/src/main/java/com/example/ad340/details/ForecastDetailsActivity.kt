package com.example.ad340.details
//package com.example.ad340.details

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ad340.R
import com.example.ad340.formatTempForDisplay
import com.example.ad340.showTempDisplaySettingsDialog
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class ForecastDetailsActivity : AppCompatActivity() {
    private lateinit var tempDisplaySettingManager: TempDisplaySettingManager // this a variaable that will be assinged a value but is not being assigned right now
    // this is done beacuse a lot of things neeed a context and and your context isn't value until on create is called and app crashes
    //so for us in oncreate we can initialize tempDispalysettingManager



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)

        tempDisplaySettingManager=TempDisplaySettingManager(this)// invoking the constructor refer line 17 18 19



        setTitle(R.string.forecast_details)


        val tempText = findViewById<TextView>(R.id.textView3)
        val descriptionText = findViewById<TextView>(R.id.descriptionText)
        //val temp=intent.getFloatExtra("key_map",0f)


        val temp = intent.getFloatExtra("key_temp", 0f)




        tempText.text = formatTempForDisplay(temp,tempDisplaySettingManager.getTempDisplaySetting())
        descriptionText.text = intent.getStringExtra("key_description")
//
    }

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
////            }
//        dialogBuilder.show() // if we put this here it will show the celsius and fahreneit and the changes toast both
//    }
}


