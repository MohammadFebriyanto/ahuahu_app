package com.cap0097.ahuahuapp.di

import com.cap0097.ahuahuapp.domain.repository.NetworkRepository
import com.cap0097.ahuahuapp.domain.repository.NetworkRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideRepository(repository: NetworkRepositoryImpl) : NetworkRepository
}