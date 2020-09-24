package com.example.colabore.ui.main.adapter

import android.R.attr
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory.decodeStream
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.model.CardModel
import com.example.colabore.ui.base.SimpleBaseRecyclerViewAdapter
import com.example.colabore.ui.description.DescriptionActivity
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_card.view.*
import kotlinx.android.synthetic.main.placeholder_default_general.view.*
import org.jetbrains.anko.image
import org.jetbrains.anko.imageURI
import org.jetbrains.anko.sdk25.coroutines.onClick
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

    inner class ItemViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        fun bind(item: CardModel) {
            itemView.apply {
                Glide.with(this).load(item.imageUrl).apply(RequestOptions().error(R.drawable.ic_default_empty).placeholder(R.drawable.ic_default_empty)).into(homeImageIv)
                ongTextTv.text = item.nome

                cardCv.setOnClickListener {
                    val title = item.nome
                    val desc = item.info
                    val url =item.imageUrl

                    val intent = Intent(context, DescriptionActivity::class.java)
                    context.startActivity(intent)
                }

            }

        }
    }
}
