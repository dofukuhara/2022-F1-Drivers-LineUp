package com.fukuhara.douglas.f1driverslineup.feature.driverslist.model

import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.dto.DriversListDto
import com.fukuhara.douglas.lib.common.arch.Result
import com.fukuhara.douglas.lib.common.exception.ModelParserException
import com.fukuhara.douglas.lib.common.logger.AppLogger
import com.fukuhara.douglas.lib.common.model.ModelMapper
import io.mockk.mockk
import io.mockk.verify
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

class DriversListModelMapperTest {

    private lateinit var modelMapper: ModelMapper<DriversListDto, Result<Throwable, DriversListModel>>
    private val appLoggerMock: AppLogger = mockk(relaxed = true)

    @Before
    fun setUp() {
        modelMapper = DriversListModelMapper(appLogger = appLoggerMock)
    }

    @Test
    fun `given A Complete Single Dto Element, When Transforming Dto Into Model Non-Skippable, Then A Single Model Element Must Be Returned`() {
        // Given
        val completeSingleDto = DriversListDtoHelper.generateCompleteSingleElement()

        // When
        val modelResult = modelMapper.transform(completeSingleDto)

        // Then
        assertThat(
            "Given a complete DTO element, then ModelMapper must map it into Model properly",
            modelResult is Result.Success
        )
        assertThat(
            "SEASON field must be properly mapped",
            (modelResult as Result.Success).model.season,
            `is`("2022")
        )
        assertThat(
            "DRIVERS_LIST must be size 1",
            (modelResult as Result.Success).model.drivers.size,
            `is`(1)
        )
        assertThat(
            "DRIVER[0].driverId must be albon",
            (modelResult as Result.Success).model.drivers[0].driverId,
            `is`("albon")
        )
        assertThat(
            "DRIVER[0].permanentNumber must be 23",
            (modelResult as Result.Success).model.drivers[0].permanentNumber,
            `is`(23)
        )
        assertThat(
            "DRIVER[0].imageUrl must be https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png",
            (modelResult as Result.Success).model.drivers[0].imageUrl,
            `is`("https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png")
        )
        assertThat(
            "DRIVER[0].name must be Alexander Albon",
            (modelResult as Result.Success).model.drivers[0].name,
            `is`("Alexander Albon")
        )
        assertThat(
            "DRIVER[0].dateOfBirth must be 1996-03-23",
            (modelResult as Result.Success).model.drivers[0].dateOfBirth,
            `is`("1996-03-23")
        )
        assertThat(
            "DRIVER[0].nationality must be Thai",
            (modelResult as Result.Success).model.drivers[0].nationality,
            `is`("TH")
        )
    }

    @Test
    fun `given A Complete Three Dto Elements, When Transforming Dto Into Model Non-Skippable, Then A Model With Three Elements Must Be Returned`() {
        // Given
        val completeThreeElements = DriversListDtoHelper.generateCompleteThreeElements()

        // When
        val modelResult = modelMapper.transform(completeThreeElements)

        // Then
        assertThat(
            "Given a complete DTO with three element, then ModelMapper must map it into Model properly",
            modelResult is Result.Success
        )
        assertThat(
            "DRIVERS_LIST must be size 3",
            (modelResult as Result.Success).model.drivers.size,
            `is`(3)
        )
    }

