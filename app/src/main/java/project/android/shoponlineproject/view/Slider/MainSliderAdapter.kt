package project.android.ecommers.view.Slider

import project.android.ecommers.model.Slider
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class MainSliderAdapter (): SliderAdapter() {
    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindImageSlide(
        position: Int,
        viewHolder: ImageSlideViewHolder
    ) {
        when (position) {
            0 -> viewHolder.bindImageSlide("http://amirhusseindeveloper.ir/images/sliding/banana.jpg")
            1 -> viewHolder.bindImageSlide("http://amirhusseindeveloper.ir/images/sliding/orange.jpg")
            2 -> viewHolder.bindImageSlide("http://amirhusseindeveloper.ir/images/sliding/pineapple.jpg")
            3 -> viewHolder.bindImageSlide("http://amirhusseindeveloper.ir/images/sliding/strawberry.jpg")
        }
    }
}