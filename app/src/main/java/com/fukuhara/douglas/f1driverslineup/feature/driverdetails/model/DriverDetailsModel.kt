package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.model

data class DriverDetailsModel(
    val season: String,
    val round: Int,
    val position: Int,
    val points: Int,
    val grid: Int,
    val laps: String,
    val status: String,
    val fastestLap: String,
    val fastestLapRank: String
)
