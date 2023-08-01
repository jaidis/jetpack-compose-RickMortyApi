package com.jaidis.jetpack.rickmortyapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.jaidis.jetpack.rickmortyapi.navigation.AppNavigation
import com.jaidis.jetpack.rickmortyapi.ui.theme.RickMortyAPITheme
import com.jaidis.jetpack.rickmortyapi.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(viewModel())
        }
    }
}

@Composable
private fun MainScreen(viewModel: MainViewModel) {

    RickMortyAPITheme {
        val navController = rememberNavController()
        AppNavigation(navController, viewModel)
    }
}