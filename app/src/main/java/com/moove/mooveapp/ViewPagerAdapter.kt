package com.moove.mooveapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter(private val context: Context) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private val images = intArrayOf(
        R.drawable.intro_1,
        R.drawable.intro_2,
        R.drawable.intro_3,
    )

    private val headings = intArrayOf(
        R.string.heading_1,
        R.string.heading_2,
        R.string.heading_3,
    )

    private val descriptions = intArrayOf(
        R.string.desc_1,
        R.string.desc_2,
        R.string.desc_3,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.slider_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.slideTitleImage.setImageResource(images[position])
        holder.slideHeading.setText(headings[position])
        holder.slideDescription.setText(descriptions[position])
    }

    override fun getItemCount(): Int {
        return headings.size
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val slideTitleImage: ImageView = view.findViewById(R.id.titleImage)
        val slideHeading: TextView = view.findViewById(R.id.texttitle)
        val slideDescription: TextView = view.findViewById(R.id.textdesc)
    }
}
