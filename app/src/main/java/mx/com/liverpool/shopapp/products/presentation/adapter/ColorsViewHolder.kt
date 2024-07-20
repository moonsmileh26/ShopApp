package mx.com.liverpool.shopapp.products.presentation.adapter

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import mx.com.liverpool.shopapp.databinding.ItemColorBinding

class ColorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemColorBinding.bind(view)

    fun render(color: String) {
        println(color)
        binding.imageviewCircle.setColorFilter(Color.parseColor(color))
    }

}