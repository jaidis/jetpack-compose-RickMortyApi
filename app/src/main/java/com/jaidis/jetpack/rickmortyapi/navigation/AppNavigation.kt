package com.jaidis.jetpack.rickmortyapi.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jaidis.jetpack.rickmortyapi.ui.views.characters.CharactersListView
import com.jaidis.jetpack.rickmortyapi.ui.views.episodes.EpisodesListView
import com.jaidis.jetpack.rickmortyapi.ui.views.locations.LocationsListView
import com.jaidis.jetpack.rickmortyapi.viewmodel.MainViewModel

@Composable
fun AppNavigation(navController: NavHostController, viewModel: MainViewModel) {

    val bottomNavigationItems =
        listOf(AppScreens.CharactersScreen, AppScreens.EpisodesScreen, AppScreens.LocationsScreen)
    val bottomBar: @Composable () -> Unit = { AppBottomNav(navController, bottomNavigationItems) }
    NavHost(navController, startDestination = AppScreens.CharactersScreen.route) {
        composable(AppScreens.CharactersScreen.route) {
            CharactersListView(viewModel, bottomBar) {
                navController.navigate(AppScreens.CharacterDetailsScreen.route + "/${it.id}")
            }
        }
        /*composable(AppScreens.CharacterDetailsScreen.route + "/{id}") { backStackEntry ->
            CharacterDetailView(viewModel, backStackEntry.arguments?.get("id") as String, popBack = { navController.popBackStack() })
        }*/
        composable(AppScreens.EpisodesScreen.route) {
            EpisodesListView(viewModel, bottomBar) {
                navController.navigate(AppScreens.EpisodeDetailsScreen.route + "/${it.id}")
            }
        }
        /*composable(AppScreens.EpisodeDetailsScreen.route + "/{id}") { backStackEntry ->
            EpisodeDetailView(viewModel, backStackEntry.arguments?.get("id") as String, popBack = { navController.popBackStack() })
        }*/
        composable(AppScreens.LocationsScreen.route) {
            LocationsListView(viewModel, bottomBar) {
                navController.navigate(AppScreens.LocationDetailsScreen.route + "/${it.id}")
            }
        }
        /*composable(AppScreens.LocationDetailsScreen.route + "/{id}") { backStackEntry ->
            LocationDetailView(viewModel, backStackEntry.arguments?.get("id") as String, popBack = { navController.popBackStack() })
        }*/
    }
}

@Composable
private fun AppBottomNav(
    navController: NavHostController,
    items: List<AppScreens>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    screen.icon?.let {
                        Icon(
                            screen.icon,
                            contentDescription = screen.label
                        )
                    }
                },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }

}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}