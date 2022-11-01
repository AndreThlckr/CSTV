package io.github.andrethlckr.cstv.core.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.andrethlckr.cstv.match.ui.details.MatchDetailsDestination
import io.github.andrethlckr.cstv.match.ui.list.MatchListDestination

@Composable
fun CSTVNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "matchlist") {
        composable("matchlist") {
            MatchListDestination()
        }

        composable("matchdetails") {
            MatchDetailsDestination()
        }
    }
}
