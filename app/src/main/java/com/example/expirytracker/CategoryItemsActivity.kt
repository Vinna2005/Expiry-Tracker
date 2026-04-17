package com.example.expirytracker

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class CategoryItemsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_items)

        val catName = intent.getStringExtra("CATEGORY_NAME") ?: "Category"
        findViewById<TextView>(R.id.tvTitle).text = catName
        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        val items = listOf(
            ExpiryItem("Milk",   "2 days left", false, catName, "May 27, 2025"),
            ExpiryItem("Bread",  "5 days left", false, catName, "May 30, 2025"),
            ExpiryItem("Yogurt", "Expired",     true,  catName, "Apr 01, 2025")
        )

        val rv = findViewById<RecyclerView>(R.id.rvItems)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = ItemAdapter(this, items) { item ->
            startActivity(
                Intent(this, ItemDetailActivity::class.java)
                    .putExtra("ITEM_NAME",  item.name)
                    .putExtra("ITEM_STATUS", item.status)
                    .putExtra("ITEM_DATE",  item.expiryDate)
                    .putExtra("CATEGORY",   item.category)
                    .putExtra("IS_EXPIRED", item.isExpired)
            )
        }

        findViewById<MaterialButton>(R.id.btnAddItem).setOnClickListener {
            startActivity(
                Intent(this, AddItemActivity::class.java)
                    .putExtra("category", catName)
            )
        }
    }
}