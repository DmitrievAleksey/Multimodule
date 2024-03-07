package com.example.feature.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.core.data.models.ModelRepository
import com.example.feature.sample.SampleFeatureUiState.Error
import com.example.feature.sample.SampleFeatureUiState.Loading
import com.example.feature.sample.SampleFeatureUiState.Success

@HiltViewModel
class SampleFeatureViewModel @Inject constructor(
    private val modelRepository: ModelRepository
) : ViewModel() {

    val uiState: StateFlow<SampleFeatureUiState> = modelRepository
        .myModels.map<List<String>, SampleFeatureUiState> { Success(data = it) }
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000), Loading)

    fun addText(text: String) {
        viewModelScope.launch {
            modelRepository.add(text)
        }
    }
}

sealed interface SampleFeatureUiState {
    data object Loading : SampleFeatureUiState
    data class Error(val throwable: Throwable) : SampleFeatureUiState
    data class Success(val data: List<String>) : SampleFeatureUiState
}
