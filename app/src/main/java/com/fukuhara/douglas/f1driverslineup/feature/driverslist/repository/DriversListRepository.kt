package com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository

import com.fukuhara.douglas.f1driverslineup.feature.driverslist.model.DriversListModel
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.dto.DriversListDto
import com.fukuhara.douglas.lib.common.arch.Result
import com.fukuhara.douglas.lib.common.arch.failure
import com.fukuhara.douglas.lib.common.arch.success
import com.fukuhara.douglas.lib.common.exception.NoDataFoundException
import com.fukuhara.douglas.lib.common.model.ModelMapper

interface DriversListRepository {
    suspend fun getDriversList(skipElementIfFailedToParseDriver: Boolean): Result<Throwable, DriversListModel>
}

internal class DriversListRemoteRepository(
    private val api: DriversListServiceApi,
    private val modelMapper: DriverListMapperType
) : DriversListRepository {

    override suspend fun getDriversList(skipElementIfFailedToParseDriver: Boolean): Result<Throwable, DriversListModel> {
        try {
            val responseDto = api.getDriversList()
            val responseModel = modelMapper.transform(responseDto, skipElementIfFailedToParseDriver)

            responseModel.fold(
                failure = { throwable -> return throwable.failure() },
                success = { driversListModel ->
                    if (driversListModel.drivers.isEmpty()) {
                        return NoDataFoundException("Drivers List returned was empty").failure()
                    } else {
                        return driversListModel.success()
                    }
                }
            )
        } catch (throwable: Throwable) {
            return NoDataFoundException("Failed to retrieve data from network").failure()
        }
    }
}

typealias DriverListMapperType = ModelMapper<DriversListDto, Result<Throwable, DriversListModel>>
