package com.example.kamussaya.feature_kamus.data.remote

import com.example.kamussaya.feature_kamus.data.remote.dto.KbbiDto
import retrofit2.http.GET
import retrofit2.http.Path

interface KamusApi {

    @GET("/cari/{kata}")
    suspend fun getWordList(@Path("kata") kata: String): KbbiDto

    companion object {
        const val BASE_URL = "https://new-kbbi-api.herokuapp.com"
    }
}
