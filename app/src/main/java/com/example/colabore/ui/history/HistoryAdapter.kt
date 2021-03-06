package com.example.colabore.ui.history

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.model.HistoryModel
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.ui.base.SimpleBaseRecyclerViewAdapter
import com.example.colabore.ui.description.DescriptionActivity
import com.example.colabore.utils.Constants.COMMON_DATE_FORMAT
import com.example.colabore.utils.extension.fromHtml
import com.example.colabore.utils.extension.getDay
import kotlinx.android.synthetic.main.item_history.view.*
import kotlinx.android.synthetic.main.item_ngo_list.view.*

class HistoryAdapter(context: Context) :  SimpleBaseRecyclerViewAdapter(context) {

    var list = listOf<HistoryModel>()
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
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun getEmptyViewHolder(parent: ViewGroup?): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.empty_layout, parent, false)
        return ItemViewHolder(itemView)
    }

    inner class ItemViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(
        itemView
    ) {
        fun bind(item: HistoryModel) {
            itemView.apply {
                Ngo.text = context.getString(R.string.history_title , item.nomeOng).fromHtml()
                Value.text = context.getString(R.string.history_value , item.valor.toString() , item.dataHora.format(COMMON_DATE_FORMAT)).fromHtml()

            }

        }
    }
}
