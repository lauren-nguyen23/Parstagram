package com.laurennguyen.parstagram

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter (val context: Context, val posts: ArrayList<Post>)
    : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
        //Specify the layout file to use for this item
        val view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
        val post = posts.get(position)
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    // Clean all elements of the recycler
    fun clear() {
        posts.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used
    fun addAll(postList: List<Post>) {
        posts.addAll(postList)
        notifyDataSetChanged()
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvUsername : TextView
        val ivImage : ImageView
        val tvCaption : TextView

        init {
            tvUsername = itemView.findViewById(R.id.tv_username)
            ivImage = itemView.findViewById(R.id.iv_image)
            tvCaption = itemView.findViewById(R.id.tv_caption)
        }

        fun bind (post:Post) {
            tvUsername.text = post.getUser()?.username
            tvCaption.text = post.getDescription()

            //Populate the image view using the Glide library
            Glide.with(itemView.context).load(post.getImage()?.url).into(ivImage)
        }
    }
}