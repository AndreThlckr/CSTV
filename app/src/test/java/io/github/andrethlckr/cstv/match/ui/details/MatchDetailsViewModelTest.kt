package io.github.andrethlckr.cstv.match.ui.details

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.domain.Match
import io.github.andrethlckr.cstv.match.domain.MatchId
import io.github.andrethlckr.cstv.match.domain.MatchRepository
import io.github.andrethlckr.cstv.match.domain.MatchTest.Companion.matchWith
import io.github.andrethlckr.test.rules.MainCoroutineRule
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MatchDetailsViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule(
        dispatcher = UnconfinedTestDispatcher()
    )

    private val repository: MatchRepository = mockk()

    private fun viewModel(
        matchResult: NetworkResult<Match> = NetworkResult.Success(matchWith(id = MatchId(5)))
    ): MatchDetailsViewModel {
        coEvery { repository.getMatchDetails(MatchId(5)) } returns matchResult

        return MatchDetailsViewModel(
            repository = repository
        )
    }

    @Test
    fun `state should be loading at start and match should be null`() = runTest {
        val viewModel = viewModel()

        val state = viewModel.state.value

        state.isLoading shouldBe true
        state.match.shouldBeNull()
    }

    @Test
    fun `setMatchId should load match details`() = runTest {
        val viewModel = viewModel(
            matchResult = NetworkResult.Success(
                matchWith(
                    id = MatchId(5),
                    name = "Test Match"
                )
            )
        )

        viewModel.setMatchId(MatchId(5))

        val state = viewModel.state.value
        state.isLoading shouldBe false
        state.match shouldBe matchWith(
            id = MatchId(5),
            name = "Test Match"
        )
    }
}
