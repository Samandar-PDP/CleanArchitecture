package uz.digital.cleanarchitecture.presentation.main.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.digital.cleanarchitecture.domain.use_case.base.AllUseCases
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val allUseCases: AllUseCases
): ViewModel() {

    fun logOut() {
        viewModelScope.launch {
            allUseCases.logOutUseCase.invoke()
        }
    }
}