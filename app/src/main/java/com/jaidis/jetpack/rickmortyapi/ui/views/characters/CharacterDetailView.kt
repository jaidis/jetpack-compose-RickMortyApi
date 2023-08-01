package com.jaidis.jetpack.rickmortyapi.ui.views.characters

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Navigation
import androidx.compose.material.icons.outlined.QueryStats
import androidx.compose.material.icons.outlined.Transgender
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jaidis.jetpack.rickmortyapi.R
import com.jaidis.jetpack.rickmortyapi.data.Character
import com.jaidis.jetpack.rickmortyapi.ui.views.shared.InfoIconRow
import com.jaidis.jetpack.rickmortyapi.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CharacterDeatilView(viewModel: MainViewModel, characterId: String, popBack: () -> Unit) {
    val (character, setCharacter) = remember { mutableStateOf<Character?>(null) }
    LaunchedEffect(characterId) {
        setCharacter(viewModel.getCharacter(characterId))
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.character)) },
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
                character?.let {
                    item(character.id) {
                        Text(
                            character.name ?: "",
                            style = MaterialTheme.typography.h4,
                            color = LocalContentColor.current,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                        Text(
                            stringResource(R.string.ch_mugshot),
                            style = MaterialTheme.typography.subtitle2,
                            color = LocalContentColor.current,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                        Surface() {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                AsyncImage(
                                    model = character.image,
                                    contentDescription = character.name,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .size(150.dp)
                                        .clip(RoundedCornerShape(20.dp))
                                )
                            }
                        }
                        Text(
                            stringResource(R.string.ch_info),
                            style = MaterialTheme.typography.subtitle2,
                            color = LocalContentColor.current,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        InfoIconRow(
                            label = stringResource(R.string.ch_species),
                            item = character.species ?: stringResource(R.string.generic_unknown),
                            icon = Icons.Outlined.Badge
                        )
                        InfoIconRow(
                            label = stringResource(R.string.ch_gender),
                            item = character.gender ?: stringResource(R.string.generic_unknown),
                            icon = Icons.Outlined.Transgender
                        )
                        InfoIconRow(
                            label = stringResource(R.string.ch_status),
                            item = character.status ?: stringResource(R.string.generic_unknown),
                            icon = Icons.Outlined.QueryStats
                        )
                        InfoIconRow(
                            label = stringResource(R.string.ch_location),
                            item = character.location?.name
                                ?: stringResource(R.string.generic_unknown),
                            icon = Icons.Outlined.Navigation
                        )
                        InfoIconRow(
                            label = stringResource(R.string.ch_origin),
                            item = character.origin?.name
                                ?: stringResource(R.string.generic_unknown),
                            icon = Icons.Outlined.Home
                        )
                    }
                }
            }

        }
    }
}
