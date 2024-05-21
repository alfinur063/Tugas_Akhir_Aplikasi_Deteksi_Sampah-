package com.example.deteksiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickListener()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)

        bottomNavigationView.selectedItemId = R.id.bottom_home
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_home -> true
                R.id.bottom_photo -> {
                    startActivity(Intent(applicationContext, PhotoActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                R.id.bottom_settings -> {
                    startActivity(Intent(applicationContext, SettingsActivity::class.java))
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    public fun clickListener(){
        var imageplastic = findViewById<ImageView>(R.id.ivplastik);
        var imagekertas = findViewById<ImageView>(R.id.ivkertas);
        var imagekaca = findViewById<ImageView>(R.id.ivkaca);
        var imagekardus = findViewById<ImageView>(R.id.ivkardus);
        var imagelogam = findViewById<ImageView>(R.id.ivlogam);
        var imagepakaian = findViewById<ImageView>(R.id.ivpakaian);

        imageplastic.setOnClickListener {
            openPlastikActivity()
        }

        imagekertas.setOnClickListener {
            openKertasActivity()
        }

        imagekaca.setOnClickListener {
            openKacaActivity()
        }

        imagekardus.setOnClickListener {
            openKardusActivity()
        }
        imagelogam.setOnClickListener {
            openLogamActivity()
        }
        imagepakaian.setOnClickListener {
            openPakaianActivity()
        }

    }

    public fun openPlastikActivity(){
        startActivity(Intent(this@MainActivity, PlastikActivity::class.java))
    }

    public fun openKertasActivity(){
        startActivity(Intent(this@MainActivity, KertasActivity::class.java))
    }
    public fun openKardusActivity(){
        startActivity(Intent(this@MainActivity, KardusActivity::class.java))
    }
    public fun openKacaActivity(){
        startActivity(Intent(this@MainActivity, KacaActivity::class.java))
    }
    public fun openLogamActivity(){
        startActivity(Intent(this@MainActivity, LogamActivity::class.java))
    }
    public fun openPakaianActivity(){
        startActivity(Intent(this@MainActivity, PakaianActivity::class.java))
    }
}