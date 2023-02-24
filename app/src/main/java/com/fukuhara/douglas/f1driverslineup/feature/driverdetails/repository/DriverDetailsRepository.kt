package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository

import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.model.DriverDetailsModel
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.dto.DriverDetailsDto
import com.fukuhara.douglas.lib.common.arch.Result
import com.fukuhara.douglas.lib.common.arch.failure
import com.fukuhara.douglas.lib.common.arch.success
import com.fukuhara.douglas.lib.common.exception.NoDataFoundException
import com.fukuhara.douglas.lib.common.model.ModelMapper

interface DriverDetailsRepository {
    suspend fun getDriverDetails(driverId: String): Result<Throwable, List<DriverDetailsModel>>
}

internal class DriverDetailsRemoteRepository(
    private val api: DriverDetailsServiceApi,
    private val mapper: DriverDetailsMapperType
) : DriverDetailsRepository {
    override suspend fun getDriverDetails(driverId: String): Result<Throwable, List<DriverDetailsModel>> {
        try {
            val dtoResult = api.getDriverDetails(driverId)
            val modelResult = mapper.transform(dtoResult)

            modelResult.fold(
                failure = { throwable -> return throwable.failure() },
                success = { driverDetailsModelList ->
                    if (driverDetailsModelList.isEmpty()) {
                        return NoDataFoundException("Driver Races List returned was empty").failure()
                    } else {
                        return driverDetailsModelList.success()
                    }
                }
            )
        } catch (throwable: Throwable) {
            return NoDataFoundException("Failed to retrieve data from network").failure()
        }
    }
}

typealias DriverDetailsMapperType = ModelMapper<DriverDetailsDto, Result<Throwable, List<DriverDetailsModel>>>
