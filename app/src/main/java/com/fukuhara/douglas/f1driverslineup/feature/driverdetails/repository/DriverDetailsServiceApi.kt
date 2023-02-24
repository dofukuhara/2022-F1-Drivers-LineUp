package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository

import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.dto.DriverDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DriverDetailsServiceApi {

    @GET("{driverId}.json")
    suspend fun getDriverDetails(@Path("driverId") driverId: String): DriverDetailsDto
}
