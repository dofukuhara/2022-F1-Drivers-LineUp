package com.fukuhara.douglas.f1driverslineup.feature.driverslist.model

data class DriversListModel(
    val season: String,
    val drivers: List<DriverModel>
)

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
) {
    val name = "$givenName $familyName"
    val teamAndNumber = "$team - #$permanentNumber"
    val countryFlagUrl = "https://flagsapi.com/$nationality/flat/24.png"
}
