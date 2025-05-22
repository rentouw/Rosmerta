package com.rentouw.lifelogging_battery.domain

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

// Data class to hold the retrieved battery information
data class CurrentBatteryState(
    val batteryPct: Int,
    val isCharging: Boolean
)

@Singleton // Make it a singleton if it's to be injected and reused
class BatteryUtils @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getCurrentBatteryState(): CurrentBatteryState? {
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { iFilter ->
            context.registerReceiver(null, iFilter)
        }

        if (batteryStatus == null) {
            // Consider logging an error or handling this case appropriately
            return null
        }

        val level: Int = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
        val scale: Int = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1)

        val batteryPct = if (level != -1 && scale != -1 && scale != 0) { // Added scale != 0 check
            (level * 100 / scale.toFloat()).toInt()
        } else {
            -1 // Or handle as an error/unknown state
        }

        val status: Int = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL

        // Only return a valid state if batteryPct was successfully calculated
        return if (batteryPct != -1) {
            CurrentBatteryState(batteryPct, isCharging)
        } else {
            null // Or a CurrentBatteryState representing an error/unknown
        }
    }
}