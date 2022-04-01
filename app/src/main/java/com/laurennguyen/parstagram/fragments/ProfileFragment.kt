package com.laurennguyen.parstagram.fragments

import android.util.Log
import com.laurennguyen.parstagram.Post
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment:FeedFragment() {
     override fun queryPosts () {
         // Specify which class to query
         val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)

         //Find all Post objects
         query.include(Post.KEY_USER)

         //Only return posts from current user
         query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())

         //Return posts in descending order (ie: more recent posts will appear first)
         query.addDescendingOrder("createdAt")

         //Only return the most recent 20 posts
         query.setLimit(20)

         query.findInBackground(object: FindCallback<Post> {
             override fun done(posts: MutableList<Post>?, e: ParseException?) {
                 if (e != null) {
                     //Something has gone wrong
                     Log.e(TAG, "Error fetching posts")
                 } else {
                     if (posts != null) {
                         for (post in posts) {
                             Log.i(TAG, "Post: " + post.getDescription() + " , username: " + post.getUser()?.username)
                         }
                         adapter.clear()
                         allPosts.addAll(posts)
                         adapter.notifyDataSetChanged()
                         swipeContainer.setRefreshing(false)
                     }
                 }
             }

         })
     }
}