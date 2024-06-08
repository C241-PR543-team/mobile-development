package com.moove.mooveapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class ViewPagerAdapter(private val context: Context) : PagerAdapter() {

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

    override fun getCount(): Int {
        return headings.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.slider_layout, container, false)

        val slideTitleImage: ImageView = view.findViewById(R.id.titleImage)
        val slideHeading: TextView = view.findViewById(R.id.texttitle)
        val slideDescription: TextView = view.findViewById(R.id.textdesc)

        slideTitleImage.setImageResource(images[position])
        slideHeading.setText(headings[position])
        slideDescription.setText(descriptions[position])

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
