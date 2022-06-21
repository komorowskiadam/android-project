package com.example.toogoodtogo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.toogoodtogo.jsonClasses.User

class UserAdapter(val users: List<User>): RecyclerView.Adapter<UserAdapter.UserSelectButtonViewHolder>()  {

    companion object {
        const val EXTRA_USER_ID = "pt.ua.tooGoodToGo.USER_ID"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserSelectButtonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.user_select_button_layout, parent, false)
        return UserSelectButtonViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserSelectButtonViewHolder, position: Int) {
        holder.userButton.text = users[position].name

        holder.userButton.setOnClickListener {
            val intent = Intent(holder.context, MainScreenActivity::class.java)
            intent.putExtra(EXTRA_USER_ID, users[position].id)
            holder.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }


    inner class UserSelectButtonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val userButton: Button = itemView.findViewById<Button>(R.id.user_select_btn)
        val context: Context = itemView.context
    }

}