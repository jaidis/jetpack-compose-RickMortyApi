package com.jaidis.jetpack.rickmortyapi.data

data class LocationsResponse(
    val info: GsonInfo,
    val results: List<GsonLocation>
)

fun LocationsResponse.asLocations(): List<Location> {
    return results.map {
        it.asLocation()
    }
}