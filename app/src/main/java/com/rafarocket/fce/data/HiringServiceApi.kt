package com.rafarocket.fce.data

import retrofit2.http.GET

/**
 * Retrofit GET call
 */
interface HiringServiceApi {
    @GET("hiring.json")
    suspend fun getHiring(): List<HiringDto>
}
