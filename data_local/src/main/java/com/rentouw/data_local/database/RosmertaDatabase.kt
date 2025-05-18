package com.rentouw.data_local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rentouw.data_local.dao.BatteryDao
import com.rentouw.data_local.model.BatteryLogEntity

@Database(entities = [BatteryLogEntity::class], version = 1, exportSchema = false)
abstract class RosmertaDatabase : RoomDatabase() {
    abstract fun batteryDao(): BatteryDao
}