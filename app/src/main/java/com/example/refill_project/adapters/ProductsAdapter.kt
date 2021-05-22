package com.example.refill_project.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        (holder as MyProducts).bind(list[position].id, list[position].name, list[position].description, list[position].price)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyProducts(view: View): RecyclerView.ViewHolder(view){
        var tv_name = view.findViewById<TextView>(R.id.product_name)
        var tv_price = view.findViewById<TextView>(R.id.product_price)
        var iv_image = view.findViewById<ImageView>(R.id.product_image)
        fun bind(i:Int, n:String, d:String, p:Int){
            tv_name.text = n
            tv_price.text = p.toString()

            if(n == "6kg Cylinder"){
                iv_image.setImageResource(R.drawable.gasimage)
            }else if(n == "6kg Refill"){
                iv_image.setImageResource(R.drawable.refillimage)
            }else if(n == "6kg Grill"){
                iv_image.setImageResource(R.drawable.grill)
            }else if(n == "6kg Burner"){
                iv_image.setImageResource(R.drawable.burner)
            }else if(n == "12.5kg Cylinder"){
                iv_image.setImageResource(R.drawable.gasimage)
            }else if(n == "12.5kg Refill"){
                iv_image.setImageResource(R.drawable.refillimage)
            }else if(n == "12.5kg Hosepipe"){
                iv_image.setImageResource(R.drawable.grill)
            }else if(n == "12.5kg Regulator"){
                iv_image.setImageResource(R.drawable.burner)
            }

            itemView.add_cart.setOnClickListener{
                UserInfo.itemid = i
                UserInfo.itemdescription = d
                UserInfo.name = n
                UserInfo.price = p

                var obj = QtyFragment()
                var manager = (itemView.context as Activity).fragmentManager
                obj.show(manager, "Qty")
            }
        }
    }
}