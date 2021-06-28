package com.example.covide19app.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class AdvicesRetrofitEntity (
        @SerializedName("results")
        @Expose
        var results: List<AdvicesEntity>
)
class AdvicesEntity (
        @SerializedName("title")
        @Expose
        val title:String,
        @SerializedName("topic")
        @Expose
        val topic:String,
        @SerializedName("image")
        @Expose
        val image:String,
)