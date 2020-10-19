package com.prateek.android.retrofit_recyclerview.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.prateek.android.retrofit_recyclerview.R
import com.prateek.android.retrofit_recyclerview.api.PostRepository
import com.prateek.android.retrofit_recyclerview.model.Post
import com.prateek.android.retrofit_recyclerview.viewmodel.PostViewModel
import com.prateek.android.retrofit_recyclerview.viewmodel.PostViewModelFactory
import kotlinx.android.synthetic.main.fragment_first.*


class LaunchFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var postViewModel: PostViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        postViewModel = ViewModelProvider(this,PostViewModelFactory(PostRepository.INSTANCE)).get(PostViewModel::class.java)
        postViewModel.getPosts().observe(viewLifecycleOwner, Observer { list ->
            if (list != null) {
                navigateToListView(Bundle().also {
                    it.putParcelableArrayList(
                        "postList",
                        list as ArrayList<Post>
                    )
                })
            }
        })
        click_me.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.click_me -> {
                status.text = "Button clicked"
                postViewModel.fetchPost()
            }
        }
    }

    private fun navigateToListView(bundle: Bundle? = null) {
        navController.navigate(
            R.id.action_firstFragment_to_listFragment, bundle
        )
    }
}