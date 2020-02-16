package com.puldroid.networking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.squareup.picasso.Picasso

/**
 * @author aggarwalpulkit596
 */

class PhotoAdapter(val photos: List<Photos>) : RecyclerView.Adapter<PhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_photo,
                parent,
                false
            )
        )

    override fun getItemCount() = photos.size

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position])
    }

}

class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(photos: Photos) {
        val imageView = itemView as ShapeableImageView
        Picasso.get().load(photos.url).placeholder(R.drawable.ic_launcher_background).into(imageView)
        imageView.shapeAppearanceModel = imageView.shapeAppearanceModel.toBuilder()
            .setBottomRightCorner(CornerFamily.CUT, 100f)
            .setBottomLeftCorner(CornerFamily.CUT, 100f)
            .setTopLeftCorner(CornerFamily.ROUNDED, 100f)
            .setTopRightCorner(CornerFamily.ROUNDED, 100f)
            .build()

    }
}