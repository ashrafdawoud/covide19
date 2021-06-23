package com.example.covide19app.Retrofit

import com.example.covide19app.Retrofit.Entities.PlacesRetrofitEntity
import com.example.covide19app.Retrofit.Entities.SucssesEntity
import retrofit2.http.*

interface RetrofitInterface {
    @Headers(
            "X-Parse-Application-Id:xZ6X6aaZPAEfUO4vaDXViwBDqyjJnUlpHM3Dvyov",
            "X-Parse-REST-API-Key:ydcv4AeewnqriN10gkTgQ8IysZ95NCdnE9Na54pR"
    )
    @GET("Places")
    suspend fun getplaces():PlacesRetrofitEntity
    @Headers(
            "X-Parse-Application-Id:xZ6X6aaZPAEfUO4vaDXViwBDqyjJnUlpHM3Dvyov",
            "X-Parse-REST-API-Key:ydcv4AeewnqriN10gkTgQ8IysZ95NCdnE9Na54pR",
            "Accept: application/json"
    )
    @FormUrlEncoded
    @POST("InfectedPoeple")
    suspend fun postInfected(@FieldMap options:Map<String, String>) : SucssesEntity

}