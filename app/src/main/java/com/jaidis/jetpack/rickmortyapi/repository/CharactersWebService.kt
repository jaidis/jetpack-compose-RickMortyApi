package com.jaidis.jetpack.rickmortyapi.repository

import com.jaidis.jetpack.rickmortyapi.data.CharacterResponse
import com.jaidis.jetpack.rickmortyapi.data.CharactersResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CharactersWebService {
    private val api: CharactersApi by lazy {
        createCharactersApi()
    }

    suspend fun getCharacter(id: String): CharacterResponse {
        return api.getCharacterId(id)
    }

    suspend fun getCharacters(): CharactersResponse {
        return api.getCharacters()
    }

    suspend fun getCharactersPage(page: String): CharactersResponse {
        return api.getCharactersPage(page)
    }

    private fun createCharactersApi(): CharactersApi {
        val gsonConverterFactory = GsonConverterFactory.create()

        val retrofit = Retrofit.Builder()
            .baseUrl(MainRepository.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(CharactersApi::class.java)
    }
}