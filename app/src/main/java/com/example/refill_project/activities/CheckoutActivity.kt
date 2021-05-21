package com.example.refill_project.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.refill_project.R
import com.example.refill_project.UserInfo
import com.example.refill_project.showToast
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.login.*

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        //showToast("You are here!")
        var url = "http://refillug.rf.gd/android/totalprice/"+ UserInfo.userid.toString()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var sr = object: StringRequest(Request.Method.GET, url, { response ->

            tvfname.text = UserInfo.fname
            tvlname.text = UserInfo.lname
            tvemail.text = UserInfo.email
            tvphonenumber.text = UserInfo.phonenumber
            tvaddress.text = UserInfo.address
            tvtotalprice.text = response

        }, { error-> showToast(error.message!!) })
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Cookie", "__test=53e7f0ab4c8aeefdcec6b8b9e8d43286; expires=Friday, January 1, 2038 at 2:55:55 AM; path=/");

                return headers
            }
        }
        rq.add(sr)
    }
}