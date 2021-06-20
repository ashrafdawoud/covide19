package com.example.covide19app.DI

import com.example.covide19app.Retrofit.RetrofitInterface
import com.example.covide19app.Retrofit.TotalCasesInterfaces
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AllCountriesRetrofit {
    @Singleton
    @Provides
    @Named("retrofit1")
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
    }

    @Singleton
    @Provides
    @Named("retrofit1")
    fun provideRetrofit(@Named("retrofit1") gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl("https://covid-19-tracking.p.rapidapi.com/v1")
                .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBlogService(@Named("retrofit1") retrofit: Retrofit.Builder): RetrofitInterface {
        return retrofit
                .build()
                .create(RetrofitInterface::class.java)
    }
}