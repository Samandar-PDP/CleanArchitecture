package uz.digital.cleanarchitecture.presentation.splash

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val auth: FirebaseAuth
): ViewModel() {
    fun isUserNotNull() = auth.currentUser != null
}