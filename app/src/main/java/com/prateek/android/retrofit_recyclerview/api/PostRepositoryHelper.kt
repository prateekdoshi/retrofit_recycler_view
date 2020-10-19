package com.prateek.android.retrofit_recyclerview.api

import androidx.lifecycle.LiveData
import com.prateek.android.retrofit_recyclerview.model.Post
import com.prateek.android.retrofit_recyclerview.model.PostResponse

interface PostRepositoryHelper {
    suspend fun getPosts(): List<Post>
    suspend fun getPostsViewModel(): LiveData<List<PostResponse>>

}