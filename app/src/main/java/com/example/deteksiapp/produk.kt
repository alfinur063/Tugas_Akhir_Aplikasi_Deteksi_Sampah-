package com.example.deteksiapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class  Produk(
    var titleproduk: String,
    var description: String,
    var imageproduk: Int,
    var bahanproduk: String,
    var langkahproduk: String,
    var textbahan: String,
    var textlangkah: String
):Parcelable