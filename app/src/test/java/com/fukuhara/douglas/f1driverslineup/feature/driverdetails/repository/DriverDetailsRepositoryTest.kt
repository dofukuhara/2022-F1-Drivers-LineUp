package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository

import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.model.DriverDetailsDtoHelper
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.model.DriverDetailsModelMapper
import com.fukuhara.douglas.lib.common.arch.Result
import com.fukuhara.douglas.lib.common.exception.ModelParserException
import com.fukuhara.douglas.lib.common.exception.NoDataFoundException
import com.fukuhara.douglas.lib.common.logger.AppLogger
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DriverDetailsRepositoryTest {

    private lateinit var repository: DriverDetailsRepository

    private val apiMock: DriverDetailsServiceApi = mockk(relaxed = true)
    private val appLoggerMock: AppLogger = mockk(relaxed = true)
    private val modelMapper: DriverDetailsMapperType = DriverDetailsModelMapper(appLoggerMock)

    @Before
    fun setup() {
        repository = DriverDetailsRemoteRepository(apiMock, modelMapper)
    }

    @Test
    fun `given An Exception From Api, When Fetching DriverDetails From Repository, Then Respond With Failure Result`() = runTest {
        // Given
        val skipElementIfFailedToParseDriver = false
        coEvery { apiMock.getDriverDetails(any()) } throws NoDataFoundException("Failed to fetch driver details from mock API")

        // When
        val modelResult = repository.getDriverDetails("albon", skipElementIfFailedToParseDriver)
        advanceUntilIdle()

        assertThat(
            "Given an exception from API, then wrap it an respond as Result.Failure",
            modelResult is Result.Failure
        )
        assertThat(
            "Given an exception from API, then Repository must generate a NoDataFoundException custom exception",
            (modelResult as Result.Failure).throwable is NoDataFoundException
        )
        assertThat(
            "Given an exception from API, then Repository must generate a custom exception with details on the problem",
            modelResult.throwable.message,
            CoreMatchers.`is`("Failed to retrieve data from network")
        )
    }

    @Test
    fun `given An Api Response With A Single But Incomplete Dto And Non-Skippable Mapper, When Fetching DriverDetails From Repository, Then Respond With Failure Result`() = runTest {
        // Given
        val skipElementIfFailedToParseDriver = false
        val dtoWithIncompleteDataResponse = DriverDetailsDtoHelper.generateIncompleteSingleMissingFastestLap()
        coEvery { apiMock.getDriverDetails(any()) } returns dtoWithIncompleteDataResponse

        // When
        val modelResult = repository.getDriverDetails("albon", skipElementIfFailedToParseDriver)
        advanceUntilIdle()

        assertThat(
            "Given an exception from Mapper, then wrap it and respond as Result.Failure",
            modelResult is Result.Failure
        )
        assertThat(
            "Given an exception from Mapper, then Repository must generate a ModelParserException custom exception",
            (modelResult as Result.Failure).throwable is ModelParserException
        )
        assertThat(
            "Given an exception from Mapper, then Repository must generate a custom exception with details on the problem",
            modelResult.throwable.message,
            CoreMatchers.`is`("Failed to Parse DriverDetailsDto to List<DriverDetailsModel> [Missing Races.Results.FastestLap.Time.time]")
        )
    }

    @Test
    fun `given An Api Response With A Single But Incomplete Dto And Skippable Mapper, When Fetching DriverDetails From Repository, Then Respond With Failure Result`() = runTest {
        // Given
        val skipElementIfFailedToParseDriver = true
        val dtoWithIncompleteDataResponse = DriverDetailsDtoHelper.generateIncompleteSingleMissingFastestLap()
        coEvery { apiMock.getDriverDetails(any()) } returns dtoWithIncompleteDataResponse

        // When
        val modelResult = repository.getDriverDetails("albon", skipElementIfFailedToParseDriver)
        advanceUntilIdle()

        assertThat(
            "Given an exception from Mapper and an empty Driver Details List result, then wrap it and respond as Result.Failure",
            modelResult is Result.Failure
        )
        assertThat(
            "Given an exception from Mapper and an empty Driver Details List result, then Repository must generate a NoDataFoundException custom exception",
            (modelResult as Result.Failure).throwable is NoDataFoundException
        )
        assertThat(
            "Given an exception from Mapper and an empty Driver Details List result, then Repository must generate a custom exception with details on the problem",
            modelResult.throwable.message,
            CoreMatchers.`is`("Driver Races List returned was empty")
        )
    }

    @Test
    fun `given A Complete Three Elements In Api Response, When Fetching DriverDetails From Repository, Then Return a List of DriverDetailsModel With Three Elemenets`() = runTest {
        // Given
        val skipElementIfFailedToParseDriver = false
        val completeThreeElementsInDto = DriverDetailsDtoHelper.generateCompleteThreeRacesDto()
        coEvery { apiMock.getDriverDetails(any()) } returns completeThreeElementsInDto

        // When
        val modelResult = repository.getDriverDetails("albon", skipElementIfFailedToParseDriver)
        advanceUntilIdle()

        assertThat(
            "Given a complete and mappable response from API, then wrap it and respond as Result.Success",
            modelResult is Result.Success
        )
        assertThat(
            "Given a complete and mappable 3 items element response from API, then Repository must return DriverList of size 3",
            (modelResult as Result.Success).model.size,
            CoreMatchers.`is`(3)
        )
    }
}
