package com.example.toogoodtogo

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class PackDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pack_details)

        intent = intent

        val packName = intent.extras?.get(PackAdapter.EXTRA_PACK_NAME)
        val packPrice = intent.extras?.get(PackAdapter.EXTRA_PRICE)
        val packTime = intent.extras?.get(PackAdapter.EXTRA_TIME)
        val packDesc = intent.extras?.get(PackAdapter.EXTRA_DESC)
        val packId: String = intent.extras?.get(PackAdapter.EXTRA_PACK_ID).toString()
        val userId = intent.extras?.get(PackAdapter.EXTRA_USERS_ID)
        val isReserved: Boolean = intent.getBooleanExtra(PackAdapter.EXTRA_IS_RESERVED, false)

        val packNameView = findViewById<TextView>(R.id.packDetailsName)
        val packPriceView = findViewById<TextView>(R.id.packDetailsPrice)
        val packTimeView = findViewById<TextView>(R.id.packDetailsTime)
        val packDescView = findViewById<TextView>(R.id.packDetailsDesc)

        packNameView.text = packName as CharSequence?
        packPriceView.text = packPrice as CharSequence?
        packTimeView.text = packTime as CharSequence?
        packDescView.text = packDesc as CharSequence?

        val reserveBtn = findViewById<Button>(R.id.packDetailsReserveBtn)

        if(isReserved || userId == null){
            reserveBtn.visibility = View.GONE
        }

        reserveBtn.setOnClickListener {
            reservePack(packId, userId.toString())
            finish()
        }


    }

    private fun reservePack(packId: String, userId: String){
        val url = URL("http://icm.dkkapusta1997.usermd.net/take_package/$userId/$packId")
        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.doOutput = true
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded")
            conn.useCaches = false

            BufferedReader(InputStreamReader(conn.inputStream)).use { br ->
                var line: String?
                while (br.readLine().also { line = it } != null) {
                    println(line)
                }
            }
        }
    }
}