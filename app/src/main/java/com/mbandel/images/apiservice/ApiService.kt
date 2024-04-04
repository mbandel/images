package com.mbandel.images.apiservice

import com.mbandel.images.remote.ImageRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(ApiConstants.KEY)
    suspend fun searchImages(@Query("q") query: String): Response<ImageRemote>
}