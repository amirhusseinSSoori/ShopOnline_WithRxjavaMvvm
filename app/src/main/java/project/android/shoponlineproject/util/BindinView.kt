package project.android.shoponlineproject.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StrikethroughSpan
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


object BindinView {


    @JvmStatic
    @BindingAdapter("image")
    fun GetImage(view: ImageView, uri: String) {


        Glide.with(view)
            .load(uri)

            .into(view);

    }

    @JvmStatic
    @BindingAdapter("Number")
    fun getPersianNumber(view: TextView, uri: String){
        var num=uri
        FaNum.convert(num)
        var spannableString= SpannableString(num)
        spannableString.setSpan(StrikethroughSpan(),0,num.length, Spanned.SPAN_MARK_MARK)
        view.text=  " "  + FaNum.convert(num) +" تومان  "
    }
    @JvmStatic
    @BindingAdapter("card")
    fun loadColor(view: Button, uri: String) {

        var c1 = color1(uri)
        var c2 = color2(uri)
//        greadient(view, c1, c2)
        view.setBackgroundColor(Color.parseColor(c1))


    }

    @JvmStatic
    @BindingAdapter("slider")
    fun GetSlider(view: ImageView, uri: String) {
        Glide.with(view).load(uri).into(view)

    }


    @JvmStatic
    @BindingAdapter("setColor")
    fun GetColor(view: View,uri:String) {
        greadient(view, color1(uri), color2(uri))

    }


///////////////////////////////////


    ///////////
    fun color1(color: String): String {

        return color.substring(0, 7)

    }

    fun color2(color: String): String {
        return color.substring(8)

    }


    fun greadient(view: View, color1: String, color2: String) {

        var colors = intArrayOf(Color.parseColor(color1), Color.parseColor(color2))

        var gradient = GradientDrawable(GradientDrawable.Orientation.BL_TR, colors)
        gradient.cornerRadius = 0f
        view.setBackground(gradient)


    }
}