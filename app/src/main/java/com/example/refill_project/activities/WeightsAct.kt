package com.example.refill_project.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.refill_project.R
import com.example.refill_project.adapters.WeightsAdapter
import com.example.refill_project.models.Weights
import com.example.refill_project.showToast
import kotlinx.android.synthetic.main.weightsact.*
import org.json.JSONArray
import org.json.JSONObject

class WeightsAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weightsact)

        val url: String = "http://refillug.rf.gd/android/total"
        var list = ArrayList<Weights>()
        var rq: RequestQueue = Volley.newRequestQueue(this)
        var jar = object : JsonObjectRequest(Request.Method.GET, url, null, { response->
            for(x in 0 until response.length())
                list.add(Weights(response.getJSONObject(x.toString()).getString("weight")))
            var adp = WeightsAdapter(this, list)
            weight_rv.layoutManager = LinearLayoutManager(this)
            weight_rv.adapter = adp
            //textView.text = response.getJSONObject("0").getString("weight")
        }, { error -> showToast(error.message.toString())})

        {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
            val headers = HashMap<String, String>()
            headers.put("Cookie", "__test=53e7f0ab4c8aeefdcec6b8b9e8d43286; expires=Friday, January 1, 2038 at 2:55:55 AM; path=/");

                return headers
            }
        }
        rq.add(jar)
    }
}
