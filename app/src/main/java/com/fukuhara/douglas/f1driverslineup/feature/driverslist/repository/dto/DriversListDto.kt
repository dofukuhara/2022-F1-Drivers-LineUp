package com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.dto

import com.google.gson.annotations.SerializedName

data class DriversListDto(
    @SerializedName("season")
    val season: String?,
    @SerializedName("total")
    val total: String?,
    @SerializedName("Drivers")
    val drivers: List<DriverDto>?
)

data class DriverDto(
    @SerializedName("driverId")
    val driverId: String?,
    @SerializedName("permanentNumber")
    val permanentNumber: String?,
    @SerializedName("code")
    val code: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("givenName")
    val givenName: String?,
    @SerializedName("familyName")
    val familyName: String?,
    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,
    @SerializedName("team")
    val team: String?,
    @SerializedName("teamColor")
    val teamColor: String?,
    @SerializedName("nationality")
    val nationality: String?
)
