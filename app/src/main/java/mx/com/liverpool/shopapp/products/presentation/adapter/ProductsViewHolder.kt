package mx.com.liverpool.shopapp.products.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import mx.com.liverpool.shopapp.R
import mx.com.liverpool.shopapp.databinding.ItemProductBinding
import mx.com.liverpool.shopapp.products.domain.model.Product

class ProductsViewHolder(val view: View) : ViewHolder(view) {
    private val binding = ItemProductBinding.bind(view)

    fun render(product: Product) {
        binding.textviewProductName.text = product.name
        binding.textviewPriceList.text =
            view.context.getString(R.string.price_list, product.listPrice)
        binding.textviewPricePromo.text =
            view.context.getString(R.string.price_list, product.promoPrice)
        Glide.with(view).load(product.image).into(binding.imageviewProduct)
    }

}