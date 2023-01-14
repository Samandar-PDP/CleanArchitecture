package uz.digital.cleanarchitecture.presentation.main.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import uz.digital.cleanarchitecture.R
import uz.digital.cleanarchitecture.databinding.FragmentProductListBinding
import uz.digital.cleanarchitecture.presentation.base.BaseFragment
import uz.digital.cleanarchitecture.util.viewBinding

class ProductListFragment : BaseFragment(R.layout.fragment_product_list) {
    private val binding by viewBinding { FragmentProductListBinding.bind(it) }
    private val viewModel by viewModels<ProductListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.btnLogOut.click {

        }
    }
}