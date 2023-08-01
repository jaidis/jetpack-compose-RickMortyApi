package com.jaidis.jetpack.rickmortyapi.data

data class GsonEpisode(
    val id: Number?,
    val name: String?,
    val air_date: String?,
    val episode: String?,
    val characters: List<String>?,
    val url: String?,
    val created: String?,
)

fun GsonEpisode.asEpisode(): Episode {
    return Episode(
        id = id,
        name = name,
        air_date = air_date,
        episode = episode,
        characters = characters,
        url = url,
        created = created
    )
}