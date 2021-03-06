package com.example.worldflags

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.example.worldflags.network.FlagPhoto
import com.example.worldflags.overview.FlagsApiStatus
import com.example.worldflags.overview.PhotoGridAdapter



@BindingAdapter("imageUrl")
fun ImageView.bind(imageUrl: String?){
    imageUrl?.let {

        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(context)) }
            .build()

        val request = ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .data(imageUrl)
            .target(this)
            .build()


        imageLoader.enqueue(request)
        }

    }

// if not svg photos
//fun ImageView.bind(imageUrl: String?){
//    imageUrl?.let {
//        val photoUri = imageUrl.toUri().buildUpon().scheme("https").build()
//        this.load(photoUri) {
//            placeholder(R.drawable.loading_img)
//            error(R.drawable.ic_broken_image)
//        }
//    }
//}

@BindingAdapter("textset")
fun bindText(textView: TextView,name : String?) {
    textView.setText(name)
}



@BindingAdapter("listData")
fun RecyclerView.bindRecyclerView(data: List<FlagPhoto>?
) {
    if (this.adapter == null) {
        this.adapter = PhotoGridAdapter()
    }

    val adapter = this.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("flagsApiStatus")
fun bindStatus(statusImageView: ImageView, status: FlagsApiStatus?) {
    when (status) {
        FlagsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FlagsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FlagsApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}