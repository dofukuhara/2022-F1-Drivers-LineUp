package com.fukuhara.douglas.f1driverslineup.feature.driverslist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.model.DriverModel
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.repository.DriversListRepository
import com.fukuhara.douglas.lib.common.logger.AppLogger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DriversListViewModel(
    private val backgroundDispatcher: CoroutineDispatcher,
    private val appLogger: AppLogger,
    private val repository: DriversListRepository
) : ViewModel() {

    private val _driversListModel = MutableLiveData<List<DriverModel>>()
    val driversListModel: LiveData<List<DriverModel>> = _driversListModel

    fun init() {
        if (_driversListModel.value == null) {
            viewModelScope.launch {
                val repositoryResponse = withContext(backgroundDispatcher) {
                    repository.getDriversList()
                }

                repositoryResponse.fold(
                    failure = { throwable -> appLogger.v("DriversListViewModel", "Error: ${throwable.message}") },
                    success = { driversListModels ->
                        _driversListModel.value = driversListModels.drivers.sortedWith(
                            compareBy(
                                { it.team },
                                { it.name }
                            )
                        )
                    }
                )
            }
        }
    }

    fun onDriverItemClick(driverModel: DriverModel) {
    }
}
