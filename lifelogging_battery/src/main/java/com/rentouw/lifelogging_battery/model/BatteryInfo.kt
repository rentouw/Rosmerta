package com.rentouw.lifelogging_battery.model

import java.util.Calendar

data class BatteryInfo( // This is slightly different from the Entity, focused on domain needs
    val timestamp: Long,
    val level: Int,
    val isCharging: Boolean,
    val calendar: Calendar = Calendar.getInstance().apply { timeInMillis = timestamp }
)