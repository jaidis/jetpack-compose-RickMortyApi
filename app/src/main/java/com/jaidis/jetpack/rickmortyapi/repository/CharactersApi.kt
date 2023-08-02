package com.jaidis.jetpack.rickmortyapi.repository

import com.jaidis.jetpack.rickmortyapi.data.CharactersResponse
import com.jaidis.jetpack.rickmortyapi.data.GsonCharacter
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("character/{id}")
    suspend fun getCharacterId(@Path("id") id: String): GsonCharacter

    @GET("character")
    suspend fun getCharacters(): CharactersResponse

    @GET("character")
    suspend fun getCharactersPage(@Query("page") page: String): CharactersResponse
}