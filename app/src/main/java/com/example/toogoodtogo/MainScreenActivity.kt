package com.example.toogoodtogo

import android.content.Intent
import com.example.toogoodtogo.R
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainScreenActivity : AppCompatActivity() {

    private var button: Button? = null

    private val pack1: Pack = Pack(
        id = 1,
        name = "Paczka1",
        price = 1.99,
        desc = "DUPA DUPA DUPA",
        taken = false,
        supplier = "DUPA I HUJ COMPANY",
        date_start = 12930,
        date_end = 123123)

    private val packs = mutableListOf<Pack>()


    private fun openMapFragment() {
        val fragment = MapsFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainLayout, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        button = findViewById<View>(R.id.set_address_btn) as Button

        button!!.setOnClickListener {
            openMapFragment()
        }

        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)
        packs.add(pack1)

        val adapter = PackAdapter(packs)
        val recyclerView = findViewById<RecyclerView>(R.id.pack_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


}
