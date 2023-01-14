package uz.digital.cleanarchitecture.presentation.auth.login

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
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: AllUseCases
) : ViewModel() {
    private val _state: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val state: StateFlow<LoginState> get() = _state

    fun login(user: User) {
        viewModelScope.launch {
            useCases.loginUseCase(user).collect { response ->
                when(response) {
                    is Response.Loading -> {
                        _state.update {
                            LoginState.Loading
                        }
                        delay(500L)
                    }
                    is Response.Error -> {
                        _state.update {
                            LoginState.Error(response.message)
                        }
                    }
                    is Response.Success -> {
                        _state.update {
                            LoginState.Success
                        }
                    }
                }
            }
        }
    }
}