package uz.digital.cleanarchitecture.presentation.main.add_update

import androidx.lifecycle.ViewModelProvider
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
import uz.digital.cleanarchitecture.databinding.FragmentAddUpdateBinding
import uz.digital.cleanarchitecture.domain.model.Product
import uz.digital.cleanarchitecture.presentation.base.BaseFragment
import uz.digital.cleanarchitecture.util.viewBinding

class AddUpdateFragment : BaseFragment(R.layout.fragment_add_update) {
    private var _binding: FragmentAddUpdateBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddUpdateViewModel by viewModels()
    private var product: Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getParcelable("product")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAddUpdateBinding.bind(view)
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        if (product != null) {
            // update
        } else {
            binding.toolBar.title = "Create"
            binding.createUpdate.text = "Create Product"
        }
        binding.createUpdate.click {
            val name = binding.name.text.toString().trim()
            val price = binding.price.text.toString().trim().toInt()
            if (product != null) {
            } else {
                viewModel.onEvent(
                    AddUpdateEvent.OnCreateProduct(
                        Product(
                            name = name,
                            price = price
                        )
                    )
                )
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is AddUpdateState.Idle -> Unit
                    is AddUpdateState.Loading -> {
                        binding.prg.isVisible = true
                        binding.createUpdate.isVisible = false
                    }
                    is AddUpdateState.Error -> {
                        binding.prg.isVisible = false
                        binding.createUpdate.isVisible = true
                        snackBar(it.message)
                    }
                    is AddUpdateState.SuccessCreate -> {
                        binding.apply {
                            prg.isVisible = false
                            createUpdate.isVisible = true
                            name.text?.clear()
                            price.text?.clear()
                            name.requestFocus()
                        }
                        snackBar("Created")
                    }
                    is AddUpdateState.SuccessUpdate -> {

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}