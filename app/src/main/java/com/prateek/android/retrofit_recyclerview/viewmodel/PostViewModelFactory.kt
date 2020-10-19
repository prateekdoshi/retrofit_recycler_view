package com.prateek.android.retrofit_recyclerview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prateek.android.retrofit_recyclerview.api.PostRepositoryHelper
import java.lang.IllegalArgumentException

class PostViewModelFactory(private val apiHelperPost: PostRepositoryHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(apiHelperPost) as T
        }
        throw IllegalArgumentException("Unknown class")
    }
}