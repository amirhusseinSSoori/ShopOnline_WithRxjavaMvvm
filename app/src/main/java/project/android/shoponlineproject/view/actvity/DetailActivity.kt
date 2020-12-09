package project.android.shoponlineproject.view.actvity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import design.android.navigationdrawer.util.SetIntent
import design.android.navigationdrawer.util.SetIntentwithoneextrastring
import design.android.navigationdrawer.util.toast
import io.reactivex.disposables.CompositeDisposable

import kotlinx.android.synthetic.main.activity_detail.*
import project.android.ecommers.Repositry.ApiService
import project.android.ecommers.Repositry.App
import project.android.ecommers.Repositry.Factory
import project.android.ecommers.Repositry.Repositry
import project.android.ecommers.model.Post
import project.android.shoponlineproject.R
import project.android.shoponlineproject.databinding.ActivityDetailBinding
import project.android.shoponlineproject.util.LikeHeart
import project.android.shoponlineproject.view.Adabter.AdabterSlider
import project.android.shoponlineproject.view.viewmodel.DetailsViewModel

class DetailActivity : AppCompatActivity() {
    lateinit var viemodeldeatil: DetailsViewModel
    var id:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var bind: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)
        viemodeldeatil = ViewModelProvider(this, Factory(App())).get(DetailsViewModel::class.java)
        var red:Int?=null


        var userlike=LikeHeart.GetShared_like(this)
        btn_like.setOnClickListener {





            if(red==null){

                red=R.drawable.ic_like_red
            }else{
                btn_like.setBackgroundResource(R.drawable.ic_like_red)
                red=null

            }


        }

         id=intent.getStringExtra("id")

        viemodeldeatil.setViewModelDetail(id!!).observe(this, Observer {

            var post = Post(
                it.post[0].code,
                it.post[0].color,
                it.post[0].cost,
                it.post[0].description,
                it.post[0].id,
                it.post[0].image,
                it.post[0].name
            )
            bind.detail = post
            Btn_cartadd.text = " افزودن ب سبد خرید" + post.cost + "  تومان   "
            setUpViewPager(it.slider)


        })


        val user = Repositry.sharedpreferences.GetSharedUser(this)


        Btn_cartadd.setOnClickListener {
            if (user.equals("null")) {
                SetIntent(LoginActivity::class.java)
            } else {
                val com = CompositeDisposable()
                Repositry.CustomResponse.Request(
                    ApiService.invoke().ADD_Cart(intent.getStringExtra("id"), user, "1", "add"), com
                ) {
                    if (it.status.equals("ok")) {
                        SetIntent(CartActivity::class.java)
                    }
                }
            }

        }









        btn_two.setOnClickListener {

            SetIntentwithoneextrastring(CommentActivity::class.java,"id",id!!)
        }


    }

    fun setUpViewPager(list: List<project.android.ecommers.model.Slider>) {
        val adapter = AdabterSlider(list)
        viewpager_slider.adapter = adapter
        viewpager_slider.pageMargin = 25
//        viewpager_slider.setPadding(45,0,15,0)
        viewpager_slider.currentItem = 1
        indicator.setViewPager(viewpager_slider)
    }

}