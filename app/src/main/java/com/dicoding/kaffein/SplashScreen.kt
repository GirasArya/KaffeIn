package com.dicoding.kaffein

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splash_screen = findViewById<ImageView>(R.id.splash_screen)
        splash_screen.alpha = 0f
        splash_screen.animate().setDuration(500).alpha(1f).withEndAction{
            val splash = Intent(this, MainActivity::class.java)
            startActivity(splash)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }
    }
}