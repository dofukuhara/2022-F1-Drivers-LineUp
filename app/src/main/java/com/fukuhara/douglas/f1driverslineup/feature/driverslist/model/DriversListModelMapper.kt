package com.fukuhara.douglas.f1driverslineup.feature.driverslist.model

import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.DriverListMapperType
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.dto.DriversListDto
import com.fukuhara.douglas.lib.common.arch.Result
import com.fukuhara.douglas.lib.common.arch.failure
import com.fukuhara.douglas.lib.common.arch.success
import com.fukuhara.douglas.lib.common.exception.ModelParserException
import com.fukuhara.douglas.lib.common.logger.AppLogger

/*
    skipElementIfFailedToParseDriver:
        TRUE: ModelMapper will perform a 1:1 transformation. In case that at least one mandatory field required by DriverModel is missing from DTO, then we will invalidate the entire obj.
        FALSE : ModelMapper will perform (N)Dto : (M)Model, where N>=M. In case that at least one mandatory field required by DriverModel is missing from DTO, then this element will be skipped from the mapping
 */
class DriversListModelMapper(
    private val skipElementIfFailedToParseDriver: Boolean = false,
    private val appLogger: AppLogger
) : DriverListMapperType {
    override fun transform(dtoData: DriversListDto): Result<Throwable, DriversListModel> {
        try {
            val season = dtoData.season ?: throw ModelParserException(" [Missing season field]")
            val drivers = dtoData.drivers?.mapNotNull { driverDto ->
                val driverId = driverDto.driverId ?: if (skipElementIfFailedToParseDriver) { null } else { throw ModelParserException(" [Missing Drivers.driverId field]") }
                val imageUrl = driverDto.imageUrl ?: if (skipElementIfFailedToParseDriver) { null } else { throw ModelParserException(" [Missing Drivers.imageUrl field]") }
                val givenName = driverDto.givenName ?: if (skipElementIfFailedToParseDriver) { null } else { throw ModelParserException(" [Missing Drivers.givenName field]") }
                val familyName = driverDto.familyName ?: if (skipElementIfFailedToParseDriver) { null } else { throw ModelParserException(" [Missing Drivers.familyName field]") }
                val dateOfBirth = driverDto.dateOfBirth ?: if (skipElementIfFailedToParseDriver) { null } else { throw ModelParserException(" [Missing Drivers.dateOfBirth field]") }
                val team = driverDto.team ?: if (skipElementIfFailedToParseDriver) { null } else { throw ModelParserException(" [Missing Drivers.team field]") }
                val teamColor = driverDto.teamColor ?: if (skipElementIfFailedToParseDriver) { null } else { throw ModelParserException(" [Missing Drivers.teamColor field]") }
                val nationality = driverDto.nationality ?: if (skipElementIfFailedToParseDriver) { null } else { throw ModelParserException(" [Missing Drivers.nationality field]") }

                val permanentNumber = if (driverDto.permanentNumber?.matches("\\d*".toRegex()) == true) {
                    driverDto.permanentNumber.toInt()
                } else if (skipElementIfFailedToParseDriver) {
                    null
                } else {
                    throw ModelParserException(" [Missing or invalid Drivers.permanentNumber field]")
                }

                if (driverId == null || permanentNumber == null || imageUrl == null || givenName == null || familyName == null || dateOfBirth == null || team == null || teamColor == null || nationality == null) {
                    appLogger.e(tag = "DriversListModelMapper", message = "Skipping this element from the list, as it is missing a mandatory field for DriverModel - [$driverDto]")
                    null
                } else {
                    DriverModel(driverId, permanentNumber, imageUrl, givenName, familyName, dateOfBirth, team, teamColor, nationality)
                }
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
