package com.example.expirytracker

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(
    private val ctx: Context,
    private val list: List<Category>,
    private val onClick: (Category) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvEmoji: TextView = view.findViewById(R.id.tvCategoryEmoji)
        val tvName: TextView = view.findViewById(R.id.tvCategoryName)
        val tvCount: TextView = view.findViewById(R.id.tvItemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(ctx)
            .inflate(R.layout.item_category_card, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val cat = list[position]
        holder.tvEmoji.text = cat.emoji
        holder.tvName.text = cat.name
        holder.tvCount.text = "${cat.itemCount} items"
        holder.itemView.setBackgroundColor(Color.parseColor(cat.color))
        holder.itemView.setOnClickListener { onClick(cat) }
    }

    override fun getItemCount() = list.size
}
