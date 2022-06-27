package com.jantonioc.lab7recyclerview.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jantonioc.lab7recyclerview.R
import com.jantonioc.lab7recyclerview.util.fromUrl

class DogsAdapter(private val images: List<String>, private val context: Context) : RecyclerView.Adapter<DogsAdapter.DogsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val cardView = layoutInflater.inflate(R.layout.card_layout,parent,false)
        return DogsViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val currentDog = images[position]
        holder.bind(currentDog)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class DogsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        fun bind(image:String)
        {
            //vincular la imagen al imageview
            //funciones de extension
            val ivDog = itemView.findViewById<ImageView>(R.id.imageView)
            ivDog.fromUrl(image)
        }

    }



}