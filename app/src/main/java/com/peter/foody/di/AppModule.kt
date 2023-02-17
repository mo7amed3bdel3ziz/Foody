package com.peter.foody.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.peter.foody.business.Constants.BASE_URL
import com.peter.foody.business.Constants.BAvaria
import com.peter.foody.business.repositories.abstraction.FoodRepository
import com.peter.foody.business.repositories.implementation.FoodRepositoryImpl
import com.peter.foody.business.usecases.abstraction.FoodUseCase
import com.peter.foody.business.usecases.implementation.FoodUseCaseImpl
import com.peter.foody.data.remote.ApiService
import com.peter.foody.framework.datasource.network.FoodAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

   // @Singleton
   // @Provides
   // fun provideAPI(): FoodAPI = Retrofit.Builder()
   //     .addConverterFactory(GsonConverterFactory.create())
   //     .addCallAdapterFactory(CoroutineCallAdapterFactory())
   //     .baseUrl(BASE_URL)
   //     .build()
   //     .create(FoodAPI::class.java)

    @Singleton
    @Provides
    fun provideAPI():FoodAPI{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .build()
            .create(FoodAPI::class.java)

    }

    @Singleton
    @Provides
    fun provideAPI2():ApiService{
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BAvaria)
            .build()
            .create(ApiService::class.java)

    }

    @Singleton
    @Provides
    fun provideFoodRepository(api: FoodAPI): FoodRepository = FoodRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideFoodUseCases(
        FoodRepository: FoodRepository,
    ): FoodUseCase = FoodUseCaseImpl(FoodRepository)
}