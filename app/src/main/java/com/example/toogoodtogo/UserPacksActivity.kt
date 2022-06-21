package com.example.toogoodtogo

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class UserPacksActivity : AppCompatActivity() {

    private var packs = mutableListOf<Pack>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_packs)

        intent = intent

        val userId = intent.getStringExtra(UserAdapter.EXTRA_USER_ID).toString()

        getPacksForUser(userId)

        val adapter = PackAdapter(packs, true, userId)
        val recyclerView = findViewById<RecyclerView>(R.id.user_pack_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    private fun getPacksForUser(id: String){
        val url = URL("http://icm.dkkapusta1997.usermd.net/packages_user/$id")
        var fullstring = ""
        val sdkVersion = Build.VERSION.SDK_INT

        if (sdkVersion > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"

            try {
                BufferedReader(InputStreamReader(conn.inputStream)).use { br ->
                    var line: String?
                    while (br.readLine().also { line = it } != null) {
                        fullstring += line
                    }
                }
            } catch(e: Exception){

            }

        }
        val gson = Gson()
        val itemType = object : TypeToken<List<Pack>>() {}.type
        try {
            val itemList = gson.fromJson<List<Pack>>(fullstring, itemType)
            packs = itemList as MutableList<Pack>
        } catch (e: Exception) {
            println(e)
        }
    }
}