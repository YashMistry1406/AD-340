package com.example.ad340.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
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



        tempText.text="${intent.getFloatExtra("key_map",0f)}"
            descriptionText.text=intent.getStringExtra("key_description")
//
    }
}