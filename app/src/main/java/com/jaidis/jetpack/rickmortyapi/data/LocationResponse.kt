package com.jaidis.jetpack.rickmortyapi.data

data class LocationResponse(
    val location: GsonLocation
)

fun LocationResponse.asLocation(): Location {
    return location.asLocation()
}