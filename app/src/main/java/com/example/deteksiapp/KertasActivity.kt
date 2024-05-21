package com.example.deteksiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class KertasActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listprodukkaca: ArrayList<Produk>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>
    lateinit var deskList: Array<String>
    lateinit var bahanList: Array<String>
    lateinit var langkahList: Array<String>
    lateinit var textbahan: Array<String>
    lateinit var textlangkah: Array<String>
    private lateinit var myAdapter: KacaAdapter
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<Produk>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kertas)

        imageList = arrayOf(
            R.drawable.kertas1,
            R.drawable.kertas2,
            R.drawable.kertas3,
            R.drawable.kertas4,
            R.drawable.kertas5,
            R.drawable.kertas6,
        )

        titleList = arrayOf(
            "Bunga Kertas",
            "Kertas Daur Ulang",
            "Kotak Pengimpanan dari Gulungan Koran",
            "Dekorasi Dinding dari Kertas Lipat ",
            "Bingkai Foto dari Karton Bekas",
            "Tempat Pensil dari Gulungan Kertas",
        )

        deskList = arrayOf(
            "lorem impusme",
            "lorem impusme",
            "lorem impusme",
            "lorem impusme",
            "lorem impusme",
            "lorem impusme",
            "lorem impusme",
            "lorem impusme",
        )

        bahanList = arrayOf(
            getString(R.string.alatkertas1),
            getString(R.string.alatkertas2),
            getString(R.string.alatkertas3),
            getString(R.string.alatkertas4),
            getString(R.string.alatkertas5),
            getString(R.string.alatkertas6),
        )

        langkahList = arrayOf(
            getString(R.string.langkahkertas1),
            getString(R.string.langkahkertas2),
            getString(R.string.langkahkertas3),
            getString(R.string.langkahkertas4),
            getString(R.string.langkahkertas5),
            getString(R.string.langkahkertas6),
        )

        textbahan = arrayOf(
            getString(R.string.alat),
            getString(R.string.alat),
            getString(R.string.alat),
            getString(R.string.alat),
            getString(R.string.alat),
            getString(R.string.alat),
        )

        textlangkah = arrayOf(
            getString(R.string.langkah),
            getString(R.string.langkah),
            getString(R.string.langkah),
            getString(R.string.langkah),
            getString(R.string.langkah),
            getString(R.string.langkah),
        )
        recyclerView = findViewById(R.id.rv_user)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        listprodukkaca = arrayListOf<Produk>()
        searchList = arrayListOf<Produk>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    listprodukkaca.forEach{
                        if (it.titleproduk.lowercase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(listprodukkaca)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }
        })
        myAdapter = KacaAdapter(searchList)
        recyclerView.adapter = myAdapter

        myAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }
    }
    private fun getData(){
        for ( i in imageList.indices){
            val produk = Produk(titleList[i], deskList [i], imageList[i], bahanList[i], langkahList[i], textbahan[i], textlangkah[i] )
            listprodukkaca.add(produk)
        }
        searchList.addAll(listprodukkaca)
        recyclerView.adapter = KacaAdapter(searchList)
    }
}