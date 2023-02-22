package com.fukuhara.douglas.lib.common.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RestClient {
    fun <T> getApi(service: Class<T>): T
}

internal class RetrofitClient(private val networkConfiguration: NetworkConfiguration) : RestClient {
    private val retrofitClient: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(networkConfiguration.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .loggingInterceptor()
            .build()
    }

    private fun Retrofit.Builder.loggingInterceptor(): Retrofit.Builder {
        return if (networkConfiguration.isDebug.not()) {
            this
        } else {
            val clientConfig = OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            ).build()

            this.client(clientConfig)
        }
    }

    override fun <T> getApi(service: Class<T>): T = retrofitClient.create(service)
}
