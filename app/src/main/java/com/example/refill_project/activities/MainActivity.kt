package com.example.refill_project.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.refill_project.R
import com.example.refill_project.UserInfo
import com.example.refill_project.showToast
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //receiving intent
//        val bundle: Bundle? = intent.extras
//        val welcome_msg = bundle!!.getString("welcome_msg")
//        if(welcome_msg != null)
//            showToast(welcome_msg)
//        else
//            showToast("Hi There!")

        //total button
        totalimage.setOnClickListener {
            var userid: Int = UserInfo.userid
            if(userid!=0){
                val url: String = "http://refillug.rf.gd/android/user/"+userid.toString()

                val rq: RequestQueue = Volley.newRequestQueue(this)
                val header : JSONObject = JSONObject()

                var jor = object : JsonObjectRequest(Request.Method.GET, url, null, { response->
                    UserInfo.fname = response.getString("fname")
                    UserInfo.lname = response.getString("lname")
                    UserInfo.email = response.getString("email")
                    UserInfo.address = response.getString("additional_address_info")
                    UserInfo.phonenumber = response.getString("phonenumber")
                }, { error->showToast(error.message!!)})
                {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        headers.put("Cookie", "__test=53e7f0ab4c8aeefdcec6b8b9e8d43286; expires=Friday, January 1, 2038 at 2:55:55 AM; path=/");
                        return headers
                    }
                }
                rq.add(jor)
                val station:String = "Total"
                val intent = Intent(this, WeightsAct::class.java)
                intent.putExtra("station_name", station)
                startActivity(intent)
            }else{
                val station:String = "Total"
                val intent = Intent(this, WeightsAct::class.java)
                intent.putExtra("station_name", station)
                startActivity(intent)
            }
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