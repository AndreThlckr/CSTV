package io.github.andrethlckr.cstv.match.ui.list

import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.shouldBe
import org.junit.Test

class MatchListViewModelTest {

    private fun viewModel() = MatchListViewModel()

    @Test
    fun `state should be loading at start and matches should be empty`() {
        val viewModel = viewModel()

        val state = viewModel.state.value

        state.isLoading shouldBe true
        state.matches.shouldBeEmpty()
    }
}
