package com.example.refill_project.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.refill_project.R
import com.example.refill_project.UserInfo
import com.example.refill_project.showToast
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        setupHomelink()
        setupOrderlink()

        //showToast("You are here!")
        var url = "http://refillug.rf.gd/android/totalprice/"+ UserInfo.userid.toString()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var sr = object: StringRequest(Request.Method.GET, url, { response ->

            tvfname.text = UserInfo.fname
            tvlname.text = UserInfo.lname
            tvphonenumber.text = UserInfo.phonenumber
            tvaddress.text = UserInfo.address
            tvaddress1.text = UserInfo.address
            tvtotalprice.text = response

        }, { error-> showToast(error.message!!) })
        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                //makair cookie
                //headers.put("Cookie", "__test=53e7f0ab4c8aeefdcec6b8b9e8d43286; expires=Friday, January 1, 2038 at 2:55:55 AM; path=/");
                //africell cookie
                headers.put("Cookie", "__test=caa2730227df078b483bf5b63dc8be00; expires=Friday, January 1, 2038 at 2:55:55 AM; path=/");

                return headers
            }
        }
        rq.add(sr)

        cancel.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        putorder.setOnClickListener {
            var url = "http://refillug.rf.gd/android/placeorder/" + UserInfo.userid.toString()
            var rq: RequestQueue = Volley.newRequestQueue(this)
            var sr = object : StringRequest(Request.Method.GET, url, { response ->

                if (response.equals("0")) {
                    showToast("You cant make an order right now")
                } else {
                    UserInfo.userid = Integer.parseInt(response)
                    var intent = Intent(this, OrderActivity::class.java)
                    var msg = "Success! We shall deliver your order soon."
                    intent.putExtra("msg", msg)
                    startActivity(intent)
                }

            }, { error -> showToast(error.message!!) }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    //makair cookie
                    //headers.put("Cookie", "__test=53e7f0ab4c8aeefdcec6b8b9e8d43286; expires=Friday, January 1, 2038 at 2:55:55 AM; path=/");
                    //africell cookie
                    headers.put("Cookie", "__test=caa2730227df078b483bf5b63dc8be00; expires=Friday, January 1, 2038 at 2:55:55 AM; path=/");

                    return headers
                }
            }
            rq.add(sr)
        }
    }

    private fun setupHomelink() {
        val homeicon = findViewById<ImageView>(R.id.homeIcon)
        val textforhomeicon = findViewById<TextView>(R.id.hometext)

        homeicon.setOnClickListener {
            val switchActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(switchActivityIntent)
        }

        textforhomeicon.setOnClickListener {
            val switchActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(switchActivityIntent)
        }

    }

    private fun setupOrderlink() {
        val icon = findViewById<ImageView>(R.id.shoppingBag)
        val textforicon = findViewById<TextView>(R.id.ordertext)

        icon.setOnClickListener {
            val switchActivityIntent = Intent(this, OrderActivity::class.java)
            startActivity(switchActivityIntent)
        }

        textforicon.setOnClickListener {
            val switchActivityIntent = Intent(this, OrderActivity::class.java)
            startActivity(switchActivityIntent)
        }

    }
}