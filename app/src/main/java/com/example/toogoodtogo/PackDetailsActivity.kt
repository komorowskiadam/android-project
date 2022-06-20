package com.example.toogoodtogo

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PackDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pack_details)

        intent = intent

        val packName = intent.extras?.get(PackAdapter.EXTRA_PACK_NAME)
        val packPrice = intent.extras?.get(PackAdapter.EXTRA_PRICE)
        val packTime = intent.extras?.get(PackAdapter.EXTRA_TIME)
        val packDesc = intent.extras?.get(PackAdapter.EXTRA_DESC)

        val packNameView = findViewById<TextView>(R.id.packDetailsName)
        val packPriceView = findViewById<TextView>(R.id.packDetailsPrice)
        val packTimeView = findViewById<TextView>(R.id.packDetailsTime)
        val packDescView = findViewById<TextView>(R.id.packDetailsDesc)

        packNameView.text = packName as CharSequence?
        packPriceView.text = packPrice as CharSequence?
        packTimeView.text = packTime as CharSequence?
        packDescView.text = packDesc as CharSequence?

        val reserveBtn = findViewById<Button>(R.id.packDetailsReserveBtn)
    }
}