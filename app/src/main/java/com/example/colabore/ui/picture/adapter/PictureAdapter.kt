package com.example.colabore.ui.picture.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.model.PictureProfileModel
import com.example.colabore.ui.base.SimpleBaseRecyclerViewAdapter
import com.example.colabore.utils.AdapterOnClick
import kotlinx.android.synthetic.main.item_picture_grid.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class PictureAdapter(context: Context, private val adapterClick: AdapterOnClick) : SimpleBaseRecyclerViewAdapter(context) {

    var list = listOf<PictureProfileModel>()
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
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_picture_grid, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getEmptyViewHolder(parent: ViewGroup?): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.empty_layout, parent, false)
        return ItemViewHolder(itemView)
    }

    inner class ItemViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(
            itemView
        ) {
        fun bind(item: PictureProfileModel) {
            itemView.apply {
                Glide.with(this).load(item.imageUrl).apply(
                    RequestOptions().error(R.drawable.ic_default_empty).placeholder(
                        R.drawable.ic_default_empty
                    )
                ).into(imageIvGrid)
                cardPicture.setOnClickListener {
                    adapterClick.onClick(item.imageUrl , item.face)
                }
            }
        }
    }
}