package com.example.flickagram.data.network.config

import retrofit2.Retrofit
import javax.inject.Inject

class APIService @Inject constructor(private val retrofit: Retrofit) {
    fun <T> createAPI(klass : Class<T>) : T {
        return retrofit.create(klass)
    }
}