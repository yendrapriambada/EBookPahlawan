package com.nurfadillahdwi.ebookpahlawan.response

import com.google.gson.annotations.SerializedName

data class FileResponse(

	@field:SerializedName("FileResponse")
	val fileResponse: List<FileResponseItem?>? = null
)

data class Thumbnail(

	@field:SerializedName("ext")
	val ext: String? = null,

	@field:SerializedName("path")
	val path: Any? = null,

	@field:SerializedName("size")
	val size: Any? = null,

	@field:SerializedName("mime")
	val mime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("hash")
	val hash: String? = null,

	@field:SerializedName("height")
	val height: Int? = null
)

data class FileResponseItem(

	@field:SerializedName("ext")
	val ext: String? = null,

	@field:SerializedName("formats")
	val formats: Formats? = null,

	@field:SerializedName("previewUrl")
	val previewUrl: Any? = null,

	@field:SerializedName("mime")
	val mime: String? = null,

	@field:SerializedName("caption")
	val caption: Any? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("size")
	val size: Any? = null,

	@field:SerializedName("provider")
	val provider: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("provider_metadata")
	val providerMetadata: Any? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("alternativeText")
	val alternativeText: Any? = null,

	@field:SerializedName("hash")
	val hash: String? = null,

	@field:SerializedName("height")
	val height: Int? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)

data class Formats(

	@field:SerializedName("thumbnail")
	val thumbnail: Thumbnail? = null
)
