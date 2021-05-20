package com.example.refill_project.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.refill_project.R
import com.example.refill_project.activities.ProductsAct
import com.example.refill_project.models.Weights
import com.example.refill_project.showToast
import kotlinx.android.synthetic.main.weights.view.*

class WeightsAdapter(var context: Context, var list: ArrayList<Weights>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var my_view = LayoutInflater.from(context).inflate(R.layout.weights, parent, false)
        return MyWeights(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyWeights).bind(list[position].weight)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyWeights(view:View):RecyclerView.ViewHolder(view){
        var iv_weightimage = view.findViewById<ImageView>(R.id.weightimage)
        var currentweight: String? = null
        var weightid: Int? = null

        init {
            itemView.weightimage.setOnClickListener{
//                context.showToast("$weightid clicked")
                val intent = Intent(context, ProductsAct::class.java)
                intent.putExtra("weightid", weightid)
                context.startActivity(intent)
            }
        }

        fun bind(n:String){

            if(n == "6kg"){
                this.currentweight = n
                this.weightid = 1
                iv_weightimage.setImageResource(R.drawable.sixkggas)
            }else if(n == "12.5kg"){
                this.weightid = 2
                this.currentweight = n
                iv_weightimage.setImageResource(R.drawable.twelvekggas)
            }

        }
    }
}