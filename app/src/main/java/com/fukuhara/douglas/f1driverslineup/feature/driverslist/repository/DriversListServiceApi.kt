package com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository

import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.dto.DriversListDto
import retrofit2.http.GET

interface DriversListServiceApi {
    @GET("2022.json")
    suspend fun getDriversList(): DriversListDto
}
