package com.pru.kmmskelton.android.di

import com.pru.kmmskelton.data.remote.APIService
import com.pru.kmmskelton.data.repository.RepositorySdk
import com.pru.kmmskelton.domain.usecases.PatientUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAPIService(): APIService = APIService()

    @Singleton
    @Provides
    fun provideRepositorySdk(apiService: APIService): RepositorySdk = RepositorySdk(apiService)

    @Singleton
    @Provides
    fun providePatientUseCase(repositorySdk: RepositorySdk): PatientUseCase =
        PatientUseCase(repositorySdk)

}