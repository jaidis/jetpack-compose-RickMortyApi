package com.jaidis.jetpack.rickmortyapi.ui.views.episodes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jaidis.jetpack.rickmortyapi.R
import com.jaidis.jetpack.rickmortyapi.data.Episode
import com.jaidis.jetpack.rickmortyapi.ui.views.shared.InfoIconRow
import com.jaidis.jetpack.rickmortyapi.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EpisodeDetailView(viewModel: MainViewModel, episodeId: String, popBack: () -> Unit) {
    val (episode, setEpisode) = remember { mutableStateOf<Episode?>(null) }
    LaunchedEffect(episodeId) {
        setEpisode(viewModel.getEpisode(episodeId))
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.episode)) },
                navigationIcon = {
                    IconButton(onClick = { popBack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.generic_back)
                        )
                    }
                }
            )
        })
    {
        Surface(color = MaterialTheme.colors.background) {
            LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
                episode?.let {
                    item(episode.id) {
                        Text(
                            episode.name ?: "",
                            style = MaterialTheme.typography.h4,
                            color = LocalContentColor.current,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                        Text(
                            stringResource(R.string.ch_info),
                            style = MaterialTheme.typography.subtitle2,
                            color = LocalContentColor.current,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        InfoIconRow(
                            label = stringResource(R.string.ep_air_date),
                            item = episode.air_date ?: stringResource(R.string.generic_unknown),
                            icon = Icons.Outlined.CalendarMonth
                        )
                        InfoIconRow(
                            label = stringResource(R.string.ep_episode),
                            item = episode.episode ?: stringResource(R.string.generic_unknown),
                            icon = Icons.Outlined.Movie
                        )
                        InfoIconRow(
                            label = stringResource(R.string.ep_total_ch),
                            item = episode.characters?.size.toString(),
                            icon = Icons.Outlined.Numbers
                        )
                    }
                }
            }

        }
    }
}
