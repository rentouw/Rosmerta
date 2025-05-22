package com.rentouw.lifelogging_battery.data.di

import com.rentouw.data_local.dao.BatteryDao
import com.rentouw.lifelogging_battery.data.BatteryRepositoryImpl
import com.rentouw.lifelogging_battery.domain.BatteryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class) // Scoped to ViewModels that use it
object BatteryModule {

    @Provides
    @ViewModelScoped // Or @Singleton
    fun provideBatteryRepository(batteryDao: BatteryDao): BatteryRepository {
        return BatteryRepositoryImpl(batteryDao)
    }
}