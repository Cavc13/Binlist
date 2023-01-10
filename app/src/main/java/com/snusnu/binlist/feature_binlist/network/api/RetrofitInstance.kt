package com.snusnu.binlist.feature_binlist.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: BinlistApi by lazy {
        Retrofit.Builder()
            .baseUrl(BIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BinlistApi::class.java)
    }

    private const val BIN_URL = "https://lookup.binlist.net"
}