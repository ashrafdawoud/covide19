package com.example.covide19app.Model

import java.io.Serializable

data class DoctorsModel (
    val objectId:String,
    val name:String,
    val address:String,
    val exp:String,
    val discreiption:String,
    val image:String,
    val long:String,
    val lat:String,
) : Serializable