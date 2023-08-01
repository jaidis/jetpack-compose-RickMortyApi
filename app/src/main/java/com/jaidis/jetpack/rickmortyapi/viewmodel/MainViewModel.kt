package com.jaidis.jetpack.rickmortyapi.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaidis.jetpack.rickmortyapi.data.Character
import com.jaidis.jetpack.rickmortyapi.data.Episode
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
    var episodes: List<Episode>? by mutableStateOf(null)
    var locations: List<Location>? by mutableStateOf(null)

    private val repository = MainRepository(
        CharactersWebService(), EpisodesWebService(), LocationsWebService()
    )

    suspend fun getCharacter(characterId: String): Character {
        return repository.getCharacter(characterId).asCharacter()
    }

    fun getCharacters() = viewModelScope.launch {
        val response = repository.getCharacters()
        characters = response.asCharacters()
    }

    fun getCharactersPages(page: String) = viewModelScope.launch {
        val tempList = characters?.toMutableList()
        tempList?.addAll(repository.getCharactersPage(page).asCharacters())
        characters = tempList
    }

    suspend fun getEpisode(locationId: String): Episode {
        return repository.getEpisode(locationId).asEpisode()
    }

    fun getEpisodes() = viewModelScope.launch {
        val response = repository.getEpisodes()
        episodes = response.asEpisodes()
    }

    fun getEpisodesPages(page: String) = viewModelScope.launch {
        val tempList = episodes?.toMutableList()
        tempList?.addAll(repository.getEpisodesPage(page).asEpisodes())
        episodes = tempList
    }

    suspend fun getLocation(locationId: String): Location {
        return repository.getLocation(locationId).asLocation()
    }

    fun getLocations() = viewModelScope.launch {
        val response = repository.getLocations()
        locations = response.asLocations()
    }

    fun getLocationsPages(page: String) = viewModelScope.launch {
        val tempList = locations?.toMutableList()
        tempList?.addAll(repository.getLocationsPage(page).asLocations())
        locations = tempList
    }


}