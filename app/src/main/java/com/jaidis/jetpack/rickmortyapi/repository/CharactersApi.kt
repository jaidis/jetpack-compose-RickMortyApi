package com.jaidis.jetpack.rickmortyapi.repository

import com.jaidis.jetpack.rickmortyapi.data.CharacterResponse
import com.jaidis.jetpack.rickmortyapi.data.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {

    @GET("character/{id}")
    suspend fun getCharacterId(@Path("id") id: String): CharacterResponse

    @GET("character")
    suspend fun getCharacters(): CharactersResponse

    @GET("character?page={id}")
    suspend fun getCharactersPage(@Path("id") id: String): CharactersResponse
}