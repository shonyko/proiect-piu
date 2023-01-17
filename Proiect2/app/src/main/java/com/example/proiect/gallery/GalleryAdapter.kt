package com.example.proiect.gallery

//import com.bumptech.glide.Glide
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proiect.databinding.FragmentGalleryListItemBinding


class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    inner class GalleryViewHolder(
        val binding: FragmentGalleryListItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    private var dataSource = mutableListOf<Int>()

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = FragmentGalleryListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        context = parent.context

        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val url = dataSource[position]
        println(url)

        with(holder) {
//            Glide.with(binding.image.context)
//                .load(url)
//                .into(binding.image)
//            Picasso.get().load(url).resize(50, 50).into(binding.image)
//            Picasso.get().load(url).into(binding.image)
//            val uiHandler = Handler(Looper.getMainLooper())
//            uiHandler.post(Runnable {
//                Picasso.get()
//                    .load(url)
//                    .into(binding.image)
//            })
//            binding.image.load(url)
        }
    }

    override fun getItemCount(): Int = dataSource.size

    fun refreshData(urls: List<Int>) {
        dataSource.clear()
        dataSource.addAll(urls)
        this.notifyDataSetChanged()
    }
}