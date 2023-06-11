package com.nurfadillahdwi.ebookpahlawan.api

import com.nurfadillahdwi.ebookpahlawan.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("api/ebook-pahlawans?fields[0]=nama&fields[1]=tgl_lahir&fields[2]=tgl_wafat&fields[3]=keterangan&fields[4]=peran&fields[5]=link_pahlawan&pagination[limit]=50&populate[avatar][fields][0]=url&sort[0]=id&filters[nama_siswa][\$eqi][0]=all")
    fun getPahlawan(
        @Header("Authorization") token: String,
        @Query("filters[nama_siswa][\$eqi][1]") namaSiswa: String
    ): Call<PahlawansResponse>

    @Multipart
    @POST("api/ebook-pahlawans")
    fun uploadDataPahlawan(
        @Header("Authorization") token: String,
        @PartMap data: Map<String, @JvmSuppressWildcards RequestBody>
    ): Call<UploadDataResponse>

    @Multipart
    @POST("api/upload")
    fun uploadImagePahlawan(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("ref") ref: RequestBody,
        @Part("refId") refId: RequestBody,
        @Part("field") field: RequestBody
    ): Call<FileResponse>
}