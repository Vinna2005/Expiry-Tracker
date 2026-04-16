package com.example.expirytracker

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        val email = sharedPref.getString("email", "Not logged in") ?: "Not logged in"
        val name = if (email == "Not logged in") "Guest"
        else email.substringBefore("@")
            .replaceFirstChar { it.uppercase() }

        findViewById<TextView>(R.id.tvProfileName).text = name
        findViewById<TextView>(R.id.tvProfileEmail).text = email
        findViewById<ImageView>(R.id.btnBackProfile).setOnClickListener { finish() }
        findViewById<com.google.android.material.button.MaterialButton>(R.id.btnLogout)
            .setOnClickListener {
                val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
                sharedPref.edit().clear().apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            }
    }
}
