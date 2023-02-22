package com.fukuhara.douglas.lib.common.di

import com.fukuhara.douglas.lib.common.logger.AppLogger
import com.fukuhara.douglas.lib.common.logger.TimberAppLogger
import com.fukuhara.douglas.lib.common.services.ImageLoader
import com.fukuhara.douglas.lib.common.services.PicassoImageLoader
import org.koin.dsl.module

val commonModule = module {
    // App Logger service
    factory<AppLogger> { TimberAppLogger() }

    // Image Loader service
    factory<ImageLoader> { PicassoImageLoader() }
}
