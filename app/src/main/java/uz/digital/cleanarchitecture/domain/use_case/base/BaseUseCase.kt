package uz.digital.cleanarchitecture.domain.use_case.base

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}