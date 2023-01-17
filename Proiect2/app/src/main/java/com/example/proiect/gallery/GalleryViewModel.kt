//package com.example.proiect.gallery
//
//import android.graphics.Bitmap
//import android.os.Handler
//import android.os.Looper
//import androidx.lifecycle.ViewModel
//import com.example.proiect.repo.GalleryRepoImpl
//import com.squareup.picasso.Picasso
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//
//
//class GalleryViewModel : ViewModel() {
//    private val _viewState = MutableStateFlow(
//        GalleryViewState(
//            images = fetchImages(GalleryRepoImpl.getPictureUrls())
//        )
//    )
//    val state = _viewState.asStateFlow()
//
//    fun fetchImages(urls: List<Int>): List<Bitmap> {
////        val images = urls.map {
////            val uiHandler = Handler(Looper.getMainLooper())
////            var t: Bitmap
////            uiHandler.post(Runnable {
////                t = Picasso.get()
////                    .load(it)
////                    .get()
////            })
////            t
////        }
////        return images
////        _viewState.update {
////            it.copy(images = images)
////        }
//    }
//}
//
//data class GalleryViewState(
//    val images: List<Bitmap>
//)