package com.prateek.android.retrofit_recyclerview

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroHTTP {
    private var retro: Retrofit
    init {
        retro = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> getService(clazz: Class<T>): T {
        return retro.create(clazz)
    }

    companion object {
       private const val BASE_URL = "https://jsonplaceholder.typicode.com"
        val INSTANCE: RetroHTTP by lazy {
            RetroHTTP()
        }
    }
}