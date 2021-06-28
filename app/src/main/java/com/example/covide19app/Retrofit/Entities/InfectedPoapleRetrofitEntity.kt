package com.example.covide19app.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class InfectedPoapleRetrofitEntity (
        @SerializedName("results")
        @Expose
        var results: List<InfectedPoapleEntity>
)
class InfectedPoapleEntity(
        @SerializedName("objectId")
        @Expose
        val objectId : String,
        @SerializedName("gander")
        @Expose
        val gander : String,
        @SerializedName("symptoms")
        @Expose
        val symptoms : String,
        @SerializedName("contant_number")
        @Expose
        val contant_number : String,
        @SerializedName("userid")
        @Expose
        val userid : String,
        @SerializedName("age")
        @Expose
        val age : String,
        @SerializedName("lat")
        @Expose
        val lat : String,
        @SerializedName("long")
        @Expose
        val long : String,
)