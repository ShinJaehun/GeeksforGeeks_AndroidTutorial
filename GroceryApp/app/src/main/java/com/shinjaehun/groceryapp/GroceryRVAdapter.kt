package com.shinjaehun.groceryapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryRVAdapter(
    var list: List<GroceryItem>,
    val groceryItemClickInterface: GroceryItemClickInterface
) : RecyclerView.Adapter<GroceryRVAdapter.GroceryViewHolder>() {

    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV = itemView.findViewById<TextView>(R.id.tvItemName)
        val quantityeTV = itemView.findViewById<TextView>(R.id.tvItemQuantity)
        val rateTV = itemView.findViewById<TextView>(R.id.tvRate)
        val amountTV = itemView.findViewById<TextView>(R.id.tvTotalAmt)
        val deleteIV = itemView.findViewById<ImageView>(R.id.ivDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_item, parent, false)
        return GroceryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.nameTV.text = list[position].itemName
        holder.quantityeTV.text = list[position].itemQuantity.toString()
        holder.rateTV.text = "₩ " + list[position].itemPrice.toString()
        val itemTotal : Int = list[position].itemPrice * list[position].itemQuantity
        holder.amountTV.text = "₩ " + itemTotal.toString()
        holder.deleteIV.setOnClickListener {
            groceryItemClickInterface.onItemClick(list[position])
        }
    }

    interface GroceryItemClickInterface {
        fun onItemClick(groceryItem: GroceryItem)
    }
}