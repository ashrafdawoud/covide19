package com.example.covide19app.Retrofit.Entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PopularLawersRetrofitEntity (
    @SerializedName("results")
    @Expose
    val results:List<PopularLawersitEntity>,
)
class PopularLawersitEntity(
    @SerializedName("objectId")
    @Expose
    val objectId:String,
    @SerializedName("name")
    @Expose
    val name:String,
    @SerializedName("address")
    @Expose
    val address:String,
    @SerializedName("exp")
    @Expose
    val exp:String,
    @SerializedName("discreiption")
    @Expose
    val discreiption:String,
    @SerializedName("image")
    @Expose
    val image:String,
    @SerializedName("long")
    @Expose
    val long:String,
    @SerializedName("lat")
    @Expose
    val lat:String,
)