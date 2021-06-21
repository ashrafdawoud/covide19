package com.example.covide19app.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PlacesRetrofitEntity (
        @SerializedName("results")
        @Expose
        var results: List<PlacesEntity>
        )
class PlacesEntity(
        @SerializedName("long")
        @Expose
        val long: String,
        @SerializedName("lang")
        @Expose
        val lang: String,
        @SerializedName("type")
        @Expose
        val type: String,
        @SerializedName("address")
        @Expose
        val address: String,
)