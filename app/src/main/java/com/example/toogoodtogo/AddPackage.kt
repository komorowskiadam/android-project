package com.example.toogoodtogo

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.toogoodtogo.jsonClasses.Package
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class AddPackage : AppCompatActivity(), OnDataPass {

    lateinit var pack: Package

    private var supplierID: String? = ""

    private var name: String? = ""
    private var price: String? = ""
    private var amount: String? = ""
    private var desc: String? = ""

    private var time_start: String? = ""
    private var time_end: String? = ""
    private var date: String? = ""

    private var nameEditText: EditText? = null
    private var priceEditText: EditText? = null
    private var amountEditText: EditText? = null
    private var descEditText: EditText? = null

    private var button: Button? = null

    override fun onDataPass(data: String) {
        println("here$data")
        if(data.take(2) == "ts") {
            time_start = data.substring(2)
            allowButton()
        } else if(data.take(2) == "te") {
            time_end = data.substring(2)
            allowButton()
        }
        else if (data.take(1) == "d") {
            date = data.substring(1)
            allowButton()
        }
    }

    fun allowButton() {
        println("name$name")
        println("desc$desc")
        println("price$price")
        println("amount$amount")
        println("ts$time_start")
        println("te$time_end")
        println("d$date")

        if((name!!.isNotEmpty()) && (price!!.isNotEmpty()) && (desc!!.isNotEmpty()) &&
            (time_start!!.isNotEmpty()) && (time_end!!.isNotEmpty()) && (date!!.isNotEmpty())
            && (amount!!.isNotEmpty())) {
            button?.visibility = View.VISIBLE
        }
    }

    private fun addListeners() {
        nameEditText = findViewById(R.id.name)
        with(nameEditText) {
            this?.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    name = s.toString()
                    allowButton()
                }
            })
        }
        amountEditText = findViewById(R.id.amount)
        with(amountEditText) {
            this?.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    amount = s.toString()
                    allowButton()
                }
            })
        }
        priceEditText = findViewById(R.id.price)
        with(priceEditText) {
            this?.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    price = s.toString()
                    allowButton()
                }
            })
        }
        descEditText = findViewById(R.id.desc)
        with(descEditText) {
            this?.addTextChangedListener(object: TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    desc = s.toString()
                    allowButton()
                }
            })
        }
    }

    private fun sendRequest() {
        val dateTimeStart = String.format("%s_%s", date, time_start)
        val dateTimeEnd = String.format("%s_%s", date, time_end)


        val url = URL("http://icm.dkkapusta1997.usermd.net/add_package/$supplierID" +
                "?name=$name&price=$price&desc=$desc&date_start=$dateTimeStart&date_end=$dateTimeEnd&amount=$amount")

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_package)

        button = findViewById<View>(R.id.add) as Button
        button?.setOnClickListener {
            sendRequest()
        }
        button?.visibility = View.INVISIBLE

        addListeners()

        supplierID = intent.getStringExtra("id")
    }

    fun showTimePickerDialog(v: View) {
        var myStr = v.tag as String
        val bundle = Bundle()
        val tpf = TimePickerFragment()

        bundle.putString("params", myStr)

        tpf.arguments = bundle
        tpf.show(supportFragmentManager, "timePicker")
    }

    fun showDatePickerDialog(v: View) {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }
}