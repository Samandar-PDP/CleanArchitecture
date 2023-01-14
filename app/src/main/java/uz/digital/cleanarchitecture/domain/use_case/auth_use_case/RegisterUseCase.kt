package uz.digital.cleanarchitecture.domain.use_case.auth_use_case

import kotlinx.coroutines.flow.Flow
import uz.digital.cleanarchitecture.domain.model.User
import uz.digital.cleanarchitecture.domain.repository.AuthRepository
import uz.digital.cleanarchitecture.domain.use_case.base.BaseUseCase
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

typealias RegisterBaseUseCase = BaseUseCase<User, Flow<Response<Boolean>>>

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository
) : RegisterBaseUseCase {
    override suspend fun invoke(params: User): Flow<Response<Boolean>> {
        return repository.register(params)
    }
}