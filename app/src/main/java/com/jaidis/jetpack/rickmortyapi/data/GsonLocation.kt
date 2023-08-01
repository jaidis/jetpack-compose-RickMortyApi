package com.jaidis.jetpack.rickmortyapi.data

data class GsonLocation(
    val id: Number?,
    val name: String?,
    val type: String?,
    val dimension: String?,
    val residents: List<String>?,
    val url: String?,
    val created: String?,
)

fun GsonLocation.asLocation(): Location {
    return Location(
        id = id,
        name = name,
        type = type,
        dimension = dimension,
        residents = residents,
        url = url,
        created = created
    )
}