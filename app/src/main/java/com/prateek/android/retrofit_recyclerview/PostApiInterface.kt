package com.prateek.android.retrofit_recyclerview

import com.prateek.android.retrofit_recyclerview.model.PostResponse
import retrofit2.Response
import retrofit2.http.GET

interface PostApiInterface {
    @GET(POST_URL)
    suspend fun getPosts(): Response<List<PostResponse>>

    companion object {
        const val POST_URL = "/posts"
    }
}