package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.di

import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.model.DriverDetailsModelMapper
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.DriverDetailsRemoteRepository
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.DriverDetailsRepository
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.DriverDetailsServiceApi
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.viewmodel.DriverDetailsViewModel
import com.fukuhara.douglas.lib.common.network.RestClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val driverDetailsModule = module {
    factory<DriverDetailsRepository> {
        DriverDetailsRemoteRepository(
            api = getDriverDetailsServiceApi(client = get()),
            mapper = DriverDetailsModelMapper(
                skipElementIfFailedToParseDriver = false,
                appLogger = get()
            )
        )
    }

    viewModel {
        DriverDetailsViewModel(
            backgroundDispatcher = Dispatchers.IO,
            appLogger = get(),
            repository = get()
        )
    }
}

private fun getDriverDetailsServiceApi(client: RestClient): DriverDetailsServiceApi = client.getApi(DriverDetailsServiceApi::class.java)
