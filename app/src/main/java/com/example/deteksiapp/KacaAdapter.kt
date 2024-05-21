package com.example.deteksiapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KacaAdapter(private val listprodukkaca: ArrayList<Produk>) : RecyclerView.Adapter<KacaAdapter.ViewHolder>() {

    var onItemClick: ((Produk) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview: View = LayoutInflater.from(parent.context).inflate(R.layout.items_user, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listprodukkaca [position]
        holder.imgPhoto.setImageResource(currentItem.imageproduk)
        holder.tvName.text= currentItem.titleproduk
     //   holder.tvDescription.text = currentItem.description

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(currentItem)
        }
    }

    override fun getItemCount(): Int = listprodukkaca.size


    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_avatar)
        val tvName: TextView = itemView.findViewById(R.id.produkname)
       // val tvDescription: TextView = itemView.findViewById(R.id.descriproduk)
    }
}