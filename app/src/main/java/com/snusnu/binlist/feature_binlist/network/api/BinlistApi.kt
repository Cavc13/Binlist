package com.snusnu.binlist.feature_binlist.network.api

import com.snusnu.binlist.feature_binlist.network.dto.ResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BinlistApi {

    @GET("/")
    suspend fun getBin(
        @Query("") binNumber: Int = 45717360
    ): Response<ResponseDto>
}