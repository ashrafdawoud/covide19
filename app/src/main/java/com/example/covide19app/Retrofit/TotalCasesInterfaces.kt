package com.example.covide19app.Retrofit

import com.example.covide19app.Retrofit.Entities.TotalCaseEntity
import retrofit2.http.GET
import retrofit2.http.Headers

interface TotalCasesInterfaces {
    @Headers(
            "x-rapidapi-key:772bfece10msh825aef9e96289a6p1d9b32jsn473c594ecd7d",
            "x-rapidapi-host:covid-19-data.p.rapidapi.com"
    )
    @GET("country?name=egypt")
    suspend fun getGeneralData(): List<TotalCaseEntity>
}