package com.example.unitconverter.di

import com.example.unitconverter.data.ConversionRepository
import com.example.unitconverter.data.ConversionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideConversionRepository(): ConversionRepository {
        return ConversionRepositoryImpl()
    }
}
