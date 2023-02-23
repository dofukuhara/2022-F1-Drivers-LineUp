package com.fukuhara.douglas.f1driverslineup.feature.driverslist.ui

import com.fukuhara.douglas.f1driverslineup.feature.driverslist.model.DriverModel

interface DriverClickListener {
    fun onClickListener(driverModel: DriverModel)
}
