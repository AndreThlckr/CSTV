package io.github.andrethlckr.cstv.match.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.domain.MatchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchListViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        MatchListState(
            isLoading = true,
            matches = emptyList()
        )
    )
    val state = _state.asStateFlow()

    init {
        loadMatches()
    }

    private fun loadMatches() {
        viewModelScope.launch {
            when(val result = repository.getMatches()) {
                is NetworkResult.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        matches = result.data
                    )
                }
                is NetworkResult.Failure -> _state.update {
                    it.copy(
                        isLoading = false,
                        matches = emptyList()
                    )
                }
            }
        }
    }
}
