package uz.digital.cleanarchitecture.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import uz.digital.cleanarchitecture.R
import uz.digital.cleanarchitecture.databinding.FragmentLoginBinding
import uz.digital.cleanarchitecture.presentation.auth.AuthActivity
import uz.digital.cleanarchitecture.presentation.base.BaseFragment
import uz.digital.cleanarchitecture.presentation.main.MainActivity
import uz.digital.cleanarchitecture.util.viewBinding


class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding by viewBinding { FragmentLoginBinding.bind(it) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            val ac = (activity as AuthActivity)
            ac.startActivity(Intent(ac, MainActivity::class.java))
            ac.finish()
        }
        binding.textView.click {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}