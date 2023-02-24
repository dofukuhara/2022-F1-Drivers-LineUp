package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.dto

import com.google.gson.annotations.SerializedName

data class DriverDetailsDto(
    @SerializedName("driverId") val driverId: String?,
    @SerializedName("Races") val races: List<RacesDto>?
)

data class RacesDto(
    @SerializedName("season") val season: String?,
    @SerializedName("round") val round: String?,
    @SerializedName("url") val wikiUrl: String?,
    @SerializedName("raceName") val raceName: String?,
    @SerializedName("Circuit") val circuit: CircuitDto?,
    @SerializedName("date") val date: String?,
    @SerializedName("time") val time: String?,
    @SerializedName("Results") val results: List<ResultDto>?
)

data class CircuitDto(
    @SerializedName("circuitId") val circuitId: String?,
    @SerializedName("url") val wikiUrl: String?,
    @SerializedName("circuitName") val circuitName: String?,
    @SerializedName("Location") val location: LocationDto?
)

data class LocationDto(
    @SerializedName("lat") val lat: String?,
    @SerializedName("long") val long: String?,
    @SerializedName("locality") val locality: String?,
    @SerializedName("country") val country: String?
)

data class ResultDto(
    @SerializedName("number") val number: String?,
    @SerializedName("position") val position: String?,
    @SerializedName("positionText") val positionText: String?,
    @SerializedName("points") val points: String?,
    @SerializedName("grid") val grid: String?,
    @SerializedName("laps") val laps: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("FastestLap") val fastestLap: FastestLapDto?
)

data class FastestLapDto(
    @SerializedName("rank") val rank: String?,
    @SerializedName("lap") val lap: String?,
    @SerializedName("Time") val time: TimeDto?
)

data class TimeDto(
    @SerializedName("time") val time: String?
)
