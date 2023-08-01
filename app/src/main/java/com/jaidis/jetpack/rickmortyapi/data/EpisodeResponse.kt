package com.jaidis.jetpack.rickmortyapi.data

data class EpisodeResponse(
    val episode: GsonEpisode
)

fun EpisodeResponse.asEpisode(): Episode {
    return episode.asEpisode()
}