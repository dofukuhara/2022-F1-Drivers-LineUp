package com.fukuhara.douglas.lib.common.logger

import timber.log.Timber

interface AppLogger {
    fun d(tag: String? = null, message: String)
    fun e(tag: String? = null, message: String)
    fun e(tag: String? = null, message: String?, throwable: Throwable)
    fun i(tag: String? = null, message: String)
    fun v(tag: String? = null, message: String)
}

internal class TimberAppLogger : AppLogger {
    override fun d(tag: String?, message: String) {
        with(Timber) {
            tag?.let { tag(it) }
            d(message)
        }
    }

    override fun e(tag: String?, message: String) {
        with(Timber) {
            tag?.let { tag(it) }
            e(message)
        }
    }

    override fun e(tag: String?, message: String?, throwable: Throwable) {
        with(Timber) {
            tag?.let { tag(it) }
            e(message, throwable)
        }
    }

    override fun i(tag: String?, message: String) {
        with(Timber) {
            tag?.let { tag(it) }
            i(message)
        }
    }

    override fun v(tag: String?, message: String) {
        with(Timber) {
            tag?.let { tag(it) }
            v(message)
        }
    }
}
