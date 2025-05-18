package com.rentouw.feature_lifelogging_battery.model

data class BatteryInfo( // This is slightly different from the Entity, focused on domain needs
    val timestamp: Long,
    val level: Int,
    val isCharging: Boolean
)