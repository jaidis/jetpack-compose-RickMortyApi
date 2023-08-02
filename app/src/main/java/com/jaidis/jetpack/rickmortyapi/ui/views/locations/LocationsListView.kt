package com.jaidis.jetpack.rickmortyapi.ui.views.locations

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.jaidis.jetpack.rickmortyapi.R
import com.jaidis.jetpack.rickmortyapi.data.Location
import com.jaidis.jetpack.rickmortyapi.ui.views.characters.isScrolledToEnd
import com.jaidis.jetpack.rickmortyapi.viewmodel.MainViewModel

fun LazyListState.isScrolledToEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@Composable
fun LocationsListView(
    viewModel: MainViewModel,
    bottomBar: @Composable () -> Unit,
    locationSelected: (location: Location) -> Unit
) {
    val locationList = viewModel.locations
    val scrollState = rememberLazyListState()
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.locations)) }) },
        bottomBar = bottomBar
    )
    {
        if (locationList != null) {
            LazyColumn(contentPadding = it, state = scrollState) {
                items(items = locationList) { it ->
                    LocationsListRowView(it, locationSelected)
                }
            }
        } else {
            viewModel.getLocations()
        }
    }

    // observer when reached end of list
    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledToEnd()
        }
    }

    // act when end of list reached
    LaunchedEffect(endOfListReached) {
        viewModel.getLocationsNext()
    }
}
