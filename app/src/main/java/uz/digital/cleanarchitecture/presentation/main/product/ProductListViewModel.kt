package uz.digital.cleanarchitecture.presentation.main.product

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.digital.cleanarchitecture.domain.use_case.base.AllUseCases
import uz.digital.cleanarchitecture.presentation.main.add_update.AddUpdateEvent
import uz.digital.cleanarchitecture.presentation.main.add_update.AddUpdateState
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val allUseCases: AllUseCases
) : ViewModel() {

    private val _state: MutableStateFlow<ProductListState> = MutableStateFlow(ProductListState.Idle)
    val state: StateFlow<ProductListState> get() = _state

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            allUseCases.getAllProductUseCase(Unit).collect { response ->
                when (response) {
                    is Response.Loading -> {
                        _state.update {
                            ProductListState.Loading
                        }
                        delay(1000L)
                    }
                    is Response.Error -> _state.update {
                        ProductListState.Error(response.message)
                    }
                    is Response.Success -> _state.update {
                        ProductListState.Success(response.data)
                    }
                }
            }
        }
    }

    fun logOut() {
        viewModelScope.launch {
            allUseCases.logOutUseCase.logOut()
            Log.d("@@@", "logOut: viwmodel")
        }
    }
}