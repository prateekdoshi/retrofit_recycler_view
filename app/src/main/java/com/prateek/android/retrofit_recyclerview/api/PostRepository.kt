package com.prateek.android.retrofit_recyclerview.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.prateek.android.retrofit_recyclerview.PostApiInterface
import com.prateek.android.retrofit_recyclerview.RetroHTTP
import com.prateek.android.retrofit_recyclerview.model.Post
import com.prateek.android.retrofit_recyclerview.model.PostResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepository : PostRepositoryHelper {

    private val api = RetroHTTP.INSTANCE.getService(PostApiInterface::class.java)

    companion object {
        val INSTANCE: PostRepository by lazy { PostRepository() }
    }

    override suspend fun getPosts(): List<Post> = withContext(Dispatchers.Default) {
        val responseList = api.getPosts()
        return@withContext if (responseList.isSuccessful && !responseList.body()
                        .isNullOrEmpty()
        ) getProcessedList(responseList.body()!!) else emptyList()
    }

    override suspend fun getPostsViewModel(): LiveData<List<PostResponse>> = liveData {
        val responseList = api.getPosts() // getPosts is a suspend function.
        if (responseList.isSuccessful && !responseList.body()
                        .isNullOrEmpty()
        ) emit(responseList.body()!!) else emit(emptyList())
    }

    private suspend fun getProcessedList(postResponseList: List<PostResponse>): List<Post> = withContext(Dispatchers.Default) {
        if (postResponseList.isNullOrEmpty()) return@withContext emptyList<Post>() else {
            val postList = mutableListOf<Post>()
            for (p: PostResponse in postResponseList) {
                postList.add(Post(p.getId(), p.getUserId(), p.getTitle(), p.getContent()))
            }
            return@withContext postList
        }
    }

}