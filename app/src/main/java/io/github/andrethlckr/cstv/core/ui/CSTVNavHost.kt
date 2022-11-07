package io.github.andrethlckr.cstv.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.github.andrethlckr.cstv.match.domain.MatchId
import io.github.andrethlckr.cstv.match.ui.details.MatchDetailsDestination
import io.github.andrethlckr.cstv.match.ui.details.MatchDetailsViewModel
import io.github.andrethlckr.cstv.match.ui.list.MatchListDestination

@Composable
fun CSTVNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "match/list") {
        composable("match/list") {
            MatchListDestination(
                viewModel = hiltViewModel()
            )
        }

        composable(
            route = "match/details/{matchId}",
            arguments = listOf(navArgument("matchId") { type = NavType.LongType })
        ) {
            val matchId = it.arguments?.getLong("matchId")
            val viewModel: MatchDetailsViewModel = hiltViewModel()

            DisposableEffect(matchId) {
                if (matchId == null) navController.navigateUp()
                else viewModel.setMatchId(MatchId(matchId))

                onDispose {  }
            }

            MatchDetailsDestination()
        }
    }
}
