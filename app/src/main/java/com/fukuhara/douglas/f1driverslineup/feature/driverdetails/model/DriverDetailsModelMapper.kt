package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.model

import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.DriverDetailsMapperType
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.dto.DriverDetailsDto
import com.fukuhara.douglas.lib.common.arch.Result
import com.fukuhara.douglas.lib.common.arch.failure
import com.fukuhara.douglas.lib.common.arch.success
import com.fukuhara.douglas.lib.common.exception.ModelParserException
import com.fukuhara.douglas.lib.common.exception.NoDataFoundException
import com.fukuhara.douglas.lib.common.logger.AppLogger

/*
    skipElementIfFailedToParseDriver:
        TRUE: ModelMapper will perform a 1:1 transformation. In case that at least one mandatory field required by DriverModel is missing from DTO, then we will invalidate the entire obj.
        FALSE : ModelMapper will perform (N)Dto : (M)Model, where N>=M. In case that at least one mandatory field required by DriverDetailsModel is missing from DTO, then this element will be skipped from the mapping
 */
class DriverDetailsModelMapper(
    private val skipElementIfFailedToParseDriver: Boolean = false,
    private val appLogger: AppLogger
) : DriverDetailsMapperType {
    override fun transform(dtoData: DriverDetailsDto): Result<Throwable, List<DriverDetailsModel>> {
        try {
            val driverRaceListModel = dtoData.races?.mapNotNull { raceDto ->
                val season = raceDto.season ?: if (skipElementIfFailedToParseDriver) { null } else return generateMissingFieldException("Missing Races.season")
                val round = raceDto.round?.toIntOrNull() ?: if (skipElementIfFailedToParseDriver) { null } else return generateMissingFieldException("Missing Races.round")
                val position = raceDto.results?.get(0)?.position?.toIntOrNull() ?: if (skipElementIfFailedToParseDriver) { null } else return generateMissingFieldException("Missing Races.Results.position")
                val points = raceDto.results?.get(0)?.points?.toIntOrNull() ?: if (skipElementIfFailedToParseDriver) { null } else return generateMissingFieldException("Missing Races.Results.points")
                val grid = raceDto.results?.get(0)?.grid?.toIntOrNull() ?: if (skipElementIfFailedToParseDriver) { null } else return generateMissingFieldException("Missing Races.Results.grid")
                val laps = raceDto.results?.get(0)?.laps ?: if (skipElementIfFailedToParseDriver) { null } else return generateMissingFieldException("Missing Races.Results.laps")
                val status = raceDto.results?.get(0)?.status ?: if (skipElementIfFailedToParseDriver) { null } else return generateMissingFieldException("Missing Races.Results.status")
                val fastestLap = raceDto.results?.get(0)?.fastestLap?.time?.time ?: if (skipElementIfFailedToParseDriver) { null } else return generateMissingFieldException("Missing Races.Results.FastestLap.Time.time")
                val fastestLapRank = raceDto.results?.get(0)?.fastestLap?.rank ?: if (skipElementIfFailedToParseDriver) { null } else return generateMissingFieldException("Missing Races.Results.FastestLap.rank")

                if (season == null || round == null || position == null || points == null || grid == null || laps == null || status == null || fastestLap == null || fastestLapRank == null) {
                    appLogger.e(tag = "DriverDetailsModelMapper", message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [$raceDto]")
                    null
                } else {
                    DriverDetailsModel(season, round, position, points, grid, laps, status, fastestLap, fastestLapRank)
                }
            } ?: return NoDataFoundException("The driver races list is empty").failure()

            return driverRaceListModel.success()
        } catch (throwable: Throwable) {
            return generateMissingFieldException(throwable.message)
        }
    }

    private fun generateMissingFieldException(additionalMessage: String?): Result.Failure<ModelParserException> {
        return ModelParserException("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [$additionalMessage]").failure()
    }
}
