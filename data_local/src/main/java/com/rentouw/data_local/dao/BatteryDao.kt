package com.rentouw.data_local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rentouw.data_local.model.BatteryLogEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BatteryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(log: BatteryLogEntity)

    @Query("SELECT * FROM battery_logs ORDER BY timestamp DESC")
    fun getAllLogs(): Flow<List<BatteryLogEntity>>

    @Query("SELECT * FROM battery_logs ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestLog(): BatteryLogEntity?
}