package ru.mby.myapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.mby.myapplication.data.AppItem
import ru.mby.myapplication.data.RemoteRepository

data class HomeUiState(
    val isLoading: Boolean = false,
    val apps: List<AppItem> = emptyList(),
    val error: String? = null,
)

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(HomeUiState(isLoading = true))
    val state: StateFlow<HomeUiState> = _state

    init {
        refresh()
    }

    fun refresh() {
        _state.value = _state.value.copy(isLoading = true, error = null)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apps = RemoteRepository.fetchCatalog()
                _state.value = HomeUiState(isLoading = false, apps = apps)
            } catch (t: Throwable) {
                _state.value = HomeUiState(isLoading = false, error = t.message)
            }
        }
    }
}


