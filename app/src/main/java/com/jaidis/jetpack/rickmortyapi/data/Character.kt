package com.jaidis.jetpack.rickmortyapi.data

data class Character(
    val id: Number?,
    val name: String?,
    val status: String?,
    val species: String?,
    val type: String?,
    val gender: String?,
    val image: String?,
    val origin: OriginCharacter?,
    val location: LocationCharacter?,
    val episode: List<String>?,
    val url: String?,
    val created: String?,
)

data class OriginCharacter(
    val name: String?,
    val url: String?,
)

data class LocationCharacter(
    val name: String?,
    val url: String?,
)