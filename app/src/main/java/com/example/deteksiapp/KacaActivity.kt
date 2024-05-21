package com.example.deteksiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class KacaActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_kaca)

        imageList = arrayOf(
            R.drawable.kaca1,
            R.drawable.kaca2,
            R.drawable.kaca3,
            R.drawable.kaca4,
            R.drawable.kaca5,
            R.drawable.kaca6,
        )

        titleList = arrayOf(
            "Vas Bunga dari Botol Kaca Bekas",
            "Tempat Lilin dari Kaca Bekas",
            "Gelang atau Kalung dari Pecahan Kaca",
            "Mozaik Hias dari Pecahan Kaca",
            "Lampu Gantung dari Botol Kaca Bekas",
            "Lukisan Kaca dari Kepingan Kaca Berwarna",
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
            getString(R.string.alatkaca1),
            getString(R.string.alatkaca2),
            getString(R.string.alatkaca3),
            getString(R.string.alatkaca4),
            getString(R.string.alatkaca5),
            getString(R.string.alatkaca6),
        )

        langkahList = arrayOf(
            getString(R.string.langkahkaca1),
            getString(R.string.langkahkaca2),
            getString(R.string.langkahkaca3),
            getString(R.string.langkahkaca4),
            getString(R.string.langkahkaca5),
            getString(R.string.langkahkaca6),
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