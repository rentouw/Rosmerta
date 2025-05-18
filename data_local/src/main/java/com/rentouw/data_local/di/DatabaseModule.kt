package com.rentouw.data_local.di

import android.content.Context
import androidx.room.Room
import com.rentouw.data_local.dao.BatteryDao
import com.rentouw.data_local.database.RosmertaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRosmertaDatabase(@ApplicationContext appContext: Context): RosmertaDatabase {
        return Room.databaseBuilder(
            appContext,
            RosmertaDatabase::class.java,
            "rosmerta_database"
        ).build()
    }

    @Provides
    fun provideBatteryDao(database: RosmertaDatabase): BatteryDao {
        return database.batteryDao()
    }
}