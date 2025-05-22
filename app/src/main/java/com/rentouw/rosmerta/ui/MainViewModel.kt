package com.rentouw.rosmerta.ui

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentouw.lifelogging_battery.model.BatteryInfo
import com.rentouw.lifelogging_battery.repository.BatteryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val batteryRepository: BatteryRepository,
    @ApplicationContext private val context: Context // For getting initial battery state
) : ViewModel() {

    val batteryLogs: StateFlow<List<BatteryInfo>> = batteryRepository.getBatteryLogs()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Function to manually log current battery status (you'll automate this later)
    fun logCurrentBatteryStatus() {
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { iFilter ->
            context.registerReceiver(null, iFilter)
        }

        val level: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        val batteryPct = if (level != -1 && scale != -1) {
            (level * 100 / scale.toFloat()).toInt()
        } else {
            -1
        }

        val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1
        val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL

        if (batteryPct != -1) {
            viewModelScope.launch {
                batteryRepository.logBatteryStatus(batteryPct, isCharging)
            }
        }
    }

    init {
        // Log initial status or set up a periodic worker here
        logCurrentBatteryStatus() // Example: Log when ViewModel is created
    }
}