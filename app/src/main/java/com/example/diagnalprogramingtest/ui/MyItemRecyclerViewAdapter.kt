package com.example.diagnalprogramingtest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.diagnalprogramingtest.R
import com.example.diagnalprogramingtest.data.dto.Content
import javax.inject.Inject


class MyItemRecyclerViewAdapter @Inject constructor() :
    PagingDataAdapter<Content, MyItemRecyclerViewAdapter.ViewHolder>(PassengerListDiffCallback()) {

    lateinit var context: Context
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.idView.text = item?.name
        var uri = "@drawable/${item?.posterImage}"
        var index = uri.indexOf(".")


        var drawable = context.resources.getIdentifier(
            uri.substring(0, index),
            "drawable",
            context.packageName
        )

        //holder.image.setImageResource(drawable)
        Glide
            .with(context)
            .load(drawable)
            .fitCenter()
            .placeholder(R.drawable.placeholder_for_missing_posters)
            .into(holder.image);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater =
            LayoutInflater.from(parent.context).inflate(R.layout.movi_item, parent, false)
        context = parent.context
        return ViewHolder(layoutInflater.rootView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {

        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val item = getItem(position)
            holder.idView.text = item?.name
            var uri = "@drawable/${item?.posterImage}"
            var index = uri.indexOf(".")
            var drawable = context.resources.getIdentifier(
                uri.substring(0, index),
                "drawable",
                context.packageName
            )

            holder.image.setImageResource(drawable)
        }


    }


    inner class ViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {
        val idView: TextView = binding.findViewById(R.id.movi_name)
        val image: ImageView = binding.findViewById(R.id.movi_image)


    }

    class PassengerListDiffCallback : DiffUtil.ItemCallback<Content>() {
        override fun areItemsTheSame(oldItem: Content, newItem: Content) =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: Content,
            newItem: Content
        ) = oldItem == newItem

        override fun getChangePayload(oldItem: Content, newItem: Content): Any? {
            if (oldItem != newItem) {
                return newItem
            }

            return super.getChangePayload(oldItem, newItem)
        }
    }
}