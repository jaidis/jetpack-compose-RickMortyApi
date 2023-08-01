package com.jaidis.jetpack.rickmortyapi.data

data class Location(
    val id: Number?,
    val name: String?,
    val type: String?,
    val dimension: String?,
    val residents: List<String>?,
    val url: String?,
    val created: String?,
)