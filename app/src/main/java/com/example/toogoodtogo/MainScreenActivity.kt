package com.example.toogoodtogo

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainScreenActivity : AppCompatActivity() {

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

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
