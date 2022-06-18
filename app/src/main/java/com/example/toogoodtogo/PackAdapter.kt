package com.example.toogoodtogo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PackAdapter(val packs: List<Pack>): RecyclerView.Adapter<PackAdapter.PackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pack_item_view, parent, false)
        return PackViewHolder(view)
    }

    override fun onBindViewHolder(holder: PackViewHolder, position: Int) {
        holder.packName.text = packs[position].name
        holder.packPrice.text = packs[position].price.toString()
    }

    override fun getItemCount(): Int {
        return packs.size
    }


    inner class PackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var packName = itemView.findViewById<TextView>(R.id.packName)
        var packPrice = itemView.findViewById<TextView>(R.id.packPrice)
    }

}