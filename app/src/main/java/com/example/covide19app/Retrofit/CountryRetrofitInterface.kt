package com.example.covide19app.Retrofit

import com.example.covide19app.Retrofit.Entities.CountryEntity
import com.example.covide19app.Retrofit.Entities.TotalCaseEntity
import retrofit2.http.GET
import retrofit2.http.Headers


interface CountryRetrofitInterface {
    @Headers(
            "x-rapidapi-key:772bfece10msh825aef9e96289a6p1d9b32jsn473c594ecd7d",
            "x-rapidapi-host:covid-19-tracking.p.rapidapi.com",
            "useQueryString:true"
    )
    @GET("v1")
    suspend fun getCountryData(): List<CountryEntity>
}