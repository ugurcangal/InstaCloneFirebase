package com.ugurcangal.instagramclonefirebase.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ugurcangal.instagramclonefirebase.databinding.RecyclerRowBinding
import com.ugurcangal.instagramclonefirebase.model.Post

class FeedAdapter(private val postList : ArrayList<Post>) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    class FeedViewHolder( val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FeedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.binding.recyclerEmailText.text = "User : ${postList.get(position).email}"
        holder.binding.recyclerCommentText.text = "Comment : ${postList.get(position).comment} "
        Glide.with(holder.itemView.context).load(postList.get(position).downloadUrl).into(holder.binding.recyclerImageView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}