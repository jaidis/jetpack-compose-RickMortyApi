package com.jaidis.jetpack.rickmortyapi.ui.views.characters

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
import com.jaidis.jetpack.rickmortyapi.data.Character
import com.jaidis.jetpack.rickmortyapi.viewmodel.MainViewModel

fun LazyListState.isScrolledToEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

@Composable
fun CharactersListView(
    viewModel: MainViewModel,
    bottomBar: @Composable () -> Unit,
    characterSelected: (character: Character) -> Unit
) {
    val characterList = viewModel.characters
    val scrollState = rememberLazyListState()
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.characters)) }) },
        bottomBar = bottomBar
    )
    {
        if (characterList != null) {
            LazyColumn(contentPadding = it, state = scrollState) {
                items(items = characterList) { it ->
                    CharactersListRowView(it, characterSelected)
                }
            }
        } else {
            viewModel.getCharacters()
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
        viewModel.getCharactersNext()
    }

}
