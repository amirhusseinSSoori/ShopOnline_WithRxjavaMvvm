package project.android.shoponlineproject.view.Slider

import project.android.ecommers.model.Slider
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class DetailsSliderAdabter (var List:List<Slider>): SliderAdapter() {
    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindImageSlide(
        position: Int,
        viewHolder: ImageSlideViewHolder
    ) {
        when (position) {
            0 -> viewHolder.bindImageSlide(List[0].image)
            1 -> viewHolder.bindImageSlide(List[1].image)
            2-> viewHolder.bindImageSlide(List[2].image)
            3 -> viewHolder.bindImageSlide(List[3].image)

        }
    }
}