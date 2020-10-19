package com.prateek.android.retrofit_recyclerview.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class PostResponse {

    @SerializedName("userId")
    @Expose
    private val userId: Int? = null

    @SerializedName("id")
    @Expose
    private val id: Int? = null

    @SerializedName("title")
    @Expose
    private val title: String? = null

    @SerializedName("body")
    @Expose
    private val body: String? = null

    fun getId(): Int? = id

    fun getContent(): String? = body

    fun getUserId(): Int? = userId

    fun getTitle(): String? = title

}