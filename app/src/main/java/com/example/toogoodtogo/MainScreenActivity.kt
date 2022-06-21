package com.example.toogoodtogo

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainScreenActivity : AppCompatActivity(), OnDataPass {

    private var button: Button? = null

    private var packs = mutableListOf<Pack>()

    private var userId = "0"

    private fun getPacks(){
        val url = URL("http://icm.dkkapusta1997.usermd.net/packages")
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
        val itemType = object : TypeToken<List<Pack>>() {}.type
        val itemList = gson.fromJson<List<Pack>>(fullstring, itemType)
        packs = itemList as MutableList<Pack>
    }


    private fun openMapFragment() {
        val fragment = MapsFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.mainLayout, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    override fun onDataPass(data: String) {
        TODO()
        // data tutaj to lat i lng
        // format - "$lat-$lng"
        // jak wysylasz request to /packages_coords/"$lat-$lng"/threshold
        // jesli zero
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val

        button = findViewById<View>(R.id.set_address_btn) as Button

        button!!.setOnClickListener {
            button!!.visibility = View.GONE
            openMapFragment()

        }

        intent = intent
        userId = intent.getStringExtra(UserAdapter.EXTRA_USER_ID).toString()

        getPacks()

        val adapter = PackAdapter(packs)
        val recyclerView = findViewById<RecyclerView>(R.id.pack_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }


}
