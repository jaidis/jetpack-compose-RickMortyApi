package com.jaidis.jetpack.rickmortyapi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaidis.jetpack.rickmortyapi.data.Character
import com.jaidis.jetpack.rickmortyapi.data.Episode
import com.jaidis.jetpack.rickmortyapi.data.GsonInfo
import com.jaidis.jetpack.rickmortyapi.data.Location
import com.jaidis.jetpack.rickmortyapi.data.asCharacter
import com.jaidis.jetpack.rickmortyapi.data.asCharacters
import com.jaidis.jetpack.rickmortyapi.data.asEpisode
import com.jaidis.jetpack.rickmortyapi.data.asEpisodes
import com.jaidis.jetpack.rickmortyapi.data.asLocation
import com.jaidis.jetpack.rickmortyapi.data.asLocations
import com.jaidis.jetpack.rickmortyapi.repository.CharactersWebService
import com.jaidis.jetpack.rickmortyapi.repository.EpisodesWebService
import com.jaidis.jetpack.rickmortyapi.repository.LocationsWebService
import com.jaidis.jetpack.rickmortyapi.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    var characters: List<Character>? by mutableStateOf(null)
    var charactersInfo: GsonInfo? = null
    var episodes: List<Episode>? by mutableStateOf(null)
    var episodesInfo: GsonInfo? = null
    var locations: List<Location>? by mutableStateOf(null)
    var locationsInfo: GsonInfo? = null

    private val repository = MainRepository(
        CharactersWebService(), EpisodesWebService(), LocationsWebService()
    )

    suspend fun getCharacter(characterId: String): Character {
        return repository.getCharacter(characterId).asCharacter()
    }

    fun getCharacters() = viewModelScope.launch {
        val response = repository.getCharacters()
        characters = response.asCharacters()
        charactersInfo = response.info
    }

    fun getCharactersNext() = viewModelScope.launch {
        val tempList = characters?.toMutableList()
        if (charactersInfo?.next != null) {
            val response =
                repository.getCharactersPage(charactersInfo?.next!!.filter { it.isDigit() })
            tempList?.addAll(response.asCharacters())
            characters = tempList
            charactersInfo = response.info
        }
    }

    suspend fun getEpisode(locationId: String): Episode {
        return repository.getEpisode(locationId).asEpisode()
    }

    fun getEpisodes() = viewModelScope.launch {
        val response = repository.getEpisodes()
        episodes = response.asEpisodes()
    }

    fun getEpisodesNext() = viewModelScope.launch {
        val tempList = episodes?.toMutableList()
        if (episodesInfo?.next != null) {
            val response =
                repository.getEpisodesPage(episodesInfo?.next!!.filter { it.isDigit() })
            tempList?.addAll(response.asEpisodes())
            episodes = tempList
            episodesInfo = response.info
        }
    }

    suspend fun getLocation(locationId: String): Location {
        return repository.getLocation(locationId).asLocation()
    }

    fun getLocations() = viewModelScope.launch {
        val response = repository.getLocations()
        locations = response.asLocations()
        locationsInfo = response.info
    }

    fun getLocationsNext() = viewModelScope.launch {
        val tempList = locations?.toMutableList()
        if (locationsInfo?.next != null) {
            val response =
                repository.getLocationsPage(locationsInfo?.next!!.filter { it.isDigit() })
            tempList?.addAll(response.asLocations())
            locations = tempList
            locationsInfo = response.info
        }
    }
}