package project.android.ecommers.view.Adabter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import design.android.navigationdrawer.util.SetIntent
import kotlinx.android.synthetic.main.user_item_view.view.*
import project.android.shoponlineproject.R
import project.android.shoponlineproject.databinding.UserItemViewBinding
import project.android.shoponlineproject.model.Detail
import project.android.shoponlineproject.util.ClickIntent
import project.android.shoponlineproject.util.FaNum
import project.android.shoponlineproject.view.actvity.SongActivity


class AdabterMain(
    var list: List<Detail>, var click: ClickIntent,
    var _Context:Context) : RecyclerView.Adapter<AdabterMain.Holder>() {





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {


        return Holder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.user_item_view,
                parent,
                false
            )
        )


    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.item.detail = list.get(position)
        var data=holder.item.detail
        holder.itemView.off.text=" تخفیف "+ FaNum.convert("10")+" درصد "

        holder.itemView.setOnClickListener {


            var Cast=data!!.code.equals("123")
            if(Cast){
                var intent= Intent(_Context,SongActivity::class.java)
                intent.putExtra("name",data!!.name)
                intent.putExtra("image",data!!.image)
                intent.putExtra("color",data!!.color)
                _Context!!.startActivity(intent)

            }else {
                click.ClickItem(data!!.id)
            }



        }





    }




    class Holder(var item: UserItemViewBinding) : RecyclerView.ViewHolder(item.root)
}