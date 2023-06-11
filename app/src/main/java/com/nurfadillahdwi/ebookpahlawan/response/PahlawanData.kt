package com.nurfadillahdwi.ebookpahlawan.response

data class PahlawanData(
    val nama: String,
    val tgl_lahir: String,
    val tgl_wafat: String,
    val keterangan: String,
    val peran: String,
    val avatar: String?, // assuming avatar field is of type String
    val nama_siswa: String,
    val link_pahlawan: String?
)
