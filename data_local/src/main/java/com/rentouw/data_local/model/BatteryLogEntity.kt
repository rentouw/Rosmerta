package com.rentouw.data_local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar

@Entity(tableName = "battery_logs")
data class BatteryLogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val timestamp: Long,
    val level: Int, // Percentage
    val isCharging: Boolean,
    val calender: Calendar
    // val temperature: Float, // Add later if needed
    // val voltage: Int // Add later if needed
)