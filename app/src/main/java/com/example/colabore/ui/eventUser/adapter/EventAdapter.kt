package com.example.colabore.ui.eventUser.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.model.CardModel
import com.example.colabore.model.EventModel
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.ui.base.SimpleBaseRecyclerViewAdapter
import com.example.colabore.ui.description.DescriptionActivity
import com.example.colabore.ui.descriptionEvent.DescrptionEventActivity
import com.example.colabore.utils.extension.fromHtml
import kotlinx.android.synthetic.main.item_event.view.*
import kotlinx.android.synthetic.main.item_ngo_list.view.*
import kotlinx.android.synthetic.main.item_ngo_list.view.titleNgo

class EventAdapter (context: Context) :  SimpleBaseRecyclerViewAdapter(context) {

        var list = listOf<EventModel>()
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
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)
            return ItemViewHolder(itemView)
        }

        override fun getEmptyViewHolder(parent: ViewGroup?): androidx.recyclerview.widget.RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(context).inflate(R.layout.empty_layout, parent, false)
            return ItemViewHolder(itemView)
        }

        inner class ItemViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(
            itemView
        ) {
            fun bind(item: EventModel) {
                itemView.apply {
                    ongTextEventTv.text = context.getString(R.string.event_ngo, item.ong , item.titulo).fromHtml()

                    cardEventCv.setOnClickListener {
                        val ong = item.ong
                        val titulo = item.titulo
                        val mensagem = item.mensagem

                        val intent = Intent(context, DescrptionEventActivity::class.java)
                        intent.putExtra("ong", ong)
                        intent.putExtra("titulo", titulo)
                        intent.putExtra("mensagem", mensagem)
                        context.startActivity(intent)
                    }

                }

            }
        }
}
