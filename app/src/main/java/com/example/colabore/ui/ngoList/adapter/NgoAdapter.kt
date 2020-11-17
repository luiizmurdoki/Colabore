package com.example.colabore.ui.ngoList.adapter
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.BaseExpandableListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.colabore.R
import com.example.colabore.model.CardModel
import com.example.colabore.model.PersistUserInformation
import com.example.colabore.ui.base.SimpleBaseRecyclerViewAdapter
import com.example.colabore.ui.description.DescriptionActivity
import kotlinx.android.synthetic.main.item_ngo_list.view.*


var context = Activity()

class NgoAdapter(context: Context) :  SimpleBaseRecyclerViewAdapter(context) {

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
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_ngo_list, parent, false)
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
                Glide.with(this).load(item.imageUrl).apply(
                    RequestOptions().error(R.drawable.ic_default_empty).placeholder(
                        R.drawable.ic_default_empty
                    )
                ).into(ngoImageIvList)
                titleNgo.text = item.nome

                cardNgo.setOnClickListener {
                    val title = item.nome
                    val desc = item.info
                    val url =item.imageUrl
                    val phone = item.telefone
                    val address = item.endereco
                    val lati = item.latitude.toString()
                    val longi = item.longitude.toString()
                    PersistUserInformation.idOng(item.idOng)

                    val intent = Intent(context, DescriptionActivity::class.java)
                    intent.putExtra("nome", title)
                    intent.putExtra("info", desc)
                    intent.putExtra("url",url)
                    intent.putExtra("phone",phone)
                    intent.putExtra("address",address)
                    intent.putExtra("lati",lati)
                    intent.putExtra("longi",longi)
                    context.startActivity(intent)
                }

            }

        }
    }
}
