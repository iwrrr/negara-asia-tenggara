package com.dicoding.picodiploma.submission

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RecyclerViewAdapter(private val listCountry: ArrayList<Country>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Country)
    }

    inner class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var countryName: TextView = itemView.findViewById(R.id.tv_country)
        var countryImage: ImageView = itemView.findViewById(R.id.iv_flag)
        var countryHistory: TextView = itemView.findViewById(R.id.tv_history)
        var countryCapital: TextView = itemView.findViewById(R.id.tv_capital_city)
        var countryDate: TextView = itemView.findViewById(R.id.tv_date)
        var countryEmblem: ImageView = itemView.findViewById(R.id.iv_emblem)
        var btnFavorite: Button = itemView.findViewById(R.id.btn_favorite)
        var btnShare: Button = itemView.findViewById(R.id.btn_share)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val country = listCountry[position]

        holder.countryName.text = country.name
        holder.countryHistory.text = country.history
        holder.countryDate.text = country.date
        holder.countryCapital.text = country.capital

        Glide.with(holder.itemView.context)
            .load(country.image)
            .apply(RequestOptions())
            .into(holder.countryImage)

        Glide.with(holder.itemView.context)
            .load(country.emblem)
            .apply(RequestOptions())
            .into(holder.countryEmblem)

        holder.itemView.setOnClickListener { v: View ->
            onItemClickCallback.onItemClicked(listCountry[holder.absoluteAdapterPosition])
        }

        holder.btnFavorite.setOnClickListener {
            Toast.makeText(holder.btnFavorite.context, listCountry[position].name + " ditambahkan sebagai favorit", Toast.LENGTH_SHORT).show()
        }

        holder.btnShare.setOnClickListener {
            Toast.makeText(holder.btnShare.context, "Bagikan " + listCountry[position].name, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return listCountry.size
    }
}