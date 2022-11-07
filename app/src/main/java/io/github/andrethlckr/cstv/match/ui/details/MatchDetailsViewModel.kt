package io.github.andrethlckr.cstv.match.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.andrethlckr.cstv.core.data.NetworkResult
import io.github.andrethlckr.cstv.match.domain.MatchId
import io.github.andrethlckr.cstv.match.domain.MatchRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchDetailsViewModel @Inject constructor(
    private val repository: MatchRepository
) : ViewModel() {

    private val _state = MutableStateFlow(
        MatchDetailsState(
            isLoading = true,
            match = null
        )
    )
    val state = _state.asStateFlow()

    fun setMatchId(id: MatchId) {
        loadMatchWith(id)
    }

    private fun loadMatchWith(id: MatchId) {
        viewModelScope.launch {
            when(val result = repository.getMatchDetails(id)) {
                is NetworkResult.Success -> _state.update {
                    it.copy(
                        isLoading = false,
                        match = result.data
                    )
                }
                is NetworkResult.Failure -> loadMatchWith(id)
            }
        }
    }
}
