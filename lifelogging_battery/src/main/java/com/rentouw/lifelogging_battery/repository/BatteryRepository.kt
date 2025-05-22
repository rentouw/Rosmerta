package com.rentouw.lifelogging_battery.repository

import com.rentouw.lifelogging_battery.model.BatteryInfo
import kotlinx.coroutines.flow.Flow

interface BatteryRepository {
    suspend fun logBatteryStatus(currentLevel: Int, isCharging: Boolean)
    fun getBatteryLogs(): Flow<List<BatteryInfo>> // Using domain model
    suspend fun getLatestBatteryInfo(): BatteryInfo?
}