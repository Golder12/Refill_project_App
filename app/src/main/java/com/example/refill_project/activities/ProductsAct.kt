package com.example.refill_project.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.refill_project.R
import com.example.refill_project.adapters.ProductsAdapter
import com.example.refill_project.models.Products
import com.example.refill_project.showToast
import kotlinx.android.synthetic.main.productsact.*

class ProductsAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.productsact)


        val bundle: Bundle? = intent.extras
        val weightid = bundle!!.getInt("weightid")

        val url: String = "http://refillug.rf.gd/android/total/products/" + weightid
        var list = ArrayList<Products>()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var jar = object : JsonObjectRequest(Request.Method.GET, url, null, { response->
            for(x in 0 until response.length())
                list.add(Products(response.getJSONObject(x.toString()).getInt("id"), response.getJSONObject(x.toString()).getString("name"), response.getJSONObject(x.toString()).getString("description"), response.getJSONObject(x.toString()).getInt("price")))
            var adp = ProductsAdapter(this, list)
            product_rv.layoutManager = GridLayoutManager(this, 2)
            product_rv.adapter = adp
            //textView.text = response.getJSONObject("0").getString("weight")
        }, { error -> showToast(error.message.toString())})

        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Cookie", "__test=2b653bde7094ac2751ce8aaf5d0aaa0b; expires=Friday, January 1, 2038 at 2:55:55 AM; path=/");
                return headers
            }
        }
        rq.add(jar)



    }
}