package com.example.colabore.ui.main.adapter

import android.R.attr
import android.app.Activity
import android.content.Context
import android.graphics.BitmapFactory.decodeStream
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.example.colabore.R
import com.example.colabore.model.CardModel
import com.example.colabore.ui.base.SimpleBaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_card.view.*
import org.jetbrains.anko.image
import org.jetbrains.anko.imageURI
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


var context = Activity()

class MainCardAdapter(context: Context) : SimpleBaseRecyclerViewAdapter(context) {

    var list = listOf<CardModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getDisplayableItemsCount() = list.size

    override fun onBindRecyclerViewHolder(
        holder: androidx.recyclerview.widget.RecyclerView.ViewHolder?,
        position: Int
    ) {
        if (holder is ItemViewHolder && list.isNotEmpty()) {
            holder.bind(list[position])
        }
    }

    override fun getItemViewHolder(parent: ViewGroup?): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getEmptyViewHolder(parent: ViewGroup?): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.empty_layout, parent, false)
        return ItemViewHolder(itemView)
    }

    inner class ItemViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(
        itemView
    ) {
        fun bind(item: CardModel) {
            itemView.apply {
              /*  val url = URL(item.imageUrl)
                val connection: HttpURLConnection =
                    (url.openConnection() as HttpURLConnection).also {
                        it.doInput = true
                        it.connect()
                    }
                val input: InputStream = connection.inputStream
                val bmp = decodeStream(input)*/
                homeImageIv.setImageURI(item.imageUrl.toUri())
            }
        }
    }
}
