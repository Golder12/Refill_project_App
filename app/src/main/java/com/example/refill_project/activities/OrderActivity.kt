package com.example.refill_project.activities

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
import kotlinx.android.synthetic.main.activity_checkout.tvfname
import kotlinx.android.synthetic.main.activity_order.*

class OrderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val bundle: Bundle? = intent.extras
        val msg = bundle!!.getString("msg")
        showToast(msg!!)

        var url = "http://refillug.rf.gd/android/order/totalprice/"+ UserInfo.userid.toString()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var sr = object: StringRequest(Request.Method.GET, url, { response ->

            tv2fname.text = UserInfo.fname
            tv2lname.text = UserInfo.lname
            tv2email.text = UserInfo.email
            tv2phonenumber.text = UserInfo.phonenumber
            tv2address.text = UserInfo.address
            tv2totalprice.text = response

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