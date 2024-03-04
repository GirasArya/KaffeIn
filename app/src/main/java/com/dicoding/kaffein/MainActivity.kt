package com.dicoding.kaffein

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCoffee: RecyclerView
    private val list = ArrayList<Coffee>()

    private fun getCoffeeList(): ArrayList<Coffee> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataCountry = resources.getStringArray(R.array.data_country)
        val dataIngredients = resources.getStringArray(R.array.data_ingredients)
        val listCoffee = ArrayList<Coffee>()
        for (i in dataName.indices) {
            val coffee = Coffee(dataName[i], dataDescription[i],dataPhoto[i], dataCountry[i], dataIngredients[i])
            listCoffee.add(coffee)
        }
        return listCoffee
    }

    private fun showSelectedHero(coffe: Coffee) {
        val intentDetail = Intent(this@MainActivity, CoffeeDetailActivity::class.java)
        intentDetail.putExtra("EXTRA_COFFEE", coffe)
    }

    private fun showRecyclerList() {
        rvCoffee.layoutManager = LinearLayoutManager(this)
        val listCoffee = ListCoffeeAdapter(list)
        rvCoffee.adapter = listCoffee

        listCoffee.setOnItemClickCallback(object : ListCoffeeAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Coffee) {
                showSelectedHero(data)
            }
        })
    }


    //Action Bar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Welcome to KaffeIn"
        rvCoffee = findViewById(R.id.rv_coffee)
        rvCoffee.setHasFixedSize(true)

        list.addAll(getCoffeeList())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page ->{
                val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

