package com.fukuhara.douglas.f1driverslineup.di

import com.fukuhara.douglas.f1driverslineup.BuildConfig
import com.fukuhara.douglas.lib.common.network.NetworkConfiguration
import org.koin.dsl.module

val appConfig = module {
    factory {
        NetworkConfiguration(
            baseUrl = "https://raw.githubusercontent.com/dofukuhara/2022-F1-Drivers-LineUp/main/api/",
            isDebug = BuildConfig.DEBUG
        )
    }
}
