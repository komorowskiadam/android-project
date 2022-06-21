package com.example.toogoodtogo

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

interface OnDataPass {
    fun onDataPass(data: String)
}

class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    lateinit var dataPasser: OnDataPass

    var timeStr = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    private fun passData(data: String){
        dataPasser.onDataPass("$timeStr$data")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        if (arguments != null) {
            timeStr = requireArguments().getString("params").toString()
        }

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        passData(String.format("%d-%d", hourOfDay, minute))
    }
}