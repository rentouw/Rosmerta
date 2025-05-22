package com.rentouw.lifelogging_battery.data

import com.rentouw.data_local.dao.BatteryDao
import com.rentouw.data_local.model.BatteryLogEntity
import com.rentouw.lifelogging_battery.domain.BatteryRepository
import com.rentouw.lifelogging_battery.model.BatteryInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BatteryRepositoryImpl @Inject constructor(
    private val batteryDao: BatteryDao
) : BatteryRepository {

    override suspend fun logBatteryStatus(currentLevel: Int, isCharging: Boolean) {
        val log = BatteryLogEntity(
            timestamp = System.currentTimeMillis(),
            level = currentLevel,
            isCharging = isCharging
        )
        batteryDao.insertLog(log)
    }

    override fun getBatteryLogs(): Flow<List<BatteryInfo>> {
        return batteryDao.getAllLogs().map { entities ->
            entities.map { entity ->
                BatteryInfo( // Map Entity to Domain model
                    timestamp = entity.timestamp,
                    level = entity.level,
                    isCharging = entity.isCharging
                )
            }
        }
    }

    override suspend fun getLatestBatteryInfo(): BatteryInfo? {
        return batteryDao.getLatestLog()?.let { entity ->
            BatteryInfo(
                timestamp = entity.timestamp,
                level = entity.level,
                isCharging = entity.isCharging
            )
        }
    }
}