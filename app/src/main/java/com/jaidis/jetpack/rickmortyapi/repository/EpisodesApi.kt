package com.jaidis.jetpack.rickmortyapi.repository

import com.jaidis.jetpack.rickmortyapi.data.EpisodeResponse
import com.jaidis.jetpack.rickmortyapi.data.EpisodesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesApi {

    @GET("episode/{id}")
    suspend fun getEpisodeId(@Path("id") id: String): EpisodeResponse

    @GET("episode")
    suspend fun getEpisodes(): EpisodesResponse

    @GET("episode?page={id}")
    suspend fun getEpisodesPage(@Path("id") id: String): EpisodesResponse
}