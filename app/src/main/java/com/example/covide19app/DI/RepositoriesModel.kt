package com.example.covide19app.DI

import com.example.covide19app.Repository.CountriesRepository
import com.example.covide19app.Repository.InfectedPoapleRepository
import com.example.covide19app.Repository.PlacesRepository
import com.example.covide19app.Repository.TotalCasesRepository
import com.example.covide19app.Retrofit.CountryRetrofitInterface
import com.example.covide19app.Retrofit.EntityMapper.CountryMapper
import com.example.covide19app.Retrofit.EntityMapper.PlacesMapper
import com.example.covide19app.Retrofit.EntityMapper.TotalCasesMapper
import com.example.covide19app.Retrofit.RetrofitInterface
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
    @Singleton
    @Provides
    fun provideCountyRepo(
            countryRetrofitInterface: CountryRetrofitInterface,
            countryMapper: CountryMapper
    ):CountriesRepository{
        return CountriesRepository(countryRetrofitInterface,countryMapper)
    }
    @Singleton
    @Provides
    fun providePlacesRepo(
            retrofitInterface: RetrofitInterface,
            placesMapper: PlacesMapper
    ):PlacesRepository{
        return PlacesRepository(retrofitInterface,placesMapper)
    }
    @Singleton
    @Provides
    fun provideInfectedPoapleRepo(
            retrofitInterface: RetrofitInterface,

    ):InfectedPoapleRepository{
        return InfectedPoapleRepository(retrofitInterface)
    }

}