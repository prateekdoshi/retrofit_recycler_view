package com.prateek.android.retrofit_recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prateek.android.retrofit_recyclerview.R
import com.prateek.android.retrofit_recyclerview.model.Post

class PostAdapter(private val postList: ArrayList<Post>) : RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_layout, parent, false)
        return PostViewHolder(v)
    }


    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position])
    }


}

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(post: Post) {
        val textId = itemView.findViewById(R.id.id) as TextView
        val textContent = itemView.findViewById(R.id.content) as TextView
        textId.text = post.id.toString()
        textContent.text = post.content
    }
}
