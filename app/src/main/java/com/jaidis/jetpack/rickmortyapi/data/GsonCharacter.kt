package com.jaidis.jetpack.rickmortyapi.data

data class GsonCharacter(
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

fun GsonCharacter.asCharacter(): Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        origin = origin,
        location = location,
        episode = episode,
        url = url,
        created = created
    )
}