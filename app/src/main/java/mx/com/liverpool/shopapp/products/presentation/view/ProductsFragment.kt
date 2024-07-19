package mx.com.liverpool.shopapp.products.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mx.com.liverpool.shopapp.R
import mx.com.liverpool.shopapp.databinding.FragmentProductsBinding
import mx.com.liverpool.shopapp.products.domain.model.Product
import mx.com.liverpool.shopapp.products.presentation.ProductsState
import mx.com.liverpool.shopapp.products.presentation.viewmodel.ProductsViewModel

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initUIState()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is ProductsState.Loading -> showLoadingIndicator()
                        is ProductsState.Success -> showProducts(state.products)
                        is ProductsState.Error -> showErrorMessage()
                    }
                }
            }
        }
    }

    private fun showProducts(products: List<Product>) {
    }

    private fun showErrorMessage() {
        Snackbar.make(binding.root, getText(R.string.error_message), Snackbar.LENGTH_SHORT).show()
    }

    private fun showLoadingIndicator() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}