package com.jaidis.jetpack.rickmortyapi.repository

import com.jaidis.jetpack.rickmortyapi.data.EpisodesResponse
import com.jaidis.jetpack.rickmortyapi.data.GsonEpisode
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesApi {

    @GET("episode/{id}")
    suspend fun getEpisodeId(@Path("id") id: String): GsonEpisode

    @GET("episode")
    suspend fun getEpisodes(): EpisodesResponse

    @GET("episode?page={id}")
    suspend fun getEpisodesPage(@Path("id") id: String): EpisodesResponse
}