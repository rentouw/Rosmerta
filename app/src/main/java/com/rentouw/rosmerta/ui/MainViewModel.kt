package com.rentouw.rosmerta.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rentouw.lifelogging_battery.domain.BatteryRepository
import com.rentouw.lifelogging_battery.domain.BatteryUtils
import com.rentouw.lifelogging_battery.model.BatteryInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val batteryRepository: BatteryRepository,
    private val batteryUtils: BatteryUtils // Inject BatteryFunctions
) : ViewModel() {

    val batteryLogs: StateFlow<List<BatteryInfo>> = batteryRepository.getBatteryLogs()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun logCurrentBatteryStatus() {
        // Use the new batteryUtils class
        val currentBatteryState = batteryUtils.getCurrentBatteryState()

        currentBatteryState?.let { state ->
            if (state.batteryPct != -1) { // Or rely on getCurrentBatteryState to not return if pct is -1
                viewModelScope.launch {
                    batteryRepository.logBatteryStatus(state.batteryPct, state.isCharging)
                }
            }
        }
    }

    init {
        logCurrentBatteryStatus()
    }
}