package uz.digital.cleanarchitecture.presentation.auth.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.digital.cleanarchitecture.domain.model.User
import uz.digital.cleanarchitecture.domain.use_case.base.AllUseCases
import uz.digital.cleanarchitecture.presentation.auth.login.LoginState
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCases: AllUseCases
) : ViewModel() {
    private val _state: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState.Idle)
    val state: StateFlow<RegisterState> get() = _state

    fun register(user: User) {
        viewModelScope.launch {
            useCases.registerUseCase(user).collect { response ->
                when(response) {
                    is Response.Loading -> {
                        _state.update {
                            RegisterState.Loading
                        }
                        delay(500L)
                    }
                    is Response.Error -> {
                        _state.update {
                            RegisterState.Error(response.message)
                        }
                    }
                    is Response.Success -> {
                        _state.update {
                            RegisterState.Success
                        }
                    }
                }
            }
        }
    }
}