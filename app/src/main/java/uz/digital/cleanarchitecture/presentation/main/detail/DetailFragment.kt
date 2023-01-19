package uz.digital.cleanarchitecture.presentation.main.detail

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
import uz.digital.cleanarchitecture.databinding.FragmentDetailBinding
import uz.digital.cleanarchitecture.domain.model.Product
import uz.digital.cleanarchitecture.presentation.base.BaseFragment
import uz.digital.cleanarchitecture.util.viewBinding

class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val binding by viewBinding { FragmentDetailBinding.bind(it) }
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var product: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        product = arguments?.getParcelable("product")!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            toolBar.title = product.name
            prg.isVisible = false
            btnDelete.isVisible = true
            name.text = product.name
            price.text = product.price.toString()
            toolBar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }

            btnDelete.click {
                viewModel.deleteProduct(product)
            }
        }
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    DetailState.Idle -> Unit
                    DetailState.Loading -> {
                        binding.prg.isVisible = true
                        binding.btnDelete.isVisible = false
                    }
                    DetailState.Success -> {
                        binding.prg.isVisible = false
                        binding.btnDelete.isVisible = true
                        snackBar("Deleted")
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }
}