package com.example.worldflags.network


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://countriesnow.space/api/v0.1/countries/flag/" //countriesnow.space/api/v0.1/countries/flag/images


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


interface flagApiService {
    @GET("photos")
    suspend fun getPhotos() : List<FlagPhoto>
}

object flagApi {
    val retrofitService: flagApiService by lazy { retrofit.create(flagApiService::class.java) }
}