    @Test
    fun `given A Complete Two Dto Elements And One Incomplete, When Transforming Dto Into Model Non-Skippable, Then A Model With Three Elements Must Be Returned`() {
        // Given
        val twoCompleteOneMissingFamilyNameElements = DriversListDtoHelper.generateTwoCompleteOneMissingFamilyNameElements()

        // When
        val modelResult = modelMapper.transform(twoCompleteOneMissingFamilyNameElements)

        // Then
        assertThat(
            "Given that the DTO has one mandatory field missing, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO is missing FamilyName field, then the custom exception must inform which field was missing",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing Drivers.familyName field]")
        )
    }

    @Test
    fun `given A Single Dto Missing Season, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val missingSeasonSingleDto = DriversListDtoHelper.generateMissingSeasonSingleElement()

        // When
        val modelResult = modelMapper.transform(missingSeasonSingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO is missing SEASON field, then the custom exception must inform which field was missing",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing season field]")
        )
    }

    @Test
    fun `given A Single Dto Missing DriverId, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val missingDriverIdSingleDto = DriversListDtoHelper.generateMissingDriverIdSingleElement()

        // When
        val modelResult = modelMapper.transform(missingDriverIdSingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO is missing DriverId field, then the custom exception must inform which field was missing",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing Drivers.driverId field]")
        )
    }

    @Test
    fun `given A Single Dto Missing PermanentNumber, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val missingPermanentNumberSingleDto = DriversListDtoHelper.generateMissingPermanentNumberSingleElement()

        // When
        val modelResult = modelMapper.transform(missingPermanentNumberSingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO is missing PermanentNumber field, then the custom exception must inform which field was missing",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing or invalid Drivers.permanentNumber field]")
        )
    }

    @Test
    fun `given A Single Dto With An Invalid PermanentNumber, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val invalidPermanentNumberSingleDto = DriversListDtoHelper.generateInvalidPermanentNumberSingleElement()

        // When
        val modelResult = modelMapper.transform(invalidPermanentNumberSingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO has an invalid PermanentNumber, then the custom exception must inform which field was invalid",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing or invalid Drivers.permanentNumber field]")
        )
    }

    @Test
    fun `given A Single Dto Missing ImageUrl, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val missingDImageUrlSingleDto = DriversListDtoHelper.generateMissingImageUrlSingleElement()

        // When
        val modelResult = modelMapper.transform(missingDImageUrlSingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO has an invalid ImageUrl, then the custom exception must inform which field was invalid",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing Drivers.imageUrl field]")
        )
    }

    @Test
    fun `given A Single Dto Missing GivenName, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val missingGivenNameSingleDto = DriversListDtoHelper.generateMissingGivenNameSingleElement()

        // When
        val modelResult = modelMapper.transform(missingGivenNameSingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO has an invalid GivenName, then the custom exception must inform which field was invalid",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing Drivers.givenName field]")
        )
    }

    @Test
    fun `given A Single Dto Missing FamilyName, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val missingFamilyNameSingleDto = DriversListDtoHelper.generateMissingFamilyNameSingleElement()

        // When
        val modelResult = modelMapper.transform(missingFamilyNameSingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO has an invalid FamilyName, then the custom exception must inform which field was invalid",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing Drivers.familyName field]")
        )
    }

    @Test
    fun `given A Single Dto Missing DateOfBirth, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val missingDateOfBirthSingleDto = DriversListDtoHelper.generateMissingDateOfBirthSingleElement()

        // When
        val modelResult = modelMapper.transform(missingDateOfBirthSingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO has an invalid DateOfBirth, then the custom exception must inform which field was invalid",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing Drivers.dateOfBirth field]")
        )
    }

    @Test
    fun `given A Single Dto Missing Team, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val missingTeamSingleDto = DriversListDtoHelper.generateMissingTeamSingleElement()

        // When
        val modelResult = modelMapper.transform(missingTeamSingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO has an invalid Team, then the custom exception must inform which field was invalid",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing Drivers.team field]")
        )
    }

    @Test
    fun `given A Single Dto Missing TeamColor, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val missingTeamColorSingleDto = DriversListDtoHelper.generateMissingTeamColorSingleElement()

        // When
        val modelResult = modelMapper.transform(missingTeamColorSingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO has an invalid TeamColor, then the custom exception must inform which field was invalid",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing Drivers.teamColor field]")
        )
    }

    @Test
    fun `given A Single Dto Missing Nationality, When Transforming Dto Into Model Non-Skippable, Then A Result With ModelParserException Must Be Returned`() {
        // Given
        val missingNationalitySingleDto = DriversListDtoHelper.generateMissingNationalitySingleElement()

        // When
        val modelResult = modelMapper.transform(missingNationalitySingleDto)

        // Then
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must return a Failure Result",
            modelResult is Result.Failure
        )
        assertThat(
            "Given a DTO missing a mandatory MODEL field, then ModelMapper must emit a proper exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given that this DTO has an invalid Nationality, then the custom exception must inform which field was invalid",
            (modelResult as Result.Failure).throwable.message,
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing Drivers.nationality field]")
        )
    }

    @Test
    fun `given A Skippable Model Mapper And A Single Incomplete Dto, When Transforming Dto Into Model, Then A Model With Empty List Will Be Returned`() {
        // Given
        modelMapper = DriversListModelMapper(skipElementIfFailedToParseDriver = true, appLoggerMock)
        val incompleteSingleDto = DriversListDtoHelper.generateMissingNationalitySingleElement()

        // When
        val modelResult = modelMapper.transform(incompleteSingleDto)

        // Then
        assertThat(
            "Given a complete DTO element, then ModelMapper must map it into Model properly",
            modelResult is Result.Success
        )
        assertThat(
            "SEASON field must be properly mapped",
            (modelResult as Result.Success).model.season,
            `is`("2022")
        )
        assertThat(
            "DRIVERS_LIST must be size 1",
            (modelResult as Result.Success).model.drivers.size,
            `is`(0)
        )
    }

    @Test
    fun `given A Skippable Model Mapper And A Single Incomplete Dto, When Transforming Dto Into Model, Then A Mapper Must Log This Failure With AppLogger`() {
        // Given
        modelMapper = DriversListModelMapper(skipElementIfFailedToParseDriver = true, appLoggerMock)
        val incompleteSingleDto = DriversListDtoHelper.generateMissingNationalitySingleElement()

        // When
        modelMapper.transform(incompleteSingleDto)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriversListModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverModel - [DriverDto(driverId=albon, permanentNumber=23, code=ALB, url=http://en.wikipedia.org/wiki/Alexander_Albon, imageUrl=https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/williams/albon.png, givenName=Alexander, familyName=Albon, dateOfBirth=1996-03-23, team=Williams, teamColor=#37BEDD, nationality=null)]"
            )
        }
    }

    @Test
    fun `given A Skippable Model Mapper And A Two Complete With One Incomplete Dto, When Transforming Dto Into Model, Then A Model With Two List Elements Will Be Returned`() {
        // Given
        modelMapper = DriversListModelMapper(skipElementIfFailedToParseDriver = true, appLoggerMock)
        val twoCompleteOneMissingFamilyNameElements = DriversListDtoHelper.generateTwoCompleteOneMissingFamilyNameElements()

        // When
        val modelResult = modelMapper.transform(twoCompleteOneMissingFamilyNameElements)

        // Then
        assertThat(
            "Given A Two Complete and One Incomplete DTO elements, Then ModelMapper can return a Result Success response",
            modelResult is Result.Success
        )
        assertThat(
            "SEASON field must be properly mapped",
            (modelResult as Result.Success).model.season,
            `is`("2022")
        )
        assertThat(
            "ModelMapper could parse 2 out of the 3 DTO entries",
            (modelResult as Result.Success).model.drivers.size,
            `is`(2)
        )
    }

    @Test
    fun `given A Skippable Model Mapper And A Two Complete With One Incomplete Dto, When Transforming Dto Into Model, Then A Mapper Must Log The Incomplete Elements With AppLogger`() {
        // Given
        modelMapper = DriversListModelMapper(skipElementIfFailedToParseDriver = true, appLoggerMock)
        val twoCompleteOneMissingFamilyNameElements = DriversListDtoHelper.generateTwoCompleteOneMissingFamilyNameElements()

        // When
        modelMapper.transform(twoCompleteOneMissingFamilyNameElements)

        // Then
        verify {
            appLoggerMock.e(
                tag = "DriversListModelMapper",
                message = "Skipping this element from the list, as it is missing a mandatory field for DriverModel - [DriverDto(driverId=alonso, permanentNumber=14, code=ALO, url=http://en.wikipedia.org/wiki/Fernando_Alonso, imageUrl=https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/img/alpine/alonso.jpeg, givenName=Fernando, familyName=null, dateOfBirth=1981-07-29, team=Alpine, teamColor=#2293D1, nationality=Spanish)]"
            )
        }
    }
}
