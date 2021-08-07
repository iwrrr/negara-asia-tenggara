package com.dicoding.picodiploma.submission

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "name"
        const val EXTRA_HISTORY = "history"
        const val EXTRA_DATE = "date"
        const val EXTRA_CAPITAL = "capital"
        const val EXTRA_FLAG = "flag"
        const val EXTRA_EMBLEM = "emblem"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        title = "Detail Negara"

        bindItems()
    }

    private fun bindItems() {
        val countryName: TextView = findViewById(R.id.tv_country)
        val countryDate: TextView = findViewById(R.id.tv_date)
        val countryHistory: TextView = findViewById(R.id.tv_history)
        val countryCapital: TextView = findViewById(R.id.tv_capital_city)
        val countryFlag: ImageView = findViewById(R.id.iv_flag)
        val countryEmblem: ImageView = findViewById(R.id.iv_emblem)

        val name = intent.getStringExtra(EXTRA_NAME)
        val date = intent.getStringExtra(EXTRA_DATE)
        val history = intent.getStringExtra(EXTRA_HISTORY)
        val capital = intent.getStringExtra(EXTRA_CAPITAL)
        val flag = intent.getIntExtra(EXTRA_FLAG, 0)
        val emblem = intent.getIntExtra(EXTRA_EMBLEM, 0)

        countryName.text = name
        countryDate.text = date
        countryHistory.text = history
        countryCapital.text = capital

        Glide.with(this)
            .load(flag)
            .apply(RequestOptions())
            .into(countryFlag)

        Glide.with(this)
            .load(emblem)
            .apply(RequestOptions())
            .into(countryEmblem)

//        countryFlag.setImageResource(flag)
//        countryEmblem.setImageResource(emblem)
    }
}