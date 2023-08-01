package com.jaidis.jetpack.rickmortyapi.ui.views.locations

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import com.jaidis.jetpack.rickmortyapi.data.Character
import com.jaidis.jetpack.rickmortyapi.data.Location
import com.jaidis.jetpack.rickmortyapi.viewmodel.MainViewModel

@Composable
fun LocationsListView(
    viewModel: MainViewModel,
    bottomBar: @Composable () -> Unit,
    locationSelected: (location: Location) -> Unit
) {
    val locationList = viewModel.locations
    Scaffold(
        topBar = { TopAppBar(title = { Text("Characters") }) },
        bottomBar = bottomBar
    )
    {
        if (locationList != null) {
            LazyColumn(contentPadding = it) {
                items(items = locationList) { it ->
                    LocationsListRowView(it, locationSelected)
                }
            }
        } else {
            viewModel.getLocations()
        }
    }
}
