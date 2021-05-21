package com.example.refill_project.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.example.refill_project.R
import com.example.refill_project.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //receiving intent
//        val bundle: Bundle? = intent.extras
//        val welcome_msg = bundle!!.getString("welcome_msg")
//        if(welcome_msg != null)
//            showToast(welcome_msg)

        //total button
        totalimage.setOnClickListener {
            val station:String = "Total"
            val intent = Intent(this, WeightsAct::class.java)
            intent.putExtra("station_name", station)
            startActivity(intent)
        }

        //shell button
        shellimage.setOnClickListener {
            val station:String = "Shell"
            val msg:String = "$station is not yet available."
            showToast(msg)
        }

        //stabex button
        stabeximage.setOnClickListener {
            val station:String = "Stabex"
            val msg:String = "$station is not yet available."
            showToast(msg)
        }

        //hass button
        hassimage.setOnClickListener {
            val station:String = "Hass"
            val msg:String = "$station is not yet available."
            showToast(msg)
        }

        //oryx button
        oryximage.setOnClickListener {
            val station:String = "Oryx"
            val msg:String = "$station is not yet available."
            showToast(msg)
        }
    }

}