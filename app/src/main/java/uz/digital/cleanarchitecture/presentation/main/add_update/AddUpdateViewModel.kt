package uz.digital.cleanarchitecture.presentation.main.add_update

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.digital.cleanarchitecture.domain.use_case.base.AllUseCases
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

@HiltViewModel
class AddUpdateViewModel @Inject constructor(
    private val useCases: AllUseCases
) : ViewModel() {
    private val _state: MutableStateFlow<AddUpdateState> = MutableStateFlow(AddUpdateState.Idle)
    val state: StateFlow<AddUpdateState> get() = _state

    fun onEvent(event: AddUpdateEvent) {
        when (event) {
            is AddUpdateEvent.OnCreateProduct -> {
                viewModelScope.launch {
                    useCases.createProductUseCase(event.product).collect { response ->
                        when (response) {
                            is Response.Loading -> {
                                _state.update {
                                    AddUpdateState.Loading
                                }
                                delay(500L)
                            }
                            is Response.Error -> {
                                _state.update {
                                    AddUpdateState.Error(response.message)
                                }
                            }
                            is Response.Success -> {
                                _state.update {
                                    AddUpdateState.SuccessCreate
                                }
                            }
                        }
                    }
                }
            }
            is AddUpdateEvent.OnUpdateProduct -> {
                viewModelScope.launch {
                    _state.update {
                        AddUpdateState.Loading
                    }
                    delay(1000L)
                    when (val response =
                        useCases.updateProductUseCase.invoke(event.oldProduct, event.newProduct)) {
                        is Response.Loading -> Unit
                        is Response.Error -> _state.update {
                            AddUpdateState.Error(response.message)
                        }
                        is Response.Success -> _state.update {
                            AddUpdateState.SuccessUpdate
                        }
                    }
                }
            }
        }
    }
}