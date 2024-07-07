package com.example.screentransition.myMode.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.screentransition.myMode.data.common.MyResult
import com.example.screentransition.myMode.data.model.MyData
import com.example.screentransition.myMode.data.model.MyName
import com.example.screentransition.myMode.data.repository.MyDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: MyDataRepository
) : ViewModel() {
    private val viewModelState = MutableStateFlow(INITIAL_VM_STATE)

    val uiState: StateFlow<MyUiState> = viewModelState
        .map { it.toUiState() }
        .stateIn(
            scope = viewModelScope,
            initialValue = INITIAL_VM_STATE.toUiState(),
            started = SharingStarted.WhileSubscribed(5_000),
        )

    init {
        repository.fetchMyData()
            .onStart { viewModelState.update { it.copy(isLoading = true) } }
            .catch { e ->
                viewModelState.update {
                    it.copy(errorMessage = it.errorMessage + ErrorMessage(e, e.message ?: ""))
                }
            }
            .onCompletion { viewModelState.update { it.copy(isLoading = false) } }
            .map { myDataList -> viewModelState.update { it.copy(myDataList = myDataList) } }
            .launchIn(viewModelScope)
    }

    fun saveData(name: String) = viewModelScope.launch {
        repository.saveMyData(MyName(value = name)).let { result ->
            viewModelState.update {
                when (result) {
                    is MyResult.Success -> {
                        it.copy(isLoading = false)
                    }
                    is MyResult.Error -> {
                        it.copy(
                            errorMessage = it.errorMessage + ErrorMessage(
                                error = result.exception,
                                message = result.exception.message ?: ""
                            )
                        )
                    }
                }
            }
        }
    }

    fun switchLocaleDialog(show: Boolean) {
        viewModelState.update { it.copy(showLocaleDialog = show) }
    }
}

private val INITIAL_VM_STATE = MyViewModelState()

data class MyViewModelState(
    val myDataList: List<MyData> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: List<ErrorMessage> = emptyList(),
    val showLocaleDialog: Boolean = false
) {
    fun toUiState(): MyUiState =
        if (myDataList.isEmpty()) {
            MyUiState.NoData(
                isLoading = isLoading,
                errorMessage = errorMessage,
                showLocaleDialog = showLocaleDialog
            )
        } else {
            MyUiState.HasData(
                myDataList = myDataList,
                isLoading = isLoading,
                errorMessage = errorMessage,
                showLocaleDialog = showLocaleDialog
            )
        }
}

sealed interface MyUiState {
    val isLoading: Boolean
    val errorMessage: List<ErrorMessage>
    val showLocaleDialog: Boolean

    data class NoData(
        override val isLoading: Boolean,
        override val errorMessage: List<ErrorMessage>,
        override val showLocaleDialog: Boolean,
    ) : MyUiState

    data class HasData(
        val myDataList: List<MyData>,
        override val isLoading: Boolean,
        override val errorMessage: List<ErrorMessage>,
        override val showLocaleDialog: Boolean,
    ) : MyUiState
}

data class ErrorMessage(private val error: Throwable, val message: String)
