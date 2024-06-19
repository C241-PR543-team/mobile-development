package com.moove.mooveapp.view.home.destinasi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moove.mooveapp.databinding.ItemDestinasiPopulerBinding

class DestinasiPopulerAdapter(
    private val context: Context,
    private val destinasiPopulerList: List<DestinasiPopulerItem>
) :
    RecyclerView.Adapter<DestinasiPopulerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDestinasiPopulerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = destinasiPopulerList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return destinasiPopulerList.size
    }

    inner class ViewHolder(private val binding: ItemDestinasiPopulerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DestinasiPopulerItem) {
            binding.imgDestinasi.setImageResource(item.imageResource)
            binding.tvTitleDestinasi.text = item.title
        }
    }
}
