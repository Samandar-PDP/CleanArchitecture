package uz.digital.cleanarchitecture.domain.use_case.base

import uz.digital.cleanarchitecture.domain.use_case.auth_use_case.LogOutUseCase
import uz.digital.cleanarchitecture.domain.use_case.auth_use_case.LoginUseCase
import uz.digital.cleanarchitecture.domain.use_case.auth_use_case.RegisterUseCase

data class AllUseCases(
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase,
    val logOutUseCase: LogOutUseCase
)