package com.example.deteksiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class KardusActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_kardus)

        imageList = arrayOf(
            R.drawable.kardus1,
            R.drawable.kardus2,
            R.drawable.kardus3,
            R.drawable.kardus4,
            R.drawable.kardus5,
            R.drawable.kardus6,
        )

        titleList = arrayOf(
            "Celengan",
            "Tas Tangan Cantik",
            "Kotak Pensil",
            "Tempat Tisu",
            "Tempat Tidur Kucing",
            "Bingkai Foto",
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
            getString(R.string.alatkardus1),
            getString(R.string.alatkardus2),
            getString(R.string.alatkardus3),
            getString(R.string.alatkardus4),
            getString(R.string.alatkardus5),
            getString(R.string.alatkardus6),

        )

        langkahList = arrayOf(
            getString(R.string.langkahkardus1),
            getString(R.string.langkahkardus2),
            getString(R.string.langkahkardus3),
            getString(R.string.langkahkardus4),
            getString(R.string.langkahkardus5),
            getString(R.string.langkahkardus6),
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