package project.android.shoponlineproject.view.Adabter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import project.android.ecommers.model.Slider
import project.android.shoponlineproject.R
import project.android.shoponlineproject.databinding.ItemsliderBinding

class AdabterSlider(var list: List<Slider>): PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun getCount(): Int =list.size


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val bind: ItemsliderBinding = DataBindingUtil.inflate(
            LayoutInflater.from(container.context),
            R.layout.itemslider,container,false)

        bind.data=list[position]
        container.addView(bind.root)
        return bind.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}