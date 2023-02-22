package com.fukuhara.douglas.f1driverslineup

import android.app.Application
import com.fukuhara.douglas.f1driverslineup.di.appConfig
import com.fukuhara.douglas.lib.common.di.commonModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val isDebugBuild = BuildConfig.DEBUG

        if (isDebugBuild) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger(if (isDebugBuild) { Level.DEBUG } else { Level.NONE })
            androidContext(this@MainApplication)
            modules(listOf(appConfig, commonModule))
        }
    }
}
