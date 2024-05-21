package com.example.deteksiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class PlastikActivity : AppCompatActivity() {


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
        setContentView(R.layout.activity_plastik)


        imageList = arrayOf(
            R.drawable.plastik1,
            R.drawable.plastik2,
            R.drawable.plastik3,
            R.drawable.plastik4,
            R.drawable.plastik5,
            R.drawable.plastik6,
        )

        titleList = arrayOf(
            "Bunga Hias",
            "Tempat Pensil",
            "Pot Gantung",
            "Vas Bunga",
            "Pot",
            "Celengan",
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
            getString(R.string.alatplastik1),
            getString(R.string.alatplastik2),
            getString(R.string.alatplastik3),
            getString(R.string.alatplastik4),
            getString(R.string.alatplastik5),
            getString(R.string.alatplastik6),
        )

        langkahList = arrayOf(
            getString(R.string.langkahplastik1),
            getString(R.string.langkahplastik2),
            getString(R.string.langkahplastik3),
            getString(R.string.langkahplastik4),
            getString(R.string.langkahplastik5),
            getString(R.string.langkahplastik6),
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