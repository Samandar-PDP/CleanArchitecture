package uz.digital.cleanarchitecture.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.digital.cleanarchitecture.domain.model.User
import uz.digital.cleanarchitecture.util.Response

interface AuthRepository {
    suspend fun login(user: User): Flow<Response<Boolean>>
    suspend fun register(user: User): Flow<Response<Boolean>>
    suspend fun logOut(): Flow<Response<Boolean>>
}