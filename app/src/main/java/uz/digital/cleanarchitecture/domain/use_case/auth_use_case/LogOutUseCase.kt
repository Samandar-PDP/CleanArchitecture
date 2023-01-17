package uz.digital.cleanarchitecture.domain.use_case.auth_use_case

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import uz.digital.cleanarchitecture.domain.repository.AuthRepository
import uz.digital.cleanarchitecture.domain.use_case.base.BaseUseCase
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: AuthRepository
)  {
    fun logOut() {
        repository.logOut()
        Log.d("@@@", "logOut: Log out usecase working")
    }
}