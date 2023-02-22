package com.peter.foody.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
//    @Singleton
//    @Provides
//    fun provideDao(db: GawlahDatabase): CityDao = db .cityDao()
//
    // @Singleton
    // @Provides
    // fun provideDao2(db: GawlahDatabase): CityDao = db.hotelDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): BavariaDataBase =
        Room.databaseBuilder(
            context,
            BavariaDataBase::class.java,
            "Bavaria_db"
        )
            .fallbackToDestructiveMigration()
            .build()

}