package com.example.toogoodtogo

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toogoodtogo.jsonClasses.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ChooseUserActivity : AppCompatActivity() {

    private var users = mutableListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_user)

        getUsers()

        val adapter = UserAdapter(users)
        val recyclerView = findViewById<RecyclerView>(R.id.user_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun getUsers(){
        val url = URL("http://icm.dkkapusta1997.usermd.net/users")
        var fullstring = ""

        val sdkVersion = Build.VERSION.SDK_INT
        if (sdkVersion > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"

            BufferedReader(InputStreamReader(conn.inputStream)).use { br ->
                var line: String?
                while (br.readLine().also { line = it } != null) {
                    fullstring += line
                }
            }
        }
        val gson = Gson()
        val itemType = object : TypeToken<List<User>>() {}.type
        val itemList = gson.fromJson<List<User>>(fullstring, itemType)
        users = itemList as MutableList<User>
    }
}