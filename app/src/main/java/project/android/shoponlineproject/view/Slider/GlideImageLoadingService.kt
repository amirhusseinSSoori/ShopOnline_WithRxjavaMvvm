package project.android.ecommers.view.Slider

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import ss.com.bannerslider.ImageLoadingService

 class GlideImageLoadingService(var context: Context) :
    ImageLoadingService {
    override fun loadImage(
        url: String,
        imageView: ImageView
    ) {
        Glide.with(context).load(url).into(imageView)
    }

    override fun loadImage(resource: Int, imageView: ImageView) {
        Glide.with(context).load(resource).into(imageView)
    }

    override fun loadImage(
        url: String,
        placeHolder: Int,
        errorDrawable: Int,
        imageView: ImageView
    ) {
        Glide.with(context).load(url).placeholder(placeHolder).error(errorDrawable).into(imageView)
    }

}