package io.github.andrethlckr.cstv.match.data

import io.github.andrethlckr.cstv.core.data.dataOrNull
import io.github.andrethlckr.cstv.match.domain.MatchRepository
import io.kotest.matchers.collections.shouldNotBeEmpty
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MatchRepositoryImplTest {

    private val repository: MatchRepository = MatchRepositoryImpl()

    @Test
    fun `getMatches should return list of matches`() = runTest {
        val matches = repository.getMatches().dataOrNull()

        matches.shouldNotBeEmpty()
    }
}