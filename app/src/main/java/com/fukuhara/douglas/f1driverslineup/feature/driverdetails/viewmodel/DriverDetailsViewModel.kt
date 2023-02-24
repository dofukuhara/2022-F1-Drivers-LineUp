package com.fukuhara.douglas.f1driverslineup.feature.driverdetails.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.model.DriverDetailsModel
import com.fukuhara.douglas.f1driverslineup.feature.driverdetails.repository.DriverDetailsRepository
import com.fukuhara.douglas.f1driverslineup.feature.driverslist.model.DriverModel
import com.fukuhara.douglas.lib.common.logger.AppLogger
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DriverDetailsViewModel(
    private val backgroundDispatcher: CoroutineDispatcher,
    private val appLogger: AppLogger,
    private val repository: DriverDetailsRepository
) : ViewModel() {

    private val _driverModel = MutableLiveData<DriverModel>()

    private val _toolbarTitle = MutableLiveData<String>()
    val toolbarTitle: LiveData<String> = _toolbarTitle

    private val _driverRaceDetailsModel = MutableLiveData<List<DriverDetailsModel>>()
    val driverRaceDetailsModel: LiveData<List<DriverDetailsModel>> = _driverRaceDetailsModel

    fun initViewModel(driverModel: DriverModel) {
        _driverModel.value = driverModel
        _toolbarTitle.value = driverModel.name

        viewModelScope.launch {
            _driverModel.value?.driverId?.let { driverId ->
                val modelResponse = withContext(backgroundDispatcher) {
                    repository.getDriverDetails(driverId)
                }

                modelResponse.fold(
                    failure = { throwable -> appLogger.e("DriverDetailsViewModel", "Error: ${throwable.message}") },
                    success = { driverDetailsModel ->
                        _driverRaceDetailsModel.value = driverDetailsModel.filter { it.season == "2022" }.sortedBy { it.round }
                    }
                )
            } ?: run {
                // TODO : Handle error case
            }
        }
    }
}
