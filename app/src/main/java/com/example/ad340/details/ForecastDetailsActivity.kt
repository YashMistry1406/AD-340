package com.example.ad340.details
//package com.example.ad340.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.example.ad340.R
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text

class ForecastDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast_details)


        setTitle(R.string.forecast_details)


        val tempText=findViewById<TextView>(R.id.textView3)
        val descriptionText=findViewById<TextView>(R.id.descriptionText)
        //val temp=intent.getFloatExtra("key_map",0f)


        val temp=intent.getFloatExtra("key_temp",0f)



        tempText.text= formatTempForDisplay(temp)
        descriptionText.text=intent.getStringExtra("key_description")
//
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater=menuInflater
        inflater.inflate(R.menu.settings_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.tempDisplaySetting-> {
                Toast.makeText(this, "clicked emnu item",Toast.LENGTH_SHORT).show()
                true

            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}