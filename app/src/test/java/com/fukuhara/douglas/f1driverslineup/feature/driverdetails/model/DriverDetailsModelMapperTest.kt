package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.model

import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.DriverDetailsMapperType
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.dto.DriverDetailsDto
import com.fukuhara.douglas.lib.common.arch.Result
import com.fukuhara.douglas.lib.common.exception.ModelParserException
import com.fukuhara.douglas.lib.common.exception.NoDataFoundException
import com.fukuhara.douglas.lib.common.logger.AppLogger
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class DriverDetailsModelMapperTest {

    private lateinit var mapper: DriverDetailsMapperType

    private val appLoggerMock: AppLogger = mockk(relaxed = true)

    @Before
    fun setup() {
        mapper = DriverDetailsModelMapper(appLoggerMock)
    }

    @Test
    fun `given A Complete Single Race Dto Element, When Transforming DTO into Model Non-Skippable, Then A Single Race Model Element Must Be Returned`() {
        // Given
        val completeSingleRaceDto = DriverDetailsDtoHelper.generateCompleteSingleRaceDto()

        // When
        val modelResult = mapper.transform(completeSingleRaceDto)

        // Then
        assertThat(
            "Given a complete DTO element, then ModelMapper must map it into Model properly",
            modelResult is Result.Success
        )
        assertThat(
            "List of races must be 1",
            (modelResult as Result.Success).model.size,
            `is`(1)
        )
        assertThat(
            "SEASON info must be properly mapped",
            modelResult.model[0].season,
            `is`("2022")
        )
        assertThat(
            "ROUND info must be properly mapped from String to Int",
            modelResult.model[0].round,
            `is`(22)
        )
        assertThat(
            "POSITION info must be properly mapped from String to Int",
            modelResult.model[0].position,
            `is`(13)
        )
        assertThat(
            "POINTS info must be properly mapped from String to Int",
            modelResult.model[0].points,
            `is`(0)
        )
        assertThat(
            "GRID info must be properly mapped from String to Int",
            modelResult.model[0].grid,
            `is`(19)
        )
        assertThat(
            "LAPS info must be properly mapped",
            modelResult.model[0].laps,
            `is`("57")
        )
        assertThat(
            "STATUS info must be properly mapped",
            modelResult.model[0].status,
            `is`("+1 Lap")
        )
        assertThat(
            "FASTESTLAP info must be properly mapped",
            modelResult.model[0].fastestLap,
            `is`("1:29.939")
        )
        assertThat(
            "FASTESTLAPRANK info must be properly mapped",
            modelResult.model[0].fastestLapRank,
            `is`("13")
        )
    }

    @Test
    fun `give A Complete Three Races List Dto Element, When Transforming Dto Into Model Non-Skippable, Then A Model With Three Races Elements Must Be Returned`() {
        // Given
        val completeThreeRacesDto = DriverDetailsDtoHelper.generateCompleteThreeRacesDto()

        // When
        val modelResult = mapper.transform(completeThreeRacesDto)

        // Then
        assertThat(
            "Given a complete DTO element, then ModelMapper must map it into Model properly",
            modelResult is Result.Success
        )
        assertThat(
            "List of races must be 3",
            (modelResult as Result.Success).model.size,
            `is`(3)
        )
    }

    @Test
    fun `given A Complete Two Elements And One Incomplete, When Transforming Dto Into Model Non-Skippable, Then A Result FAILURE With ModelParserException Must Be Returned`() {
        // Given
        val twoCompleteOneMissingLapsDto = DriverDetailsDtoHelper.generateTwoCompleteOneMissingLapsDto()

        // When
        val modelResult = mapper.transform(twoCompleteOneMissingLapsDto)

        // Then
        assertThat(
            "Given a Two Complete Races but One Missing Laps DTO element, then ModelMapper must return a Result.FAILURE response",
            modelResult is Result.Failure
        )
        assertThat(
            "When at least one mandatory attribute is missing, and Mapper is setup as Non-Skippable, then return a ModelParserException exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that LAPS mandatory field is missing, then the custom exception must inform which field was missing",
            modelResult.throwable.message,
            `is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.Results.laps]")
        )
    }

    @Test
    fun `given A Complete Two Elements And One Incomplete, When Transforming Dto Into Model Skippable, Then The Two Parsable Race Elements Must Be Returned`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val twoCompleteOneMissingLapsDto = DriverDetailsDtoHelper.generateTwoCompleteOneMissingLapsDto()

        // When
        val modelResult = mapper.transform(twoCompleteOneMissingLapsDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given A Two Complete and One Incomplete DTO elements, Then ModelMapper can return a Result Success response",
            modelResult is Result.Success
        )
        assertThat(
            "ModelMapper could parse 2 out of the 3 DTO entries",
            (modelResult as Result.Success).model.size,
            `is`(2)
        )
    }

    @Test
    fun `given A Complete Two Elements And One Incomplete, When Transforming Dto Into Model Skippable, Then Mapper Must Log The Incomplete Element With AppLogger`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val twoCompleteOneMissingLapsDto = DriverDetailsDtoHelper.generateTwoCompleteOneMissingLapsDto()

        // When
        mapper.transform(twoCompleteOneMissingLapsDto, skipElementIfFailedToParseDriver)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriverDetailsModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [RacesDto(season=2022, round=21, wikiUrl=http://en.wikipedia.org/wiki/2022_Brazilian_Grand_Prix, raceName=Brazilian Grand Prix, circuit=CircuitDto(circuitId=interlagos, wikiUrl=http://en.wikipedia.org/wiki/Aut%C3%B3dromo_Jos%C3%A9_Carlos_Pace, circuitName=Autódromo José Carlos Pace, location=LocationDto(lat=-23.7036, long=-46.6997, locality=São Paulo, country=Brazil)), date=2022-11-13, time=18:00:00Z, results=[ResultDto(number=23, position=15, positionText=15, points=0, grid=19, laps=null, status=Finished, fastestLap=FastestLapDto(rank=16, lap=49, time=TimeDto(time=1:15.613)))])]"
            )
        }
    }

    @Test
    fun `given A Single Race Dto Missing Season, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val incompleteMissingSeasonDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingSeason()

        // When
        val modelResult = mapper.transform(incompleteMissingSeasonDto)

        // Then
        assertThat(
            "Given a single dto missing Season attribute, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a single dto missing Season attribute, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given a single dto missing Season attribute, then the custom exception must inform which field was invalid",
            modelResult.throwable.message,
            `is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.season]")
        )
    }

    @Test
    fun `given A Single Race Dto Missing Season, When Transforming Dto Into Model Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingSeasonDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingSeason()

        // When
        val modelResult = mapper.transform(incompleteMissingSeasonDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given a single dto missing Season attribute, when mapper is set as skippable, then ModelMapper can return a Success Result",
            modelResult is Result.Success
        )
        assertThat(
            "Given a single dto missing Season attribute, then the result model race list must be zero",
            (modelResult as Result.Success).model.size,
            `is`(0)
        )
    }

    @Test
    fun `given A Single Race Dto Missing Season, When Transforming Dto Into Model Skippable, Then Mapper Must Log This Failure With AppLogger`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingSeasonDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingSeason()

        // When
        mapper.transform(incompleteMissingSeasonDto, skipElementIfFailedToParseDriver)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriverDetailsModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [RacesDto(season=null, round=22, wikiUrl=http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix, raceName=Abu Dhabi Grand Prix, circuit=CircuitDto(circuitId=yas_marina, wikiUrl=http://en.wikipedia.org/wiki/Yas_Marina_Circuit, circuitName=Yas Marina Circuit, location=LocationDto(lat=24.4672, long=54.6031, locality=Abu Dhabi, country=UAE)), date=2022-11-20, time=13:00:00Z, results=[ResultDto(number=23, position=13, positionText=13, points=0, grid=19, laps=57, status=+1 Lap, fastestLap=FastestLapDto(rank=13, lap=40, time=TimeDto(time=1:29.939)))])]"
            )
        }
    }

    @Test
    fun `given A Single Race Dto Missing Round, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val incompleteMissingRoundDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingRound()

        // When
        val modelResult = mapper.transform(incompleteMissingRoundDto)

        // Then
        assertThat(
            "Given a single dto missing Round attribute, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a single dto missing Round attribute, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given a single dto missing Round attribute, then the custom exception must inform which field was invalid",
            modelResult.throwable.message,
            `is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.round]")
        )
    }

    @Test
    fun `given A Single Race Dto Missing Round, When Transforming Dto Into Model Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingRoundDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingRound()

        // When
        val modelResult = mapper.transform(incompleteMissingRoundDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given a single dto missing Round attribute, when mapper is set as skippable, then ModelMapper can return a Success Result",
            modelResult is Result.Success
        )
        assertThat(
            "Given a single dto missing Round attribute, then the result model race list must be zero",
            (modelResult as Result.Success).model.size,
            `is`(0)
        )
    }

    @Test
    fun `given A Single Race Dto Missing Round, When Transforming Dto Into Model Skippable, Then Mapper Must Log This Failure With AppLogger`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingRoundDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingRound()

        // When
        mapper.transform(incompleteMissingRoundDto, skipElementIfFailedToParseDriver)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriverDetailsModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [RacesDto(season=2022, round=null, wikiUrl=http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix, raceName=Abu Dhabi Grand Prix, circuit=CircuitDto(circuitId=yas_marina, wikiUrl=http://en.wikipedia.org/wiki/Yas_Marina_Circuit, circuitName=Yas Marina Circuit, location=LocationDto(lat=24.4672, long=54.6031, locality=Abu Dhabi, country=UAE)), date=2022-11-20, time=13:00:00Z, results=[ResultDto(number=23, position=13, positionText=13, points=0, grid=19, laps=57, status=+1 Lap, fastestLap=FastestLapDto(rank=13, lap=40, time=TimeDto(time=1:29.939)))])]"
            )
        }
    }

    @Test
    fun `given A Single Race Dto Missing Position, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val incompleteMissingPositionDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingPosition()

        // When
        val modelResult = mapper.transform(incompleteMissingPositionDto)

        // Then
        assertThat(
            "Given a single dto missing Position attribute, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a single dto missing Position attribute, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given a single dto missing Position attribute, then the custom exception must inform which field was invalid",
            modelResult.throwable.message,
            `is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.Results.position]")
        )
    }

    @Test
    fun `given A Single Race Dto Missing Position, When Transforming Dto Into Model Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingPositionDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingPosition()

        // When
        val modelResult = mapper.transform(incompleteMissingPositionDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given a single dto missing Position attribute, when mapper is set as skippable, then ModelMapper can return a Success Result",
            modelResult is Result.Success
        )
        assertThat(
            "Given a single dto missing Position attribute, then the result model race list must be zero",
            (modelResult as Result.Success).model.size,
            `is`(0)
        )
    }

    @Test
    fun `given A Single Race Dto Missing Position, When Transforming Dto Into Model Skippable, Then Mapper Must Log This Failure With AppLogger`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingPositionDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingPosition()

        // When
        mapper.transform(incompleteMissingPositionDto, skipElementIfFailedToParseDriver)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriverDetailsModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [RacesDto(season=2022, round=22, wikiUrl=http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix, raceName=Abu Dhabi Grand Prix, circuit=CircuitDto(circuitId=yas_marina, wikiUrl=http://en.wikipedia.org/wiki/Yas_Marina_Circuit, circuitName=Yas Marina Circuit, location=LocationDto(lat=24.4672, long=54.6031, locality=Abu Dhabi, country=UAE)), date=2022-11-20, time=13:00:00Z, results=[ResultDto(number=23, position=null, positionText=13, points=0, grid=19, laps=57, status=+1 Lap, fastestLap=FastestLapDto(rank=13, lap=40, time=TimeDto(time=1:29.939)))])]"
            )
        }
    }

    @Test
    fun `given A Single Race Dto Missing Points, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val incompleteMissingPointsDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingPoints()

        // When
        val modelResult = mapper.transform(incompleteMissingPointsDto)

        // Then
        assertThat(
            "Given a single dto missing Points attribute, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a single dto missing Points attribute, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given a single dto missing Points attribute, then the custom exception must inform which field was invalid",
            modelResult.throwable.message,
            `is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.Results.points]")
        )
    }

    @Test
    fun `given A Single Race Dto Missing Points, When Transforming Dto Into Model Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingPointsDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingPoints()

        // When
        val modelResult = mapper.transform(incompleteMissingPointsDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given a single dto missing Points attribute, when mapper is set as skippable, then ModelMapper can return a Success Result",
            modelResult is Result.Success
        )
        assertThat(
            "Given a single dto missing Points attribute, then the result model race list must be zero",
            (modelResult as Result.Success).model.size,
            `is`(0)
        )
    }

    @Test
    fun `given A Single Race Dto Missing Points, When Transforming Dto Into Model Skippable, Then Mapper Must Log This Failure With AppLogger`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingPointsDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingPoints()

        // When
        mapper.transform(incompleteMissingPointsDto, skipElementIfFailedToParseDriver)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriverDetailsModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [RacesDto(season=2022, round=22, wikiUrl=http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix, raceName=Abu Dhabi Grand Prix, circuit=CircuitDto(circuitId=yas_marina, wikiUrl=http://en.wikipedia.org/wiki/Yas_Marina_Circuit, circuitName=Yas Marina Circuit, location=LocationDto(lat=24.4672, long=54.6031, locality=Abu Dhabi, country=UAE)), date=2022-11-20, time=13:00:00Z, results=[ResultDto(number=23, position=13, positionText=13, points=null, grid=19, laps=57, status=+1 Lap, fastestLap=FastestLapDto(rank=13, lap=40, time=TimeDto(time=1:29.939)))])]"
            )
        }
    }

    @Test
    fun `given A Single Race Dto Missing Grid, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val incompleteMissingGridDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingGrid()

        // When
        val modelResult = mapper.transform(incompleteMissingGridDto)

        // Then
        assertThat(
            "Given a single dto missing Grid attribute, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a single dto missing Grid attribute, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given a single dto missing Grid attribute, then the custom exception must inform which field was invalid",
            modelResult.throwable.message,
            `is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.Results.grid]")
        )
    }

    @Test
    fun `given A Single Race Dto Missing Grid, When Transforming Dto Into Model Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingGridDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingGrid()

        // When
        val modelResult = mapper.transform(incompleteMissingGridDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given a single dto missing Grid attribute, when mapper is set as skippable, then ModelMapper can return a Success Result",
            modelResult is Result.Success
        )
        assertThat(
            "Given a single dto missing Grid attribute, then the result model race list must be zero",
            (modelResult as Result.Success).model.size,
            `is`(0)
        )
    }

    @Test
    fun `given A Single Race Dto Missing Grid, When Transforming Dto Into Model Skippable, Then Mapper Must Log This Failure With AppLogger`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingGridDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingGrid()

        // When
        mapper.transform(incompleteMissingGridDto, skipElementIfFailedToParseDriver)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriverDetailsModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [RacesDto(season=2022, round=22, wikiUrl=http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix, raceName=Abu Dhabi Grand Prix, circuit=CircuitDto(circuitId=yas_marina, wikiUrl=http://en.wikipedia.org/wiki/Yas_Marina_Circuit, circuitName=Yas Marina Circuit, location=LocationDto(lat=24.4672, long=54.6031, locality=Abu Dhabi, country=UAE)), date=2022-11-20, time=13:00:00Z, results=[ResultDto(number=23, position=13, positionText=13, points=0, grid=null, laps=57, status=+1 Lap, fastestLap=FastestLapDto(rank=13, lap=40, time=TimeDto(time=1:29.939)))])]"
            )
        }
    }

    @Test
    fun `given A Single Race Dto Missing Laps, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val incompleteMissingLapsDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingLaps()

        // When
        val modelResult = mapper.transform(incompleteMissingLapsDto)

        // Then
        assertThat(
            "Given a single dto missing Laps attribute, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a single dto missing Laps attribute, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given a single dto missing Laps attribute, then the custom exception must inform which field was invalid",
            modelResult.throwable.message,
            `is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.Results.laps]")
        )
    }

    @Test
    fun `given A Single Race Dto Missing Laps, When Transforming Dto Into Model Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingLapsDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingLaps()

        // When
        val modelResult = mapper.transform(incompleteMissingLapsDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given a single dto missing Laps attribute, when mapper is set as skippable, then ModelMapper can return a Success Result",
            modelResult is Result.Success
        )
        assertThat(
            "Given a single dto missing Laps attribute, then the result model race list must be zero",
            (modelResult as Result.Success).model.size,
            `is`(0)
        )
    }

    @Test
    fun `given A Single Race Dto Missing Laps, When Transforming Dto Into Model Skippable, Then Mapper Must Log This Failure With AppLogger`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingLapsDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingLaps()

        // When
        mapper.transform(incompleteMissingLapsDto, skipElementIfFailedToParseDriver)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriverDetailsModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [RacesDto(season=2022, round=22, wikiUrl=http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix, raceName=Abu Dhabi Grand Prix, circuit=CircuitDto(circuitId=yas_marina, wikiUrl=http://en.wikipedia.org/wiki/Yas_Marina_Circuit, circuitName=Yas Marina Circuit, location=LocationDto(lat=24.4672, long=54.6031, locality=Abu Dhabi, country=UAE)), date=2022-11-20, time=13:00:00Z, results=[ResultDto(number=23, position=13, positionText=13, points=0, grid=19, laps=null, status=+1 Lap, fastestLap=FastestLapDto(rank=13, lap=40, time=TimeDto(time=1:29.939)))])]"
            )
        }
    }

    @Test
    fun `given A Single Race Dto Missing Status, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val incompleteMissingStatusDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingStatus()

        // When
        val modelResult = mapper.transform(incompleteMissingStatusDto)

        // Then
        assertThat(
            "Given a single dto missing Status attribute, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a single dto missing Status attribute, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given a single dto missing Status attribute, then the custom exception must inform which field was invalid",
            modelResult.throwable.message,
            `is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.Results.status]")
        )
    }

    @Test
    fun `given A Single Race Dto Missing Status, When Transforming Dto Into Model Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingStatusDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingStatus()

        // When
        val modelResult = mapper.transform(incompleteMissingStatusDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given a single dto missing Status attribute, when mapper is set as skippable, then ModelMapper can return a Success Result",
            modelResult is Result.Success
        )
        assertThat(
            "Given a single dto missing Status attribute, then the result model race list must be zero",
            (modelResult as Result.Success).model.size,
            `is`(0)
        )
    }

    @Test
    fun `given A Single Race Dto Missing Status, When Transforming Dto Into Model Skippable, Then Mapper Must Log This Failure With AppLogger`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingStatusDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingStatus()

        // When
        mapper.transform(incompleteMissingStatusDto, skipElementIfFailedToParseDriver)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriverDetailsModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [RacesDto(season=2022, round=22, wikiUrl=http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix, raceName=Abu Dhabi Grand Prix, circuit=CircuitDto(circuitId=yas_marina, wikiUrl=http://en.wikipedia.org/wiki/Yas_Marina_Circuit, circuitName=Yas Marina Circuit, location=LocationDto(lat=24.4672, long=54.6031, locality=Abu Dhabi, country=UAE)), date=2022-11-20, time=13:00:00Z, results=[ResultDto(number=23, position=13, positionText=13, points=0, grid=19, laps=57, status=null, fastestLap=FastestLapDto(rank=13, lap=40, time=TimeDto(time=1:29.939)))])]"
            )
        }
    }

    @Test
    fun `given A Single Race Dto Missing FastestLap, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val incompleteMissingFastestLapDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingFastestLap()

        // When
        val modelResult = mapper.transform(incompleteMissingFastestLapDto)

        // Then
        assertThat(
            "Given a single dto missing FastestLap attribute, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a single dto missing FastestLap attribute, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given a single dto missing FastestLap attribute, then the custom exception must inform which field was invalid",
            modelResult.throwable.message,
            `is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.Results.FastestLap.Time.time]")
        )
    }

    @Test
    fun `given A Single Race Dto Missing FastestLap, When Transforming Dto Into Model Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingFastestLapDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingFastestLap()

        // When
        val modelResult = mapper.transform(incompleteMissingFastestLapDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given a single dto missing FastestLap attribute, when mapper is set as skippable, then ModelMapper can return a Success Result",
            modelResult is Result.Success
        )
        assertThat(
            "Given a single dto missing FastestLap attribute, then the result model race list must be zero",
            (modelResult as Result.Success).model.size,
            `is`(0)
        )
    }

    @Test
    fun `given A Single Race Dto Missing FastestLap, When Transforming Dto Into Model Skippable, Then Mapper Must Log This Failure With AppLogger`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingFastestLapDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingFastestLap()

        // When
        mapper.transform(incompleteMissingFastestLapDto, skipElementIfFailedToParseDriver)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriverDetailsModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [RacesDto(season=2022, round=22, wikiUrl=http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix, raceName=Abu Dhabi Grand Prix, circuit=CircuitDto(circuitId=yas_marina, wikiUrl=http://en.wikipedia.org/wiki/Yas_Marina_Circuit, circuitName=Yas Marina Circuit, location=LocationDto(lat=24.4672, long=54.6031, locality=Abu Dhabi, country=UAE)), date=2022-11-20, time=13:00:00Z, results=[ResultDto(number=23, position=13, positionText=13, points=0, grid=19, laps=57, status=+1 Lap, fastestLap=FastestLapDto(rank=13, lap=40, time=TimeDto(time=null)))])]"
            )
        }
    }

    @Test
    fun `given A Single Race Dto Missing FastestLapRank, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val incompleteMissingFastestLapRankDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingFastestLapRank()

        // When
        val modelResult = mapper.transform(incompleteMissingFastestLapRankDto)

        // Then
        assertThat(
            "Given a single dto missing FastestLapRank attribute, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a single dto missing FastestLapRank attribute, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given a single dto missing FastestLapRank attribute, then the custom exception must inform which field was invalid",
            modelResult.throwable.message,
            `is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.Results.FastestLap.rank]")
        )
    }

    @Test
    fun `given A Single Race Dto Missing FastestLapRank, When Transforming Dto Into Model Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingFastestLapRankDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingFastestLapRank()

        // When
        val modelResult = mapper.transform(incompleteMissingFastestLapRankDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given a single dto missing FastestLapRank attribute, when mapper is set as skippable, then ModelMapper can return a Success Result",
            modelResult is Result.Success
        )
        assertThat(
            "Given a single dto missing FastestLapRank attribute, then the result model race list must be zero",
            (modelResult as Result.Success).model.size,
            `is`(0)
        )
    }

    @Test
    fun `given A Single Race Dto Missing FastestLap Rank, When Transforming Dto Into Model Skippable, Then Mapper Must Log This Failure With AppLogger`() {
        // Given
        val skipElementIfFailedToParseDriver = true
        val incompleteMissingFastestLapRankDto = DriverDetailsDtoHelper.generateIncompleteSingleMissingFastestLapRank()

        // When
        mapper.transform(incompleteMissingFastestLapRankDto, skipElementIfFailedToParseDriver)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriverDetailsModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverDetailsModel - [RacesDto(season=2022, round=22, wikiUrl=http://en.wikipedia.org/wiki/2022_Abu_Dhabi_Grand_Prix, raceName=Abu Dhabi Grand Prix, circuit=CircuitDto(circuitId=yas_marina, wikiUrl=http://en.wikipedia.org/wiki/Yas_Marina_Circuit, circuitName=Yas Marina Circuit, location=LocationDto(lat=24.4672, long=54.6031, locality=Abu Dhabi, country=UAE)), date=2022-11-20, time=13:00:00Z, results=[ResultDto(number=23, position=13, positionText=13, points=0, grid=19, laps=57, status=+1 Lap, fastestLap=FastestLapDto(rank=null, lap=40, time=TimeDto(time=1:29.939)))])]"
            )
        }
    }

    @Test
    fun `given An Dto With A Null List Of Races, When Transforming Dto Into Model Skippable, Then Respond With Result Failure And NoDataFoundException Exception`() {
        // Given
        val skipElementIfFailedToParseDriver = false
        val emptyDto = DriverDetailsDto(null, null)

        // When
        val modelResult = mapper.transform(emptyDto, skipElementIfFailedToParseDriver)

        // Then
        assertThat(
            "Given a DTO with null list of races, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO with null list of races, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is NoDataFoundException
        )
        assertThat(
            "Given a DTO with null list of races, then the custom exception must inform that driver races list is empty",
            modelResult.throwable.message,
            `is`("The driver races list is empty")
        )
    }
}
