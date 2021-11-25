package com.example.worldflags

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.worldflags.network.FlagPhoto
import com.example.worldflags.overview.PhotoGridAdapter



//@BindingAdapter("imageUrl")
//fun bindImage(imgView: ImageView, imgUrl: String?, name : String? , textView: TextView) {
//    imgUrl?.let {
//        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
//        imgView.load(imgUri)
//    }
//}
//
//@BindingAdapter("textset")
//fun bindText(name : String? , textView: TextView) {
//    textView.setText(name)
//}

@BindingAdapter("imageUrl")
fun ImageView.bind(imageUrl: String?){
    imageUrl?.let {
        val photoUri = imageUrl.toUri().buildUpon().scheme("https").build()
        this.load(photoUri) {
            placeholder(R.drawable.loading_img)
            error(R.drawable.ic_connection_error)
        }
    }
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