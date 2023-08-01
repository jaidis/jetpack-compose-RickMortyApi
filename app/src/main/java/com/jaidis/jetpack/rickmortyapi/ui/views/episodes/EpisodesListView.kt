package com.jaidis.jetpack.rickmortyapi.ui.views.episodes

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jaidis.jetpack.rickmortyapi.R
import com.jaidis.jetpack.rickmortyapi.data.Episode
import com.jaidis.jetpack.rickmortyapi.viewmodel.MainViewModel

@Composable
fun EpisodesListView(
    viewModel: MainViewModel,
    bottomBar: @Composable () -> Unit,
    episodeSelected: (episode: Episode) -> Unit
) {
    val episodeList = viewModel.episodes
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.episodes)) }) },
        bottomBar = bottomBar
    )
    {
        if (episodeList != null) {
            LazyColumn(contentPadding = it) {
                items(items = episodeList) { it ->
                    EpisodesListRowView(it, episodeSelected)
                }
            }
        } else {
            viewModel.getEpisodes()
        }
    }
}
