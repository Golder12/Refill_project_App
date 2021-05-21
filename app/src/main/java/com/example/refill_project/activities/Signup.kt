package com.example.refill_project.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.refill_project.R
import com.example.refill_project.UserInfo
import com.example.refill_project.showToast
import kotlinx.android.synthetic.main.activity_signup.*

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        setupHomelink()
        setupLoginlink()


        btnsignup.setOnClickListener {
            if (fname.text.toString().length < 0 && lname.text.toString().length < 0 && email.text.toString().length < 0 && phonenumber.text.toString().length < 0) {

                showToast("All fields are required")

            } else if (phonenumber.text.toString().length < 10) {

                showToast("Phone number should be more 10 digits")

            } else if (password.text.toString().length < 8 && confirmpassword.text.toString().length < 8) {

                showToast("Passowrds must have atleast 8 characters")

            } else if (password.text.toString() == confirmpassword.text.toString()) {

                var url =
                    "http://refillug.rf.gd/android/signup/" + fname.text.toString() + "/" + lname.text.toString() + "/" + email.text.toString() + "/" + phonenumber.text.toString() + "/" + password.text.toString()
                var rq: RequestQueue = Volley.newRequestQueue(this)
                var sr = object : StringRequest(Request.Method.GET, url, { response ->

                    if (response.equals("0")) {
                        showToast("Email Address already used")
                    } else {
                        UserInfo.userid = Integer.parseInt(response)
                        var intent = Intent(this, MainActivity::class.java)
                        var welcome_msg = "Welcome! We are happy to serve you!"
                        intent.putExtra("welcome_msg", welcome_msg)
                        startActivity(intent)
                    }
                }, { error -> showToast(error.message!!) }) {
                    @Throws(AuthFailureError::class)
                    override fun getHeaders(): Map<String, String> {
                        val headers = HashMap<String, String>()
                        headers.put(
                            "Cookie",
                            "__test=53e7f0ab4c8aeefdcec6b8b9e8d43286; expires=Friday, January 1, 2038 at 2:55:55 AM; path=/"
                        );

                        return headers
                    }
                }
                rq.add(sr)

            } else {

                showToast("Passwords don't match")

            }
        }
    }

    private fun setupHomelink() {
        val linkTextView = findViewById<TextView>(R.id.homeAccount)
        linkTextView.setOnClickListener {
            val switchActivityIntent = Intent(this, MainActivity::class.java)
            startActivity(switchActivityIntent)
        }
    }

    private fun setupLoginlink() {
        val linkTextView = findViewById<TextView>(R.id.backtologin)
        linkTextView.setOnClickListener {
            val switchActivityIntent = Intent(this, Login::class.java)
            startActivity(switchActivityIntent)
        }
    }
}