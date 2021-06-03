package com.cap0097.ahuahuapp.di

import com.cap0097.ahuahuapp.domain.repository.Repository
import com.cap0097.ahuahuapp.domain.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideRepository(repository: RepositoryImpl) : Repository
}