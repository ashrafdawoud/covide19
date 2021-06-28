package com.example.covide19app.DI

import com.example.covide19app.Repository.*
import com.example.covide19app.Retrofit.CountryRetrofitInterface
import com.example.covide19app.Retrofit.EntityMapper.*
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
            infectedPoapleMapper: InfectedPoapleMapper
    ):InfectedPoapleRepository{
        return InfectedPoapleRepository(retrofitInterface,infectedPoapleMapper)
    }
    @Singleton
    @Provides
    fun provideAdvicesRepository(
            retrofitInterface: RetrofitInterface,
            advicesMapper: AdvicesMapper
    ): AdvicesRepository {
        return AdvicesRepository(retrofitInterface,advicesMapper)
    }

}