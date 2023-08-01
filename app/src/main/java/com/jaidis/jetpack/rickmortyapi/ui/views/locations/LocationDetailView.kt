package com.jaidis.jetpack.rickmortyapi.ui.views.locations

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
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.RocketLaunch
import androidx.compose.material.icons.outlined.TravelExplore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jaidis.jetpack.rickmortyapi.R
import com.jaidis.jetpack.rickmortyapi.data.Location
import com.jaidis.jetpack.rickmortyapi.ui.views.shared.InfoIconRow
import com.jaidis.jetpack.rickmortyapi.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LocationDetailView(viewModel: MainViewModel, locationId: String, popBack: () -> Unit) {
    val (location, setLocation) = remember { mutableStateOf<Location?>(null) }
    LaunchedEffect(locationId) {
        setLocation(viewModel.getLocation(locationId))
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.location)) },
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
                location?.let {
                    item(location.id) {
                        Text(
                            location.name ?: "",
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
                            label = stringResource(R.string.lo_type),
                            item = location.type ?: stringResource(R.string.generic_unknown),
                            icon = Icons.Outlined.TravelExplore
                        )
                        InfoIconRow(
                            label = stringResource(R.string.lo_dimension),
                            item = location.dimension ?: stringResource(R.string.generic_unknown),
                            icon = Icons.Outlined.RocketLaunch
                        )
                        InfoIconRow(
                            label = stringResource(R.string.lo_total_ch),
                            item = location.residents?.size.toString(),
                            icon = Icons.Outlined.Numbers
                        )
                    }
                }
            }

        }
    }
}
