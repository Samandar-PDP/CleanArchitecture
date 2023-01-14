package uz.digital.cleanarchitecture.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import uz.digital.cleanarchitecture.R
import uz.digital.cleanarchitecture.databinding.FragmentLoginBinding
import uz.digital.cleanarchitecture.domain.model.User
import uz.digital.cleanarchitecture.presentation.auth.AuthActivity
import uz.digital.cleanarchitecture.presentation.base.BaseFragment
import uz.digital.cleanarchitecture.presentation.main.MainActivity
import uz.digital.cleanarchitecture.util.viewBinding

class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding by viewBinding { FragmentLoginBinding.bind(it) }
    private val viewModel: LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.click {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnLogin.click {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            if (validate(email, password)) {
                viewModel.login(User("", email, password))
            } else {
                snackBar("Enter data!")
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                when(it) {
                    is LoginState.Idle -> Unit
                    is LoginState.Loading -> {
                        binding.btnLogin.isVisible = false
                        binding.prg.isVisible = true
                    }
                    is LoginState.Error -> {
                        binding.btnLogin.isVisible = true
                        binding.prg.isVisible = false
                        snackBar(it.message)
                        print("@@@${it.message}")
                    }
                    is LoginState.Success -> {
                        binding.btnLogin.isVisible = true
                        binding.prg.isVisible = false
                        snackBar("Successfully logged in")
                        startActivity()
                    }
                }
            }
        }
    }
}