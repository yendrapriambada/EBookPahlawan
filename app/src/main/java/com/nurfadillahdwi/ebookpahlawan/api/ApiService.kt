package com.nurfadillahdwi.ebookpahlawan.api

import retrofit2.Call
import com.nurfadillahdwi.ebookpahlawan.response.DataItem
import com.nurfadillahdwi.ebookpahlawan.response.FileResponse
import com.nurfadillahdwi.ebookpahlawan.response.PahlawansResponse
import com.nurfadillahdwi.ebookpahlawan.response.UploadDataResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @GET("api/ebook-pahlawans")
    fun getPahlawan(
        @Header("Authorization") token: String,
        @Query("populate") populate: String = "*"
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


//    @FormUrlEncoded
//    @POST("/v1/register")
//    fun register(
//        @Field("name") name: String,
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Call<RegisterResponse>
//
//    @FormUrlEncoded
//    @POST("/v1/login")
//    fun login(
//        @Field("email") email: String,
//        @Field("password") password: String
//    ): Call<LoginResponse>


//    @GET("/v1/stories")
//    suspend fun getAllStories(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int,
//        @Query("size") size: Int
//    ): ListStoriesRespond
//
//    @GET("/v1/stories")
//    suspend fun getStoriesWithLocation(
//        @Header("Authorization") token: String,
//        @Query("location") location: Int = 1
//    ): ListStoriesRespond
//
//    @Multipart
//    @POST("/v1/stories")
//    fun uploadStory(
//        @Header("Authorization") token: String,
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//        @Part("lat") latitude: RequestBody,
//        @Part("lon") longitude: RequestBody
//    ): Call<UploadStoryResponse>
}