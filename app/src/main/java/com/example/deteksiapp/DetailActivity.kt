package com.example.deteksiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity() {

    private lateinit var TextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        TextView = findViewById(R.id.textbahan)

        val getData = intent.getParcelableExtra<Produk>("android")
        if (getData != null) {
            val detailTitle: TextView = findViewById(R.id.title_detail)
            val detailImage: ImageView = findViewById(R.id.img_detail)
            val detailBahan: TextView = findViewById(R.id.detailAlat)
            val detailLangkah: TextView = findViewById(R.id.detaipembuatan)
            val textbahan: TextView = findViewById(R.id.textbahan)
            val textlangkah: TextView = findViewById(R.id.textlangkah)


            detailTitle.text = getData.titleproduk
            detailImage.setImageResource(getData.imageproduk)
            detailBahan.text = getData.bahanproduk
            detailLangkah.text = getData.langkahproduk
            textbahan.text = getData.textbahan
            textlangkah.text=getData.textlangkah
        }
    }
}