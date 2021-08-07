package com.dicoding.picodiploma.submission

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var rvCountries: RecyclerView
    private var list: ArrayList<Country> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Negara Asia Tenggara"

        rvCountries = findViewById(R.id.rv_countries)
        rvCountries.setHasFixedSize(true)

        list.addAll(CountriesData.listData)
        showList()
    }

    private fun showList() {
        rvCountries.layoutManager = LinearLayoutManager(this)
        val rvAdapter = RecyclerViewAdapter(list)
        rvCountries.adapter = rvAdapter

        rvAdapter.setOnItemClickCallback(object : RecyclerViewAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Country) {
                val detailIntent = Intent(this@MainActivity, DetailActivity::class.java)

                detailIntent.putExtra(DetailActivity.EXTRA_NAME, data.name)
                detailIntent.putExtra(DetailActivity.EXTRA_DATE, data.date)
                detailIntent.putExtra(DetailActivity.EXTRA_HISTORY, data.history)
                detailIntent.putExtra(DetailActivity.EXTRA_CAPITAL, data.capital)
                detailIntent.putExtra(DetailActivity.EXTRA_FLAG, data.image)
                detailIntent.putExtra(DetailActivity.EXTRA_EMBLEM, data.emblem)

                startActivity(detailIntent)
                showSelectedCountry(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.about -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
                return
            }
        }
    }

    private fun showSelectedCountry(country: Country) {
        Toast.makeText(this, "Kamu memilih " + country.name, Toast.LENGTH_SHORT).show()

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.rv_countries -> {
                intent = Intent(this@MainActivity, DetailActivity::class.java)
                startActivity(intent)
            }
        }
    }
}