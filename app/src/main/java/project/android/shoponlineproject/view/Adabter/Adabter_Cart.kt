package project.android.shoponlineproject.view.Adabter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import design.android.navigationdrawer.util.SetIntent
import io.reactivex.disposables.CompositeDisposable
import project.android.ecommers.Repositry.ApiService
import project.android.ecommers.Repositry.Repositry
import project.android.shoponlineproject.R
import project.android.shoponlineproject.databinding.ItemsCartBinding
import project.android.shoponlineproject.model.Buy
import project.android.shoponlineproject.view.actvity.MainActivity

class Adabter_Cart(val _Context:Context, var list: ArrayList<Buy>, var change: GetChangeItems, var user: String) :
    RecyclerView.Adapter<Adabter_Cart.Holder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.items_cart,
            parent,
            false
        )
    )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var data = list.get(position)
        holder.item.data = data
        holder.item.TvPrice.text = data.cost + " تومان "
        holder.item.TvCount.text = " تعداد " + data.count
        var count = data.count.toString()
        holder.item.TvTitle.text = data.name

        Glide.with(_Context).load(data.image).into(holder.item.ImPost)
        holder.item.ImMosbat.setOnClickListener {
            val com = CompositeDisposable()
            Repositry.CustomResponse.Request(
                ApiService.invoke().ADD_Cart(data.idproduct, user, "1", "add"), com
            ) {
                if (it.status.equals("ok")) {
                    holder.item.TvPrice.text = it.cost_post.toString() + " تومان "
                    holder.item.TvCount.text = " تعداد " + it.count.toString()


                    change.GetChnage(0)
                }
            }


        }
        holder.item.ImMines.setOnClickListener {
            val com = CompositeDisposable()
            Repositry.CustomResponse.Request(
                ApiService.invoke().ADD_Cart(data.idproduct, user, "1", "m"), com
            ) {
                if (it.status.equals("ok")) {
                    holder.item.TvPrice.text = it.cost_post.toString() + " تومان "
                    holder.item.TvCount.text = " تعداد " + it.count.toString()


                    change.GetChnage(0)
                }
            }


        }
        holder.item.BtnDel.setOnClickListener {
            val Com = CompositeDisposable()
            Repositry.CustomResponse.Request(ApiService.invoke().Del_Cart(data.id),Com){
                if(it.status.equals("ok")){

                    list.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position,list.size)
                    change.GetChnage(1)
                    listBeZero()
                }
            }

        }
    }


    class Holder(var item: ItemsCartBinding) : RecyclerView.ViewHolder(item.root)

    interface GetChangeItems {
        fun GetChnage(p:Int)
    }

    fun listBeZero(){
        if(list.size==0){
            _Context.SetIntent(MainActivity::class.java)
        }
    }


}