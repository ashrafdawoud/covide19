package com.example.covide19app.DI

import com.example.covide19app.Retrofit.CountryRetrofitInterface
import com.example.covide19app.Retrofit.RetrofitInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
@Module
@InstallIn(ApplicationComponent::class)
object MainRetrofit {
    @Singleton
    @Provides
    @Named("retrofitmain")
    fun provideGsonBuilder(): Gson {
        return GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
    }

    @Singleton
    @Provides
    @Named("retrofitmain")
    fun provideRetrofit(@Named("retrofit1") gson: Gson): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl("https://parseapi.back4app.com/classes/")
                .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBlogService(@Named("retrofitmain") retrofit: Retrofit.Builder): RetrofitInterface {
        return retrofit
                .build()
                .create(RetrofitInterface::class.java)
    }
}