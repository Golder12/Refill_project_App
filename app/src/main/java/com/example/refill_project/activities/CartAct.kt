package com.example.refill_project.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.refill_project.*
import com.example.refill_project.adapters.WeightsAdapter
import com.example.refill_project.models.Weights
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.weightsact.*

class CartAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        setupOrderlink()
        setupHomelink()


        var url = "http://refillug.rf.gd/android/total/getcart/"+UserInfo.userid.toString()
        var list = ArrayList<String>()
        var rq:RequestQueue = Volley.newRequestQueue(this)
        var jar = object : JsonObjectRequest(Request.Method.GET, url, null, { response->
            for(x in 0 until response.length())
                list.add("Item : "+ response.getJSONObject(x.toString()).getString("name") +"\n"+ "Station : "+ response.getJSONObject(x.toString()).getString("station")
                +"\n"+ "Quantity : "+ response.getJSONObject(x.toString()).getString("quantity") +"\n"+ "Price : "+ response.getJSONObject(x.toString()).getString("price")
                +"\n"+ "Total Price : "+ response.getJSONObject(x.toString()).getString("total"))
            var adp = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
            cart_lv.adapter = adp
        }, { error -> showToast(error.message.toString())})

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
        rq.add(jar)

        continue_shopping.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        checkout.setOnClickListener{
            if(UserInfo.address.length<=4 || UserInfo.address == null){
                var obj = AddressFragment()
                var manager = (this).supportFragmentManager
                obj.show(manager, "Address")
            }else{
                var intent = Intent(this, CheckoutActivity::class.java)
                startActivity(intent)
            }
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
}