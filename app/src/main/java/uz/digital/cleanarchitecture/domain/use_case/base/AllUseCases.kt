package uz.digital.cleanarchitecture.domain.use_case.base

import uz.digital.cleanarchitecture.domain.use_case.auth_use_case.LogOutUseCase
import uz.digital.cleanarchitecture.domain.use_case.auth_use_case.LoginUseCase
import uz.digital.cleanarchitecture.domain.use_case.auth_use_case.RegisterUseCase
import uz.digital.cleanarchitecture.domain.use_case.product_use_case.CreateProductUseCase
import uz.digital.cleanarchitecture.domain.use_case.product_use_case.GetAllProductUseCase

data class AllUseCases(
    val loginUseCase: LoginUseCase,
    val registerUseCase: RegisterUseCase,
    val logOutUseCase: LogOutUseCase,
    val createProductUseCase: CreateProductUseCase,
    val getAllProductUseCase: GetAllProductUseCase
)