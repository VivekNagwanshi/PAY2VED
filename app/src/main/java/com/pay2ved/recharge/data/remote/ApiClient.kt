package com.jmcpapertech.jmcapp.data.remote

import com.dc.retroapi.builder.RetrofitClientBuilder
import com.dc.retroapi.interceptor.ResponseInterceptor
import com.intuit.sdp.BuildConfig
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val BASE_URL = "http://13.126.65.42/WS/"

        val apiService: ApiInterface by lazy {
            return@lazy RetrofitClientBuilder()
                .baseUrl(BASE_URL)
                .readTimeout(1000 * 120)
                .writeTimeout(1000 * 120)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addResponseInterceptor(object : ResponseInterceptor
                .OnResponseCallback {
                    override fun onNetworkResponse(response: Response) {
                    }
                })
                .addLogInterceptor(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
                .create(ApiInterface::class.java)
        }
    }
}