package com.example.refill_project

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.refill_project.activities.CartAct
import com.example.refill_project.activities.CheckoutActivity
import com.example.refill_project.activities.Login

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddressFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddressFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.fragment_address, container, false)

        var et = v.findViewById<EditText>(R.id.et_address)
        var btn = v.findViewById<Button>(R.id.btn_address)

        btn.setOnClickListener {
            UserInfo.address = et.text.toString()
            if(UserInfo.address.length>4){
                var url = "http://refillug.rf.gd/android/addaddress/" + UserInfo.address + "/" + UserInfo.userid.toString()
                var rq: RequestQueue = Volley.newRequestQueue(activity)
                var sr = object : StringRequest(Request.Method.GET, url, { response ->
                    var i = Intent(activity, CheckoutActivity::class.java)
                    startActivity(i)
                }, { error ->
                        Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show()
                    })
                {
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
            }else{
                Toast.makeText(activity, "Please Enter an Address", Toast.LENGTH_LONG).show()
            }
        }

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddressFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddressFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}