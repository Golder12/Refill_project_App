package com.example.refill_project

import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.refill_project.activities.CartAct
import com.example.refill_project.activities.Login
import kotlinx.android.synthetic.main.fragment_qty.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QtyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QtyFragment : DialogFragment() {
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
        var v = inflater.inflate(R.layout.fragment_qty, container, false)
        var describe = v.findViewById<TextView>(R.id.descriptionView)

        var et = v.findViewById<EditText>(R.id.et_qty)
        var btn = v.findViewById<Button>(R.id.btn_qty)
        describe.text = UserInfo.itemdescription

        btn.setOnClickListener {
            var userid :Int = UserInfo.userid
            UserInfo.qty = et.text.toString()
            if(userid != 0){
                if(et.text.toString().equals("0")){
                    Toast.makeText(activity, "Choose a higher quantity", Toast.LENGTH_LONG).show()
                }else{
                    var url = "http://refillug.rf.gd/android/total/addtocart/"+ UserInfo.itemid.toString() +"/"+ userid.toString() +"/"+ et.text.toString() +"/Total"
                    var rq:RequestQueue = Volley.newRequestQueue(activity)
                    var sr = object:StringRequest( Request.Method.GET, url, { response->
                        var i = Intent(activity, CartAct::class.java)
                        startActivity(i)
                    },
                        { error -> Toast.makeText(activity, error.message, Toast.LENGTH_LONG).show() })
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
            }else{

                var i = Intent(activity, Login::class.java)
                var previous = "cart"
                i.putExtra("previous", previous)
                startActivity(i)
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
         * @return A new instance of fragment QtyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            QtyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}