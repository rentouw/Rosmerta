package com.rentouw.lifelogging_battery.model

data class BatteryInfo( // This is slightly different from the Entity, focused on domain needs
    val timestamp: Long,
    val level: Int,
    val isCharging: Boolean
)