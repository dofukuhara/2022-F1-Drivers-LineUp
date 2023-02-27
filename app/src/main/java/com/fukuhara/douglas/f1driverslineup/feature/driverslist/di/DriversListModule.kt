package com.fukuhara.douglas.f1driverslineup.feature.driverslist.di

import com.fukuhara.douglas.f1driverslineup.feature.driverslist.model.DriversListModelMapper
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.DriversListRemoteRepository
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.DriversListRepository
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.DriversListServiceApi
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.viewmodel.DriversListViewModel
import com.fukuhara.douglas.lib.common.network.RestClient
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val driversListModule = module {
    viewModel {
        DriversListViewModel(
            backgroundDispatcher = Dispatchers.IO,
            appLogger = get(),
            repository = get()
        )
    }

    factory<DriversListRepository> {
        DriversListRemoteRepository(
            api = getDriverListServiceApi(client = get()),
            modelMapper = DriversListModelMapper(appLogger = get())
        )
    }
}

private fun getDriverListServiceApi(client: RestClient): DriversListServiceApi = client.getApi(DriversListServiceApi::class.java)
