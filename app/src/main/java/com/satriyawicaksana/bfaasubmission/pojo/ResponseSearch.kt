package com.satriyawicaksana.bfaasubmission.pojo

import com.google.gson.annotations.SerializedName

data class ResponseSearch(

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("html_url")
	val htmlUrl: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("login")
	val login: String
)
