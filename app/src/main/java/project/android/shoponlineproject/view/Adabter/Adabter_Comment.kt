package project.android.shoponlineproject.view.Adabter

import android.content.Context
import android.database.DatabaseUtils
import android.graphics.Color
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View.inflate
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.items_comments.view.*
import project.android.shoponlineproject.R
import project.android.shoponlineproject.databinding.ActivityDetailBinding.inflate
import project.android.shoponlineproject.databinding.ItemsCommentsBinding
import project.android.shoponlineproject.model.Comment
import project.android.shoponlineproject.util.SaveDate

class Adabter_Comment (var List: MutableList<Comment>, var _Context:Context):RecyclerView.Adapter<Adabter_Comment.Holder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(      DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.items_comments,
            parent,
            false
        ))
    }

    override fun getItemCount(): Int =List.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.item.comment=List.get(position)

        var data=List.get(position)

        var name=holder.item.txtName.text
        name=data.name.toString()

        var username:String=SaveDate.GetShared_name(_Context).toString()


        if(name.equals(username)){

            holder.itemView.Linear_Comment_one.setBackground(ContextCompat.getDrawable(_Context, R.drawable.backgroundcommentuser))
            holder.itemView.Linear_Comment_two.setBackgroundColor(Color.parseColor("#66003ED3"))
            holder.itemView.correct.background=ContextCompat.getDrawable(_Context, R.drawable.correctchange)

        }

}

    class Holder(var item:ItemsCommentsBinding):RecyclerView.ViewHolder(item.root)




}