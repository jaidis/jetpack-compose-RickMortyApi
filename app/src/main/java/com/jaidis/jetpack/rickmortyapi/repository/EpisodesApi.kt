package com.jaidis.jetpack.rickmortyapi.repository

import com.jaidis.jetpack.rickmortyapi.data.EpisodesResponse
import com.jaidis.jetpack.rickmortyapi.data.GsonEpisode
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesApi {

    @GET("episode/{id}")
    suspend fun getEpisodeId(@Path("id") id: String): GsonEpisode

    @GET("episode")
    suspend fun getEpisodes(): EpisodesResponse

    @GET("episode")
    suspend fun getEpisodesPage(@Query("page") page: String): EpisodesResponse
}