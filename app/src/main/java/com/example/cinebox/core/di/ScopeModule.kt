package com.example.cinebox.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ScopeModule {

    @Provides
    @Singleton
    fun provideCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.IO)

}