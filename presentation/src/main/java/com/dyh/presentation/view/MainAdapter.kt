package com.dyh.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.dyh.domain.entity.Post
import com.dyh.presentation.R
import com.dyh.presentation.databinding.ItemMainBinding
import kotlin.properties.Delegates

class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var mPostList: List<Post> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = if (mPostList.isNullOrEmpty()) 0 else mPostList.size

    private fun getItem(position: Int): Post = mPostList[position]

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderPostBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.item_main, parent, false
        )
        return PostViewHolder(holderPostBinding)
    }
    private inner class PostViewHolder(private val viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun onBind(post: Post) {
            (viewDataBinding as ItemMainBinding).post = post
        }
    }
}