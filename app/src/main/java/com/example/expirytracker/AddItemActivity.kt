package com.example.expirytracker

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class AddItemActivity : AppCompatActivity() {

    private lateinit var etItemName: TextInputEditText
    private lateinit var etExpiryDate: TextInputEditText
    private lateinit var spinnerCategory: AutoCompleteTextView
    private lateinit var btnSaveItem: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        etItemName = findViewById(R.id.etItemName)
        etExpiryDate = findViewById(R.id.etExpiryDate)
        spinnerCategory = findViewById(R.id.spinnerCategory)
        btnSaveItem = findViewById(R.id.btnSaveItem)

        // Close button
        findViewById<ImageView>(R.id.btnClose).setOnClickListener { finish() }

        // Categories
        val categories = arrayOf("Food", "Medicine", "Cosmetics", "Drinks")
        val adapter = ArrayAdapter(this,
            android.R.layout.simple_dropdown_item_1line, categories)
        spinnerCategory.setAdapter(adapter)

        // Pre-select category if passed
        val passedCategory = intent.getStringExtra("category")
        passedCategory?.let { spinnerCategory.setText(it, false) }

        // Date picker
        etExpiryDate.setOnClickListener {
            val cal = Calendar.getInstance()
            DatePickerDialog(this, { _, year, month, day ->
                etExpiryDate.setText("$day/${month + 1}/$year")
            }, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        // Save
        btnSaveItem.setOnClickListener {
            val name = etItemName.text.toString().trim()
            val category = spinnerCategory.text.toString().trim()
            val date = etExpiryDate.text.toString().trim()

            if (name.isEmpty()) {
                etItemName.error = "Item name daalo"
                return@setOnClickListener
            }
            if (category.isEmpty()) {
                spinnerCategory.error = "Category select karo"
                return@setOnClickListener
            }
            if (date.isEmpty()) {
                etExpiryDate.error = "Expiry date select karo"
                return@setOnClickListener
            }

            Toast.makeText(this, "$name saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}