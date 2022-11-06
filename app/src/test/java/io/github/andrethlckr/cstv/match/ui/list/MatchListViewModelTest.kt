package io.github.andrethlckr.cstv.match.ui.list

import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.domain.Match
import io.github.andrethlckr.cstv.match.domain.MatchRepository
import io.github.andrethlckr.cstv.match.domain.MatchTest.Companion.matchWith
import io.github.andrethlckr.test.rules.MainCoroutineRule
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MatchListViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val repository: MatchRepository = mockk()

    private fun viewModel(
        matchesResult: NetworkResult<List<Match>> = NetworkResult.Success(listOf(matchWith()))
    ): MatchListViewModel {
        coEvery { repository.getMatches() } returns matchesResult

        return MatchListViewModel(
            repository = repository
        )
    }

    @Test
    fun `state should be loading at start and matches should be empty`() = runTest {
        val viewModel = viewModel()

        val state = viewModel.state.value

        state.isLoading shouldBe true
        state.matches.shouldBeEmpty()
    }

    @Test
    fun `state should load matches from repository`() = runTest {
        val viewModel = viewModel(
            matchesResult = NetworkResult.Success(listOf(matchWith(name = "Test Match")))
        )
        advanceUntilIdle()

        val state = viewModel.state.value

        state.isLoading shouldBe false
        state.matches.first().name shouldBe "Test Match"
    }
}
