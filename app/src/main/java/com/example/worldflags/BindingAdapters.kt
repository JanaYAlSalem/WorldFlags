package com.example.worldflags

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.worldflags.network.FlagPhoto
import com.example.worldflags.overview.PhotoGridAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?, name : String? , textView: TextView ) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}
@BindingAdapter("textUrl")
fun bindText(name : String? , textView: TextView ) {
    textView.setText(name)
}



@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,
                     data: List<FlagPhoto>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)


}