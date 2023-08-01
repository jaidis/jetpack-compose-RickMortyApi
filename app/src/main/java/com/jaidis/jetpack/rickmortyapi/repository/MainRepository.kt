package com.jaidis.jetpack.rickmortyapi.repository

import com.jaidis.jetpack.rickmortyapi.data.CharacterResponse
import com.jaidis.jetpack.rickmortyapi.data.CharactersResponse
import com.jaidis.jetpack.rickmortyapi.data.EpisodeResponse
import com.jaidis.jetpack.rickmortyapi.data.EpisodesResponse
import com.jaidis.jetpack.rickmortyapi.data.LocationResponse
import com.jaidis.jetpack.rickmortyapi.data.LocationsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(
    private val charactersWebService: CharactersWebService,
    private val episodesWebService: EpisodesWebService,
    private val locationsWebService: LocationsWebService
) {
    companion object {
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    suspend fun getCharacter(characterId: String): CharacterResponse =
        withContext(Dispatchers.IO) {
            return@withContext charactersWebService.getCharacter(characterId)
        }

    suspend fun getCharacters(): CharactersResponse =
        withContext(Dispatchers.IO) {
            return@withContext charactersWebService.getCharacters()
        }

    suspend fun getCharactersPage(page: String): CharactersResponse =
        withContext(Dispatchers.IO) {
            return@withContext charactersWebService.getCharactersPage(page)
        }

    suspend fun getEpisode(locationId: String): EpisodeResponse =
        withContext(Dispatchers.IO) {
            return@withContext episodesWebService.getEpisode(locationId)
        }

    suspend fun getEpisodes(): EpisodesResponse =
        withContext(Dispatchers.IO) {
            return@withContext episodesWebService.getEpisodes()
        }

    suspend fun getEpisodesPage(page: String): EpisodesResponse =
        withContext(Dispatchers.IO) {
            return@withContext episodesWebService.getEpisodesPage(page)
        }

    suspend fun getLocation(locationId: String): LocationResponse =
        withContext(Dispatchers.IO) {
            return@withContext locationsWebService.getLocation(locationId)
        }

    suspend fun getLocations(): LocationsResponse =
        withContext(Dispatchers.IO) {
            return@withContext locationsWebService.getLocations()
        }

    suspend fun getLocationsPage(page: String): LocationsResponse =
        withContext(Dispatchers.IO) {
            return@withContext locationsWebService.getLocationsPage(page)
        }
}