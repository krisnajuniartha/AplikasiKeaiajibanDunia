package com.example.aplikasikeaiajibandunia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvKeajaiban: RecyclerView
    private val list = ArrayList<Keajaiban>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvKeajaiban = findViewById(R.id.rv_keajaiban)
        rvKeajaiban.setHasFixedSize(true)

        list.addAll(getListKeajaiban())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_about, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val intentAboutMe = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intentAboutMe)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListKeajaiban(): ArrayList<Keajaiban> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_deskripsi)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listKeajaiban = ArrayList<Keajaiban>()
        for (i in dataName.indices) {
            val keajaiban = Keajaiban(dataName[i], dataDescription[i], dataPhoto[i])
            listKeajaiban.add(keajaiban)
        }
        return listKeajaiban
    }

    private fun showRecyclerList() {
        rvKeajaiban.layoutManager = LinearLayoutManager(this)
        val listTempatAdapter = ListTempatAdapter(list)
        rvKeajaiban.adapter = listTempatAdapter

        listTempatAdapter.setOnItemClickCallback(object : ListTempatAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Keajaiban) {
                showSelectedKeajaiban(data)
            }
        })
    }

    private fun showSelectedKeajaiban(keajaiban: Keajaiban) {
        Toast.makeText(this, "Anda memilih " + keajaiban.name, Toast.LENGTH_SHORT).show()
        val intentChange = Intent(this@MainActivity, KeajaibanDetail::class.java)
        intentChange.putExtra(KeajaibanDetail.EXTRA_KEAJAIBAN, keajaiban)
        startActivity(intentChange)
    }
}