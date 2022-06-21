package com.example.toogoodtogo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user_btn = findViewById<Button>(R.id.user_btn)
        val seller_btn = findViewById<Button>(R.id.seller_btn)

        user_btn.setOnClickListener {
            val intent = Intent(this, ChooseUserActivity::class.java)
            startActivity(intent)
        }

        seller_btn.setOnClickListener {
            val intent = Intent(this, ChooseSeller::class.java)
            startActivity(intent)
        }
    }
}