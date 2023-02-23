package com.fukuhara.douglas.f1driverslineup.feature.driverslist.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class DriversListModel(
    val season: String,
    val drivers: List<DriverModel>
)

@Parcelize
data class DriverModel(
    val driverId: String,
    val permanentNumber: Int,
    val imageUrl: String,
    private val givenName: String,
    private val familyName: String,
    val dateOfBirth: String,
    val team: String,
    val teamColor: String,
    val nationality: String
) : Parcelable {
    val name = "$givenName $familyName"
    val teamAndNumber = "$team - #$permanentNumber"
    val countryFlagUrl = "https://flagsapi.com/$nationality/flat/24.png"
}
