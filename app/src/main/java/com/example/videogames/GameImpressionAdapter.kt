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
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val username_textview: TextView
        init {
            username_textview=view.findViewById(R.id.username_textview)

        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layout=if(viewType==0) R.layout.rating_layout else R.layout.review_layout
        val view=LayoutInflater.from(viewGroup.context)
            .inflate(layout,viewGroup,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.username_textview.text=impressions[position].username
    }
    override fun getItemCount():Int = impressions.size

    fun updateImpressions(impressions: List<UserImpression>) {
        this.impressions = impressions
        notifyDataSetChanged()
    }

}