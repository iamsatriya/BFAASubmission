package com.satriyawicaksana.bfaasubmission.pojo

import com.google.gson.annotations.SerializedName

data class ResponseDetailUser(

	@field:SerializedName("followers")
	val followers: Int,

	@field:SerializedName("avatar_url")
	val avatarUrl: String,

	@field:SerializedName("html_url")
	val htmlUrl: String,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("name")
	val name: Any,

	@field:SerializedName("company")
	val company: Any,

	@field:SerializedName("location")
	val location: Any,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("public_repos")
	val publicRepos: Int,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("email")
	val email: Any
)
