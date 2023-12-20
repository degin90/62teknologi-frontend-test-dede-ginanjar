package com.degin90.test62.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.degin90.test62.R
import com.degin90.test62.model.Business


class searchItemListAdapter(
    private val items: List<Business>,
    private val cardClickListener : OnItemClickListener
) :
    RecyclerView.Adapter<searchItemListAdapter.ViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(action: String, item: Business?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.slice_card_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(item: Business) {
            val text1    = itemView.findViewById<TextView>(R.id.cardName)
            val text2     = itemView.findViewById<TextView>(R.id.cardPhone)
            val text3   = itemView.findViewById<TextView>(R.id.cardAddrA)
            val image   = itemView.findViewById<ImageView>(R.id.cardImage)
            text1.text = item.name
            text2.text = item.displayPhone
            text3.text = item.location?.displayAddress?.joinToString()

            Glide.with(itemView)
                .load(item.imageUrl)
                .centerCrop()
                .into(image)


            itemView.setOnClickListener { cardClickListener.onItemClick("hapus",item) }
        }
    }
}
