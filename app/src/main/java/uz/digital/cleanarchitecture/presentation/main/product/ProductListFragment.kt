package uz.digital.cleanarchitecture.presentation.main.product

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import uz.digital.cleanarchitecture.R
import uz.digital.cleanarchitecture.databinding.FragmentAddUpdateBinding
import uz.digital.cleanarchitecture.databinding.FragmentProductListBinding
import uz.digital.cleanarchitecture.presentation.base.BaseFragment
import uz.digital.cleanarchitecture.util.viewBinding

class ProductListFragment : BaseFragment(R.layout.fragment_product_list) {
    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ProductListViewModel>()
    private val productListAdapter by lazy { ProductListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.btnLogOut.click {
            showDialog()
        }
        binding.fab.click {
            findNavController().navigate(R.id.action_productListFragment_to_addUpdateFragment)
        }
        binding.rv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productListAdapter
        }
        productListAdapter.onDetailClick = {
            val product = bundleOf("product" to it)
            findNavController().navigate(R.id.action_productListFragment_to_detailFragment)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is ProductListState.Idle -> Unit
                    is ProductListState.Loading -> {
                        binding.prg.isVisible = true
                    }
                    is ProductListState.Error -> {
                        binding.prg.isVisible = false
                        snackBar(it.message)
                    }
                    is ProductListState.Success -> {
                        binding.prg.isVisible = false
                        productListAdapter.submitList(it.productList)
                    }
                }
            }
        }
    }

    private fun showDialog() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Log Out")
            setMessage("Do you want to log out?")
            setPositiveButton("Yes") { di, _ ->
                viewModel.logOut()
                binding.prg.isVisible = true
                di.dismiss()
                startAuthActivity()
            }
            setNegativeButton("Cancel", null)
        }.show()
    }
}