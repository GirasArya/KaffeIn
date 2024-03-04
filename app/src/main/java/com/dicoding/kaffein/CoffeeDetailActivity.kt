package com.dicoding.kaffein

import android.content.Intent
import android.media.Image
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CoffeeDetailActivity : AppCompatActivity() {
    private var coffee:Coffee? = null
    companion object {
        const val EXTRA_COFFEE = "extra_coffee"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coffee_detail)

        val dataCoffee = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_COFFEE, Coffee::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_COFFEE)
        }

        val tvDetail = findViewById<TextView>(R.id.tv_coffee_name_detail)
        val tvDescription = findViewById<TextView>(R.id.tv_coffee_description_detail)
        val tvPhoto = findViewById<ImageView>(R.id.img_coffee_photo_detail)
        val tvCountry = findViewById<TextView>(R.id.tv_coffee_country)
        val tvIngredients = findViewById<TextView>(R.id.tv_coffee_ingredients)


        dataCoffee?.let{ it ->
            Glide.with(this)
                .load(it.photo)
                .into(tvPhoto)
            tvDetail.text = it.name
            tvDescription.text = it.description
            tvCountry.text = it.country
            tvIngredients.text = it.ingredients
        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page ->{
                val moveIntent = Intent(this@CoffeeDetailActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}