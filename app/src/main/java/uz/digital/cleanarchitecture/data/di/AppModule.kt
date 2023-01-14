package uz.digital.cleanarchitecture.data.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.digital.cleanarchitecture.data.repository.AuthRepositoryImpl
import uz.digital.cleanarchitecture.domain.repository.AuthRepository
import uz.digital.cleanarchitecture.domain.use_case.auth_use_case.LogOutUseCase
import uz.digital.cleanarchitecture.domain.use_case.auth_use_case.LoginUseCase
import uz.digital.cleanarchitecture.domain.use_case.auth_use_case.RegisterUseCase
import uz.digital.cleanarchitecture.domain.use_case.base.AllUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
    @Singleton
    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthRepository {
        return AuthRepositoryImpl(auth, firestore)
    }

    @Singleton
    @Provides
    fun provideAllUseCases(
        authRepository: AuthRepository
    ): AllUseCases {
        return AllUseCases(
            loginUseCase = LoginUseCase(authRepository),
            registerUseCase = RegisterUseCase(authRepository),
           logOutUseCase = LogOutUseCase(authRepository)
        )
    }
}