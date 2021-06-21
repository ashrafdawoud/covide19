package com.example.covide19app.Retrofit

import com.example.covide19app.Retrofit.Entities.PlacesRetrofitEntity
import retrofit2.http.GET
import retrofit2.http.Headers

interface RetrofitInterface {
    @Headers(
            "X-Parse-Application-Id:xZ6X6aaZPAEfUO4vaDXViwBDqyjJnUlpHM3Dvyov",
            "X-Parse-REST-API-Key:ydcv4AeewnqriN10gkTgQ8IysZ95NCdnE9Na54pR"
    )
    @GET("Places")
    suspend fun getplaces():PlacesRetrofitEntity
}