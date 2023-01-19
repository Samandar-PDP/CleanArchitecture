package uz.digital.cleanarchitecture.presentation.main.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.digital.cleanarchitecture.domain.model.Product
import uz.digital.cleanarchitecture.domain.use_case.base.AllUseCases
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: AllUseCases
) : ViewModel() {
    private val _state: MutableStateFlow<DetailState> = MutableStateFlow(DetailState.Idle)
    val state: StateFlow<DetailState> get() = _state


    fun deleteProduct(product: Product) {
        viewModelScope.launch {
            useCases.deleteProductUseCase(product)
                .collect { res ->
                    when (res) {
                        is Response.Error -> Unit
                        is Response.Loading -> _state.update {
                            DetailState.Loading
                        }
                        is Response.Success -> _state.update {
                            DetailState.Success
                        }
                    }
                }

        }
    }
}