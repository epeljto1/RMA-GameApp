package com.example.videogames

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GameImpressionAdapter(
    private var impressions: List<UserImpression>
) : RecyclerView.Adapter<GameImpressionAdapter.ViewHolder>(){


    override fun getItemViewType(position: Int): Int {
        return if (impressions[position] is UserRating) 0 else 1
    }

    class ViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder(view) {
        val username_textview: TextView
        lateinit var review_textview: TextView
        lateinit var rating_bar: RatingBar
        init {
            username_textview=view.findViewById(R.id.username_textview)
            if(viewType==1)
                review_textview = view.findViewById(R.id.review_textview)
            else rating_bar = view.findViewById((R.id.rating_bar))

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layout=if(viewType==0) R.layout.rating_layout else R.layout.review_layout
        val view=LayoutInflater.from(viewGroup.context)
            .inflate(layout,viewGroup,false)
        return ViewHolder(view,viewType)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.username_textview.text=impressions[position].username
        if(impressions[position] is UserReview)
            viewHolder.review_textview.text=getReview(impressions[position])
        else
            viewHolder.rating_bar.rating=getRating(impressions[position]).toFloat()

    }
    override fun getItemCount():Int = impressions.size

    fun updateImpressions(impressions: List<UserImpression>) {
        this.impressions = impressions
        notifyDataSetChanged()
    }

    private fun getReview(impression: UserImpression): String {
        lateinit var Review : UserReview
            Review = impression as UserReview
        return Review.review
    }

    private fun getRating(impression: UserImpression): Double
    {
        lateinit var Rating : UserRating
        Rating = impression as UserRating
        return Rating.rating
    }

}