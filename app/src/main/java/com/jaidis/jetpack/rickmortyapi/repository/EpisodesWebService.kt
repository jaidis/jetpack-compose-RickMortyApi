package com.jaidis.jetpack.rickmortyapi.repository

import com.jaidis.jetpack.rickmortyapi.data.EpisodeResponse
import com.jaidis.jetpack.rickmortyapi.data.EpisodesResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EpisodesWebService {
    private val api: EpisodesApi by lazy {
        createEpisodesApi()
    }

    suspend fun getEpisode(id: String): EpisodeResponse {
        return api.getEpisodeId(id)
    }

    suspend fun getEpisodes(): EpisodesResponse {
        return api.getEpisodes()
    }

    suspend fun getEpisodesPage(page: String): EpisodesResponse {
        return api.getEpisodesPage(page)
    }

    private fun createEpisodesApi(): EpisodesApi {
        val gsonConverterFactory = GsonConverterFactory.create()

        val retrofit = Retrofit.Builder()
            .baseUrl(MainRepository.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(EpisodesApi::class.java)
    }
}