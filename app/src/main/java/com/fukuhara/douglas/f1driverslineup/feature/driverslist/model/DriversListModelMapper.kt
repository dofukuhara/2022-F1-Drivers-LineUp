package com.fukuhara.douglas.f1driverslineup.feature.driverslist.model

import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.DriverListMapperType
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.dto.DriversListDto
import com.fukuhara.douglas.lib.common.arch.Result
import com.fukuhara.douglas.lib.common.arch.failure
import com.fukuhara.douglas.lib.common.arch.success
import com.fukuhara.douglas.lib.common.exception.ModelParserException

class DriversListModelMapper : DriverListMapperType {
    override fun transform(dtoData: DriversListDto): Result<Throwable, DriversListModel> {
        try {
            val season = dtoData.season ?: throw ModelParserException(" [Missing season field]")
            val drivers = dtoData.drivers?.map { driverDto ->
                val driverId = driverDto.driverId ?: throw ModelParserException(" [Missing Drivers.driverId field]")
                val permanentNumber = driverDto.permanentNumber ?: throw ModelParserException(" [Missing Drivers.permanentNumber field]")
                val imageUrl = driverDto.imageUrl ?: throw ModelParserException(" [Missing Drivers.imageUrl field]")
                val givenName = driverDto.givenName ?: throw ModelParserException(" [Missing Drivers.givenName field]")
                val familyName = driverDto.familyName ?: throw ModelParserException(" [Missing Drivers.familyName field]")
                val dateOfBirth = driverDto.dateOfBirth ?: throw ModelParserException(" [Missing Drivers.dateOfBirth field]")
                val nationality = driverDto.nationality ?: throw ModelParserException(" [Missing Drivers.nationality field]")

                DriverModel(driverId, permanentNumber, imageUrl, givenName, familyName, dateOfBirth, nationality)
            } ?: throw ModelParserException(" [Missing Drivers field]")

            val resultModelList = DriversListModel(season, drivers)

            return resultModelList.success()
        } catch (throwable: Throwable) {
            val customMessage = if (throwable is ModelParserException) {
                throwable.message
            } else {
                ""
            }

            return ModelParserException("Failed to Parse DriversListDto to DriversListModel$customMessage").failure()
        }
    }
}
