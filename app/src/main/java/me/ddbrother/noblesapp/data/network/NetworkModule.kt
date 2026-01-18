package me.ddbrother.noblesapp.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private const val BASE_URL = "https://nobilis.nobles.edu/"

    val apiService: NoblesApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NoblesApiService::class.java)
    }
}
