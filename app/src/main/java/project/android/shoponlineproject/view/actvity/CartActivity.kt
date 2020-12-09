package project.android.shoponlineproject.view.actvity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.activity_detail.*
import project.android.ecommers.Repositry.App
import project.android.ecommers.Repositry.Factory
import project.android.ecommers.Repositry.Repositry
import project.android.shoponlineproject.R
import project.android.shoponlineproject.view.Adabter.Adabter_Cart
import project.android.shoponlineproject.view.viewmodel.CartViewModel

class CartActivity : AppCompatActivity() {

    lateinit var viewModel :CartViewModel
    var bchange : Boolean?=false
    lateinit var user:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
      user= Repositry.sharedpreferences.GetSharedUser(this)
        viewModel= ViewModelProvider(this, Factory(App())).get(CartViewModel::class.java)




        show_Price(user)
        viewModel.showGetCart(user).observe(this, Observer {itCart->
            recyclerview_shop.also {
                it.layoutManager=LinearLayoutManager(this)


                var adapter=Adabter_Cart(this,itCart.Buy,object: Adabter_Cart.GetChangeItems{
                    override fun GetChnage(p:Int) {
                        if(p==1){
                            bchange=true
                        }


                        show_Price(user)
                    }

                },user)


                it.adapter=adapter


            }



        })

    }


    fun show_Price(id:String){
        viewModel.showPrice(id).observe(this, Observer {

            if(!it.cost.isNullOrEmpty()){
                Tv_price_all.text=it.cost+ " تومان ".toString()
            }else{
                Tv_change.text="محصولی موجود نیست-افزودن محصول"
                txt_cost.text= "0 ریال "
                Tv_price_all.visibility= View.GONE
            }
        })




    }
}