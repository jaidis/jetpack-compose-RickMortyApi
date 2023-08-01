package com.jaidis.jetpack.rickmortyapi.data

data class Episode(
    val id: Number?,
    val name: String?,
    val air_date: String?,
    val episode: String?,
    val characters: List<String>?,
    val url: String?,
    val created: String?,
)