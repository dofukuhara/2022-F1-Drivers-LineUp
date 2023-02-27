package com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository

import com.fukuhara.douglas.f1driverslineup.feature.driverslist.model.DriversListDtoHelper
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.model.DriversListModelMapper
import com.fukuhara.douglas.lib.common.arch.Result
import com.fukuhara.douglas.lib.common.exception.ModelParserException
import com.fukuhara.douglas.lib.common.exception.NoDataFoundException
import com.fukuhara.douglas.lib.common.logger.AppLogger
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DriversListRemoteRepositoryTest {

    private lateinit var repository: DriversListRepository

    private val apiMock: DriversListServiceApi = mockk(relaxed = true)
    private val appLoggerMock: AppLogger = mockk(relaxed = true)
    private val modelMapper: DriverListMapperType = DriversListModelMapper(appLoggerMock)

    @Before
    fun setUp() {
        repository = DriversListRemoteRepository(apiMock, modelMapper)
    }

    @Test
    fun `given An Exception From Api, When Fetching DriversList From Repository, Then Respond With Failure Result`() = runTest {
        // Given
        val skipElementIfFailedToParseDriver = false
        coEvery { apiMock.getDriversList() } throws NoDataFoundException("Failed to fetch Drivers List from mock API")

        // When
        val modelResult = repository.getDriversList(skipElementIfFailedToParseDriver)
        advanceUntilIdle()

        assertThat(
            "Given an exception from API, then wrap it and respond as Result.Failure",
            modelResult is Result.Failure
        )
        assertThat(
            "Given an exception from API, then Repository must generate a NoDataFoundException custom exception",
            (modelResult as Result.Failure).throwable is NoDataFoundException
        )
        assertThat(
            "Given an exception from API, then Repository must generate a custom exception with details on the problem",
            modelResult.throwable.message,
            `is`("Failed to retrieve data from network")
        )
    }

    @Test
    fun `given An Api Response With A Single But Incomplete Dto, When Fetching DriversList From Repository, Then Respond With Failure Result`() = runTest {
        // Given
        val skipElementIfFailedToParseDriver = false
        val dtoWithIncompleteDataResponse = DriversListDtoHelper.generateMissingGivenNameSingleElement()
        coEvery { apiMock.getDriversList() } returns dtoWithIncompleteDataResponse

        // When
        val modelResult = repository.getDriversList(skipElementIfFailedToParseDriver)
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
            `is`("Failed to Parse DriversListDto to DriversListModel [Missing Drivers.givenName field]")
        )
    }

    @Test
    fun `given A Skippable ModelMapper And An Incomplete Dto, When Fetching DriversList From Repository, Then Respond With Failure Result`() = runTest {
        // Given
        val skipElementIfFailedToParseDriver = true
        val dtoWithIncompleteDataResponse = DriversListDtoHelper.generateMissingGivenNameSingleElement()
        coEvery { apiMock.getDriversList() } returns dtoWithIncompleteDataResponse

        // When
        val modelResult = repository.getDriversList(skipElementIfFailedToParseDriver)
        advanceUntilIdle()

        assertThat(
            "Given an DriversListModel with empty DriverList from ModelMapper, then wrap it and respond as Result.Failure",
            modelResult is Result.Failure
        )
        assertThat(
            "Given an DriversListModel with empty DriverList from ModelMapper, then Repository must generate a NoDataFoundException custom exception",
            (modelResult as Result.Failure).throwable is NoDataFoundException
        )
        assertThat(
            "Given an DriversListModel with empty DriverList from ModelMapper, then Repository must generate a custom exception with details on the problem",
            modelResult.throwable.message,
            `is`("Drivers List returned was empty")
        )
    }

    @Test
    fun `given A Complete Three Elements In Api Response, When Fetching DriversList From Repository, Then Return A DriversListModel With Three Elements`() = runTest {
        // Given
        val skipElementIfFailedToParseDriver = false
        val completeThreeElementsInDto = DriversListDtoHelper.generateCompleteThreeElements()
        coEvery { apiMock.getDriversList() } returns completeThreeElementsInDto

        // When
        val modelResult = repository.getDriversList(skipElementIfFailedToParseDriver)
        advanceUntilIdle()

        assertThat(
            "Given a complete and mappable response from API, then wrap it and respond as Result.Success",
            modelResult is Result.Success
        )
        assertThat(
            "Given a complete and mappable 3 items element response from API, then Repository must return DriverList of size 3",
            (modelResult as Result.Success).model.drivers.size,
            `is`(3)
        )
    }
}
