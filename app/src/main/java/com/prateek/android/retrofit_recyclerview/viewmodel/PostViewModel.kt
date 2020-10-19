package com.prateek.android.retrofit_recyclerview.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prateek.android.retrofit_recyclerview.api.PostRepositoryHelper
import com.prateek.android.retrofit_recyclerview.model.Post
import kotlinx.coroutines.launch

class PostViewModel(private val apiHelperPost: PostRepositoryHelper) : ViewModel() {
    private var postData: MutableLiveData<List<Post>> = MutableLiveData()
    private var morePostData: MutableLiveData<List<Post>> = MutableLiveData()


    fun fetchPost() {
        viewModelScope.launch {
            val list = apiHelperPost.getPosts()
            postData.value = list
        }
    }

    fun fetchMorePost() {
        viewModelScope.launch {
            val list = apiHelperPost.getPosts()
            morePostData.value = list
        }
    }

    fun getPosts(): LiveData<List<Post>> = postData
    fun getMorePosts(): LiveData<List<Post>> = morePostData
}