package uz.digital.cleanarchitecture.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uz.digital.cleanarchitecture.domain.model.User
import uz.digital.cleanarchitecture.domain.repository.AuthRepository
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : AuthRepository {
    override suspend fun login(user: User): Flow<Response<Boolean>> = flow {
        try {

        } catch (e: Exception) {

        }
    }

    override suspend fun register(user: User): Flow<Response<Boolean>> {

    }

    override suspend fun logOut(): Flow<Response<Boolean>> {

    }
}