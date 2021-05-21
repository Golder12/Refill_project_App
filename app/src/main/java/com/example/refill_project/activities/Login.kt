package com.example.refill_project.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.refill_project.R
import com.example.refill_project.UserInfo
import com.example.refill_project.showToast
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.login.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        signup.setOnClickListener{
            var intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        signin.setOnClickListener{
            if(signinemail.text.toString().length<0 && signinpassword.text.toString().length<0){

                showToast("All fields are required")

            }else{

                var url = "http://refillug.rf.gd/android/signin/"+ signinemail.text.toString() +"/"+ signinpassword.text.toString()
                var rq: RequestQueue = Volley.newRequestQueue(this)
                var sr = object: StringRequest(Request.Method.GET, url, { response ->

                if (response.equals("0")) {
                    showToast("Your credentials don't match our records")
                } else {
                    UserInfo.userid = Integer.parseInt(response)
                    var intent = Intent(this, MainActivity::class.java)
                    var welcome_msg = "Welcome Back! We are happy to serve you!"
                    intent.putExtra("welcome_msg", welcome_msg)
                    startActivity(intent)
                }

            }, { error-> showToast(error.message!!) })
            {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Cookie", "__test=2b653bde7094ac2751ce8aaf5d0aaa0b; expires=Friday, January 1, 2038 at 2:55:55 AM; path=/");

                    return headers
                }
            }
            rq.add(sr)

            }
        }
    }
}