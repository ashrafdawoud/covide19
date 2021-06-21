package com.example.covide19app.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountryEntity (
        @SerializedName("Country_text")
        @Expose
        val Country_text : String,
        @SerializedName("Last Update")
        @Expose
        val Last_Update : String,
        @SerializedName("New Cases_text")
        @Expose
        val New_Cases_text : String,
        @SerializedName("New Deaths_text")
        @Expose
        val New_Deaths_text : String,
        @SerializedName("Total Cases_text")
        @Expose
        val Total_Cases_text : String,
        @SerializedName("Total Deaths_text")
        @Expose
        val Total_Deaths_text : String,
        @SerializedName("Total Recovered_text")
        @Expose
        val Total_Recovered_text : String,
        )