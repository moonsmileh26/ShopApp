package mx.com.liverpool.shopapp.products.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import mx.com.liverpool.shopapp.R
import mx.com.liverpool.shopapp.databinding.FragmentProductsBinding
import mx.com.liverpool.shopapp.products.domain.model.Product
import mx.com.liverpool.shopapp.products.presentation.ProductsState
import mx.com.liverpool.shopapp.products.presentation.adapter.PaginationScrollListener
import mx.com.liverpool.shopapp.products.presentation.adapter.ProductsAdapter
import mx.com.liverpool.shopapp.products.presentation.viewmodel.ProductsViewModel

@AndroidEntryPoint
class ProductsFragment : Fragment(),
    SearchView.OnQueryTextListener {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModels()

    private lateinit var productsAdapter: ProductsAdapter

    var lastWordSearched = ""
    var isLoading = true


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
        initSearchView()
        initSpinner()
        initRecyclerView()
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is ProductsState.Loading -> showLoadingIndicator(state.isLoading)
                        is ProductsState.Success -> showProducts(state.products)
                        is ProductsState.SuccessSorted -> showProductsSorted(state.products)
                        is ProductsState.Error -> showErrorMessage()
                    }
                }
            }
        }
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(this)
    }

    private fun initSpinner() {
        val sortTypes = resources.getStringArray(R.array.SortTypes)
        binding.spinnerSortTypes.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, sortTypes
        )
        binding.spinnerSortTypes.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if ((position == 1) or (position == 2)) {
                    productsAdapter.clearList()
                    viewModel.sortProducts(lastWordSearched, position)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }

    private fun initRecyclerView() {
        productsAdapter = ProductsAdapter()

        val linearLayoutManager = LinearLayoutManager(context)

        binding.recyclerViewProducts.apply {
            layoutManager = linearLayoutManager
            adapter = productsAdapter
            addOnScrollListener(object : PaginationScrollListener(linearLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.searchProductsByTerm(lastWordSearched)
                }

                override fun isLoading(): Boolean = isLoading
            })
        }
    }

    private fun showProducts(products: List<Product>) {
        productsAdapter.updateList(products)
    }

    private fun showProductsSorted(products: List<Product>) {
        productsAdapter.clearList()
        showProducts(products)
    }

    private fun showErrorMessage() {
        Snackbar.make(binding.root, getText(R.string.error_message), Snackbar.LENGTH_SHORT).show()
    }

    private fun showLoadingIndicator(isLoading: Boolean) {
        this.isLoading = isLoading
        if (isLoading)
            (activity as HomeActivity).showLoadingView()
        else
            (activity as HomeActivity).hideLoadingView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchLogic(query)
        return false
    }

    private fun searchLogic(searchString: String?) {
        if (searchString?.isNotEmpty() == true) {
            productsAdapter.clearList()
            viewModel.searchProductsByTerm(searchString)
            lastWordSearched = searchString
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
