package com.moove.mooveapp.data.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("body")
	val body: Body? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Body(

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
