package com.example.readfromjson.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

//Moshi object that retrofit uses to read json
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

// Used retrofit builder to build retrofit object using Moshi converter with coroutine support
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    /**
     * Returns a coroutine Deferred List of Items
     */
    @GET("hiring.json")
    fun getItems() : Deferred<List<Item>>
}

/**
 * Public object that exposes lazy-initialized Retrofit object
 * */

object Api {
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

