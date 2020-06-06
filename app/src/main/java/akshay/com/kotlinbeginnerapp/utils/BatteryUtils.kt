package akshay.com.kotlinbeginnerapp.utils

import akshay.com.kotlinbeginnerapp.R
import android.app.Activity
import android.os.BatteryManager

class BatteryUtils {

    companion object {

        fun checkBatteryStatus(batteryStatus : Int,context:Activity):String {
            var status = ""

            if (batteryStatus != -1) {

                when (batteryStatus) {
                    BatteryManager.BATTERY_STATUS_CHARGING -> status = context.getString(R.string.status_charging)
                    BatteryManager.BATTERY_STATUS_FULL -> status = context.getString(R.string.status_full)
                    BatteryManager.BATTERY_STATUS_DISCHARGING -> status = context.getString(R.string.status_DisCharging)
                    BatteryManager.BATTERY_STATUS_NOT_CHARGING -> status =
                            context.getString(R.string.status_not_charging)
                }

                return status
            }

            return ""
        }

        fun checkBatteryPercentage(batteryLevel :Int,batteryScale:Int):Int{
            return (batteryLevel.toFloat() / batteryScale.toFloat() * 100.0f).toInt()
        }
    }
}