package com.cap0097.ahuahuapp.di

import android.content.Context
import androidx.room.Room
import com.cap0097.ahuahuapp.utils.Constants
import com.cap0097.ahuahuapp.data.local.AppDatabase
import com.cap0097.ahuahuapp.data.local.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context, AppDatabase::class.java, Constants.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideHistoryDao(database: AppDatabase): HistoryDao = database.historyDao()
}