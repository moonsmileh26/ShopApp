package mx.com.liverpool.shopapp.products.presentation.adapter

import android.graphics.Paint
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import mx.com.liverpool.shopapp.R
import mx.com.liverpool.shopapp.databinding.ItemProductBinding
import mx.com.liverpool.shopapp.products.domain.model.Product


class ProductsViewHolder(val view: View) : ViewHolder(view) {
    private val binding = ItemProductBinding.bind(view)

    fun render(product: Product) {
        Glide.with(view).load(product.image).into(binding.imageviewProduct)
        binding.textviewProductName.text = product.name
        binding.textviewPriceList.text =
            view.context.getString(R.string.price_list, product.listPrice)

        binding.textviewPriceList.paintFlags =
            binding.textviewPriceList.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        binding.textviewPricePromo.text =
            view.context.getString(R.string.price_list, product.promoPrice)

        binding.recyclerviewColors.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        binding.recyclerviewColors.adapter = ColorsAdapter(product.colors)

    }

}