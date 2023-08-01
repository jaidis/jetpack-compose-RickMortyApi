package com.jaidis.jetpack.rickmortyapi.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreens(val route: String, val label: String, val icon: ImageVector? = null) {
    object CharactersScreen : AppScreens("Characters", "Characters", Icons.Default.Person)
    object EpisodesScreen : AppScreens("Episodes", "Episodes", Icons.Default.Tv)
    object LocationsScreen : AppScreens("Locations", "Locations", Icons.Default.LocationOn)
    object CharacterDetailsScreen : AppScreens("CharacterDetails", "CharacterDetails")
    object EpisodeDetailsScreen : AppScreens("EpisodeDetails", "EpisodeDetails")
    object LocationDetailsScreen : AppScreens("LocationDetails", "LocationDetails")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

    fun withArgsFormat(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/{$arg}")
            }
        }
    }
}