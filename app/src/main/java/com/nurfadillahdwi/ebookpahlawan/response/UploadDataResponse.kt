package com.nurfadillahdwi.ebookpahlawan.response

import com.google.gson.annotations.SerializedName

data class UploadDataResponse(

	@field:SerializedName("data")
	val data: Data2? = null,

	@field:SerializedName("meta")
	val meta: Meta? = null
)

data class Attributes2(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("peran")
	val peran: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@field:SerializedName("tgl_wafat")
	val tglWafat: String? = null,

	@field:SerializedName("tgl_lahir")
	val tglLahir: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class Data2(

	@field:SerializedName("attributes")
	val attributes: Attributes2? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Meta(
	val any: Any? = null
)
