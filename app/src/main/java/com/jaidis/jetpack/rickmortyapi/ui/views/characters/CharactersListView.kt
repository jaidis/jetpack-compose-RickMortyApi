package com.jaidis.jetpack.rickmortyapi.ui.views.characters

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.jaidis.jetpack.rickmortyapi.R
import com.jaidis.jetpack.rickmortyapi.data.Character
import com.jaidis.jetpack.rickmortyapi.viewmodel.MainViewModel

@Composable
fun CharactersListView(
    viewModel: MainViewModel,
    bottomBar: @Composable () -> Unit,
    characterSelected: (character: Character) -> Unit
) {
    val characterList = viewModel.characters
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.characters)) }) },
        bottomBar = bottomBar
    )
    {
        if (characterList != null) {
            LazyColumn(contentPadding = it) {
                items(items = characterList) { it ->
                    CharactersListRowView(it, characterSelected)
                }
            }
        } else {
            viewModel.getCharacters()
        }
    }
}
