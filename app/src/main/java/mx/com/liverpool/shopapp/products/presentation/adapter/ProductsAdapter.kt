package mx.com.liverpool.shopapp.products.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.com.liverpool.shopapp.R
import mx.com.liverpool.shopapp.products.domain.model.Product

class ProductsAdapter(private var products: MutableList<Product> = mutableListOf()) :
    RecyclerView.Adapter<ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.render(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun updateList(products: List<Product>) {
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    fun clearList() {
        this.products.removeAll(products)
    }
}