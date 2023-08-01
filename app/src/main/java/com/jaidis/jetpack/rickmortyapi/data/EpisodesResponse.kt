package com.jaidis.jetpack.rickmortyapi.data

data class EpisodesResponse(
    val info: GsonInfo,
    val results: List<GsonEpisode>
)

fun EpisodesResponse.asEpisodes(): List<Episode> {
    return results.map {
        it.asEpisode()
    }
}