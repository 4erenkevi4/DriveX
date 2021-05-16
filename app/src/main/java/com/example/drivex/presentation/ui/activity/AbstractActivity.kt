package com.example.drivex.presentation.ui.activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

abstract class AbstractActivity: AppCompatActivity(), ScreenManager {
    private val CAMERA_PERMISSION_CODE = 1
    private val CAMERA_PIC_REQUEST = 2

    @SuppressLint("SimpleDateFormat")
    override fun  initCalendar(textViewDate: TextView) {
        textViewDate.setOnClickListener {
            textViewDate.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
            val cal = Calendar.getInstance()
            val dateSetListener =
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, monthOfYear)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val myFormat = "dd.MM.yyyy"
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    textViewDate.text = sdf.format(cal.time)
                }
            textViewDate.setOnClickListener {
                DatePickerDialog(
                    this, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }
    }

    override fun initPhotoButton(view: View){
        view.setOnClickListener {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_PIC_REQUEST)
    }
    }

    override fun initSaveButton(view: View) {
            view.setOnClickListener {
                putData()
            }
        }
}

