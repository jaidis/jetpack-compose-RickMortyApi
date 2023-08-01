package com.jaidis.jetpack.rickmortyapi.repository

import com.jaidis.jetpack.rickmortyapi.data.LocationResponse
import com.jaidis.jetpack.rickmortyapi.data.LocationsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LocationsWebService {
    private val api: LocationsApi by lazy {
        createLocationsApi()
    }

    suspend fun getLocation(id: String): LocationResponse {
        return api.getLocationId(id)
    }

    suspend fun getLocations(): LocationsResponse {
        return api.getLocations()
    }

    suspend fun getLocationsPage(page: String): LocationsResponse {
        return api.getLocationsPage(page)
    }

    private fun createLocationsApi(): LocationsApi {
        val gsonConverterFactory = GsonConverterFactory.create()

        val retrofit = Retrofit.Builder()
            .baseUrl(MainRepository.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()

        return retrofit.create(LocationsApi::class.java)
    }
}