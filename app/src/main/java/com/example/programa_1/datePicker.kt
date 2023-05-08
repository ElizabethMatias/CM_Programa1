package com.example.programa_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.app.Dialog
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class datePicker (val listener:(year:Int, month:Int, day:Int) -> Unit): DialogFragment(), DatePickerDialog.OnDateSetListener{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val cal = Calendar.getInstance()
        val year =  cal.get(Calendar.YEAR)
        val month =  cal.get(Calendar.MONTH)
        val day =  cal.get(Calendar.DAY_OF_MONTH)
        val picker = DatePickerDialog(requireActivity(), this, year, month, day)
        picker.datePicker.maxDate = cal.timeInMillis
        return picker
    }

    override fun onDateSet(p0: DatePicker?, yy: Int, mm: Int, dd: Int) {
        listener(yy, mm+1, dd)
    }
}