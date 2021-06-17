package com.example.covide19app.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TotalCaseEntity(
        @SerializedName("country")
        @Expose
        val country: String,
        @SerializedName("confirmed")
        @Expose
        val confirmed: String,
        @SerializedName("recovered")
        @Expose
        val recovered: String,
        @SerializedName("critical")
        @Expose
        val critical: String,
        @SerializedName("deaths")
        @Expose
        val deaths: String,

        )