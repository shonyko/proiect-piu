package com.example.proiect.repo

import com.example.proiect.R

interface GalleryRepo {
    fun getPictureUrls(): List<Int>
}

object GalleryRepoImpl : GalleryRepo {
    private val pictureUrls: List<Int> = listOf(
        R.drawable.gal1,
        R.drawable.gal2,
        R.drawable.gal3,
        R.drawable.gal4,
        R.drawable.gal5,
    )

    fun t() {

    }

    override fun getPictureUrls(): List<Int> = pictureUrls
}