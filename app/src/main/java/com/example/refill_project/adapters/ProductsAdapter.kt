package com.example.refill_project.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.refill_project.QtyFragment
import com.example.refill_project.R
import com.example.refill_project.UserInfo
import com.example.refill_project.models.Products
import kotlinx.android.synthetic.main.products.view.*

class ProductsAdapter(var context: Context, var list: ArrayList<Products>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var my_view = LayoutInflater.from(context).inflate(R.layout.products, parent, false)
        return MyProducts(my_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyProducts).bind(list[position].id, list[position].name, list[position].price)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyProducts(view: View): RecyclerView.ViewHolder(view){
        var tv_name = view.findViewById<TextView>(R.id.product_name)
        var tv_price = view.findViewById<TextView>(R.id.product_price)
        fun bind(i:Int, n:String, p:Int){
            tv_name.text = n
            tv_price.text = p.toString()
            itemView.add_cart.setOnClickListener{
                UserInfo.itemid = i
                var obj = QtyFragment()
                var manager = (itemView.context as Activity).fragmentManager
                obj.show(manager, "Qty")
            }
        }
    }
}