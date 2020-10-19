package com.prateek.android.retrofit_recyclerview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.prateek.android.retrofit_recyclerview.R
import com.prateek.android.retrofit_recyclerview.adapter.PostAdapter
import com.prateek.android.retrofit_recyclerview.api.PostRepository
import com.prateek.android.retrofit_recyclerview.model.Post
import com.prateek.android.retrofit_recyclerview.viewmodel.PostViewModel
import com.prateek.android.retrofit_recyclerview.viewmodel.PostViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*


class PostListFragment : Fragment(), View.OnClickListener {

    var postList: ArrayList<Post>? = null
    lateinit var postViewModel: PostViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMore.setOnClickListener(this)
        postList = arguments?.let { it.getParcelableArrayList<Post>("postList")?.slice(1..3) as ArrayList<Post> }
        recyclerview.apply {
            adapter = postList?.let { postList -> PostAdapter(postList) }
            layoutManager = LinearLayoutManager(activity)
        }
        postViewModel = ViewModelProvider(this, PostViewModelFactory(PostRepository.INSTANCE)).get(PostViewModel::class.java)
        postViewModel.getMorePosts().observe(viewLifecycleOwner, Observer { newPostList ->
            val add = postList?.addAll(newPostList)
            if (add!!) {
                recyclerview.adapter?.notifyDataSetChanged()
            }
        })
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.loadMore -> {
                postViewModel.fetchMorePost()
            }
        }
    }
}