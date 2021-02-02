package com.example.ad340

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.ad340.details.TempDisplaySetting
import com.example.ad340.details.TempDisplaySettingManager

fun formatTempForDisplay(temp:Float ,tempDisplaySetting: TempDisplaySetting): String{
    return when (tempDisplaySetting){
        TempDisplaySetting.Fahrenheit-> String.format("%.2f F",temp)
        TempDisplaySetting.Celsius-> {
            val temp = (temp - 32f) * (5f / 9f)
            String.format("%.2f C", temp)

        }
    }
}
 fun showTempDisplaySettingsDialog(context:Context,tempDisplaySettingManager: TempDisplaySettingManager) {
    val dialogBuilder = AlertDialog.Builder(context)
        .setTitle("Choose Display Units")
        .setMessage("Choose which temperature unit to use for temperature display ")
        .setPositiveButton("F"){_,_->
            tempDisplaySettingManager.updateSetting(TempDisplaySetting.Fahrenheit) //we are referencing to te TempDisplaySettingManager class
            // where the TempDisplaySetting is enum class

        }
        .setNeutralButton("C") {_,_->
            tempDisplaySettingManager.updateSetting(TempDisplaySetting.Celsius)

        }
        .setOnDismissListener {
            Toast.makeText(context,"the changes will take place after restarting the app", Toast.LENGTH_SHORT).show()}
//    dialogBuilder.show()//if we put this here it will not sow the above celcius and fahrenheit

//            }
    dialogBuilder.show() // if we put this here it will show the celsius and fahreneit and the changes toast both
}