package com.example.ad340.details

import android.content.Context
import java.security.AccessControlContext

enum class TempDisplaySetting
{
    Fahrenheit,Celsius

}
class TempDisplaySettingManager(context: Context) {
    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)


    fun updateSetting(setting: TempDisplaySetting) {
        preferences.edit().putString("key_temp_display", setting.name).apply()
    }
    fun getTempDisplaySetting():TempDisplaySetting{
        val settingValue=preferences.getString("key_temp_display",TempDisplaySetting.Fahrenheit.name)?:TempDisplaySetting.Fahrenheit.name
        return TempDisplaySetting.valueOf(settingValue)

    }
}
