package com.snusnu.binlist.feature_binlist.data.network.api

import com.snusnu.binlist.feature_binlist.data.network.dto.ResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BinApi {

    @GET("{bin}")
    suspend fun getBin(
        @Path("bin") bin: String
    ): Response<ResponseDto>
}