package com.example.toogoodtogo

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.toogoodtogo.jsonClasses.Supplier
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class ChooseSeller : AppCompatActivity() {
    private var b0: Button? = null
    private var b1: Button? = null
    private var b2: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seller)

        val b = Bundle()

        b0 = findViewById<View>(R.id.button0) as Button
        b1 = findViewById<View>(R.id.button1) as Button
        b2 = findViewById<View>(R.id.button2) as Button

        b0!!.setOnClickListener {
            val intent = Intent(this, AddPackage::class.java)
            b.putString("id", "0")
            intent.putExtras(b)
            startActivity(intent)
        }

        b1!!.setOnClickListener {
            val intent = Intent(this, AddPackage::class.java)
            b.putString("id", "1")
            intent.putExtras(b)
            startActivity(intent)
        }

        b2!!.setOnClickListener {
            val intent = Intent(this, AddPackage::class.java)
            b.putString("id", "2")
            intent.putExtras(b)
            startActivity(intent)
        }
    }


    fun getRequest() {
        var fullstring = ""
        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val url = URL("http://icm.dkkapusta1997.usermd.net/suppliers")

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
        val itemType = object : TypeToken<List<Supplier>>() {}.type
        val itemList = gson.fromJson<List<Supplier>>(fullstring, itemType)
        println(itemList[0].id)
    }


}