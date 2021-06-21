package com.example.covide19app.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CountriesModel (
        val Country_text : String?,
        val Last_Update : String?,
        val New_Cases_text : String?,
        val New_Deaths_text : String?,
        val Total_Cases_text : String?,
        val Total_Deaths_text : String?,
        val Total_Recovered_text : String?,
        )