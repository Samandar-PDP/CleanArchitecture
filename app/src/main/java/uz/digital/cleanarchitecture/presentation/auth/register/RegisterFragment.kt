package uz.digital.cleanarchitecture.presentation.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import uz.digital.cleanarchitecture.R
import uz.digital.cleanarchitecture.databinding.FragmentRegisterBinding
import uz.digital.cleanarchitecture.domain.model.User
import uz.digital.cleanarchitecture.presentation.auth.login.LoginState
import uz.digital.cleanarchitecture.presentation.base.BaseFragment
import uz.digital.cleanarchitecture.util.viewBinding

class RegisterFragment : BaseFragment(R.layout.fragment_register) {
    private val viewModel: RegisterViewModel by viewModels()
    private val binding by viewBinding { FragmentRegisterBinding.bind(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.apply {
            btnRegister.click {
                val fullName = fullName.text.toString().trim()
                val email = binding.email.text.toString().trim()
                val password = binding.password.text.toString().trim()

                if (validate(email, password) && fullName.isNotBlank()) {
                    viewModel.register(User(fullName, email, password))
                } else {
                    snackBar("Enter correct data")
                }
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                when(it) {
                    is RegisterState.Idle -> Unit
                    is RegisterState.Loading -> {
                        binding.prg.isVisible = true
                        binding.btnRegister.isVisible = false
                    }
                    is RegisterState.Error -> {
                        binding.btnRegister.isVisible = true
                        binding.prg.isVisible = false
                        snackBar(it.message)
                    }
                    is RegisterState.Success -> {
                        binding.btnRegister.isVisible = true
                        binding.prg.isVisible = false
                        snackBar("Successfully registered")
                        startActivity()
                    }
                }
            }
        }
    }
}