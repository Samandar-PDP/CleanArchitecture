package uz.digital.cleanarchitecture.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import uz.digital.cleanarchitecture.domain.model.User
import uz.digital.cleanarchitecture.domain.repository.AuthRepository
import uz.digital.cleanarchitecture.util.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : AuthRepository {
    private var isSuccessful = false
    override suspend fun login(user: User): Flow<Response<Boolean>> = flow {
        isSuccessful = false
        try {
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(user.email, user.password).addOnSuccessListener {
                isSuccessful = true
            }.await()
            emit(Response.Success(isSuccessful))
        } catch (e: Exception) {
            emit(Response.Error(e.message.toString()))
        }
    }

    override suspend fun register(user: User): Flow<Response<Boolean>>  = flow {
        isSuccessful = false
        try {
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(user.email, user.password).addOnSuccessListener {
                val id = auth.currentUser?.uid!!
                firestore.collection("users").document(id).set(user)
                    .addOnSuccessListener {
                        isSuccessful = true
                    }
            }.await()

            emit(Response.Success(isSuccessful))
        } catch (e: Exception) {
            emit(Response.Error(e.message.toString()))
        }
    }

    override suspend fun logOut(): Boolean {
        auth.signOut()
        return true
    }
}