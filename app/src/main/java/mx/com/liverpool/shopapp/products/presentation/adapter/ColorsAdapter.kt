package mx.com.liverpool.shopapp.products.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.com.liverpool.shopapp.R

class ColorsAdapter(private var colors: List<String>) :
    RecyclerView.Adapter<ColorsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        return ColorsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        if (colors[position].isNotEmpty())
            holder.render(colors[position])
    }

    override fun getItemCount(): Int = colors.size
}