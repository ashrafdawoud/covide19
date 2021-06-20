package com.example.covide19app.DI

import com.example.covide19app.Repository.TotalCasesRepository
import com.example.covide19app.Retrofit.EntityMapper.TotalCasesMapper
import com.example.covide19app.Retrofit.TotalCasesInterfaces
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoriesModel {
    @Singleton
    @Provides
    fun provideTotalCasesRepo(
            totalCasesInterfaces: TotalCasesInterfaces,
            totalCasesMapper: TotalCasesMapper
    ):TotalCasesRepository{
        return TotalCasesRepository(totalCasesInterfaces,totalCasesMapper)
    }

}