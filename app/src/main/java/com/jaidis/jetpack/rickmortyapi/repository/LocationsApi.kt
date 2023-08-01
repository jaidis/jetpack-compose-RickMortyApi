package com.jaidis.jetpack.rickmortyapi.repository

import com.jaidis.jetpack.rickmortyapi.data.GsonLocation
import com.jaidis.jetpack.rickmortyapi.data.LocationsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationsApi {

    @GET("location/{id}")
    suspend fun getLocationId(@Path("id") id: String): GsonLocation

    @GET("location")
    suspend fun getLocations(): LocationsResponse

    @GET("location?page={id}")
    suspend fun getLocationsPage(@Path("id") id: String): LocationsResponse
}