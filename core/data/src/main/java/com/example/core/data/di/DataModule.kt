package com.example.core.data.di

import com.example.core.data.models.ExampleModelRepository
import com.example.core.data.models.ModelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsExampleModelRepository(model: ExampleModelRepository): ModelRepository
}