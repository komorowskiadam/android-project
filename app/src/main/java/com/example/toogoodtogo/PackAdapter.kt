package com.example.toogoodtogo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.toogoodtogo.jsonClasses.User

class PackAdapter(private val packs: List<Pack>): RecyclerView.Adapter<PackAdapter.PackViewHolder>() {

    companion object {
        const val EXTRA_PACK_NAME = "pt.ua.tooGoodToGo.PACK_NAME"
        const val EXTRA_PRICE = "pt.ua.tooGoodToGo.PRICE"
        const val EXTRA_TIME = "pt.ua.tooGoodToGo.TIME"
        const val EXTRA_DESC = "pt.ua.tooGoodToGo.DESC"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.pack_item_view, parent, false)
        return PackViewHolder(view)
    }

    override fun onBindViewHolder(holder: PackViewHolder, position: Int) {
        holder.packName.text = packs[position].name
        holder.packPrice.text = packs[position].price.toString()
        holder.packDetailsBtn.setOnClickListener {
            val intent = Intent(holder.context, PackDetailsActivity::class.java)
            intent.putExtra(EXTRA_PACK_NAME, packs[position].name)
            intent.putExtra(EXTRA_PRICE, "" + packs[position].price + "$")
            val time = "" + packs[position].date_start + " : " + packs[position].date_end
            intent.putExtra(EXTRA_TIME, time)
            intent.putExtra(EXTRA_DESC, packs[position].desc)
            holder.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return packs.size
    }


    inner class PackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var packName: TextView = itemView.findViewById<TextView>(R.id.packName)
        var packPrice: TextView = itemView.findViewById<TextView>(R.id.packPrice)
        val packDetailsBtn = itemView.findViewById<Button>(R.id.pack_details_btn)
        val context: Context = itemView.context
    }

}