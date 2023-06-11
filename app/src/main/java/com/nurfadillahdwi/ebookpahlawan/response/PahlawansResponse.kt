package com.nurfadillahdwi.ebookpahlawan.response

import com.google.gson.annotations.SerializedName
import com.nurfadillahdwi.ebookpahlawan.response.extra.Attributes
data class PahlawansResponse(
	@SerializedName("data")
	val data: List<DataItem?>? = null,
)

data class DataItem (
	@SerializedName("id")
	var id: Int? = null,
	@SerializedName("attributes")
	var attributes: com.nurfadillahdwi.ebookpahlawan.response.Attributes? = com.nurfadillahdwi.ebookpahlawan.response.Attributes()
)

data class Attributes (
	@SerializedName("nama")
	var nama: String? = null,
	@SerializedName("tgl_lahir")
	var tglLahir: String? = null,
	@SerializedName("tgl_wafat")
	var tglWafat: String? = null,
	@SerializedName("keterangan") var keterangan: String? = null,
	@SerializedName("peran") var peran: String? = null,
	@SerializedName("link_pahlawan") var linkPahlawan: String? = null,
	@SerializedName("avatar") var avatar: Avatar? = Avatar()

)

data class Avatar (
	@SerializedName("data")
	var data: Data? = Data()
)

data class Data (
	@SerializedName("id")
	var id: Int? = null,
	@SerializedName("attributes")
	var attributes: Attributes? = Attributes()
)


