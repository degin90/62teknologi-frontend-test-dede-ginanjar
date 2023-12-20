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
import com.degin90.test62.model.Review


class reviewItemListAdapter(
    private val items: List<Review>,
    private val cardClickListener : OnItemClickListener
) :
    RecyclerView.Adapter<reviewItemListAdapter.ViewHolder>() {


    interface OnItemClickListener {
        fun onItemClick(action: String, item: Review?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.slice_card_review, parent, false)
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
        fun bind(item: Review) {
            val text1   = itemView.findViewById<TextView>(R.id.reviewUser)
            val text2   = itemView.findViewById<TextView>(R.id.reviewDate)
            val text3   = itemView.findViewById<TextView>(R.id.reviewtext)
            val image   = itemView.findViewById<ImageView>(R.id.profilePic)
            text1.text = item.user?.name
            text2.text = item.timeCreated
            text3.text = item.text

            Glide.with(itemView)
                .load(item.user?.imageUrl)
                .centerCrop()
                .circleCrop()
                .placeholder(R.drawable.review_user)
                .into(image)

            itemView.setOnClickListener { cardClickListener.onItemClick("todo",item) }
        }
    }
}

