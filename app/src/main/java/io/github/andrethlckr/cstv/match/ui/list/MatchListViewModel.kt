package io.github.andrethlckr.cstv.match.ui.list

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MatchListViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(
        MatchListState(
            isLoading = true,
            matches = emptyList()
        )
    )
    val state = _state.asStateFlow()
}
