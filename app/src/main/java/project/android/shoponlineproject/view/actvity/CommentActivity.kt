package project.android.shoponlineproject.view.actvity

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import design.android.navigationdrawer.util.SetIntent
import design.android.navigationdrawer.util.toast
import kotlinx.android.synthetic.main.activity_comment.*
import project.android.ecommers.Repositry.App
import project.android.ecommers.Repositry.Factory
import project.android.ecommers.Repositry.Repositry
import project.android.shoponlineproject.R
import project.android.shoponlineproject.databinding.ActivityCommentBinding
import project.android.shoponlineproject.databinding.ActivityCommentBindingImpl
import project.android.shoponlineproject.databinding.ActivityLoginBinding
import project.android.shoponlineproject.model.Comment
import project.android.shoponlineproject.model.StatusComment
import project.android.shoponlineproject.util.SaveDate
import project.android.shoponlineproject.view.Adabter.Adabter_Comment
import project.android.shoponlineproject.view.viewmodel.CommentViewModel
import project.android.shoponlineproject.view.viewmodel.infaceComment
import java.util.*
import kotlin.collections.ArrayList

class CommentActivity : AppCompatActivity(),infaceComment {

    lateinit var _CommentViewModel: CommentViewModel
    var adabter:Adabter_Comment?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        _CommentViewModel =
            ViewModelProvider(this, Factory(App())).get(CommentViewModel::class.java)
        val bind: ActivityCommentBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_comment)
        _CommentViewModel._Context=this
        _CommentViewModel.id=intent.getStringExtra("id")
        _CommentViewModel.setInterface=this
        bind.data=_CommentViewModel
        showComment()



    }


    fun showComment() {
        _CommentViewModel.showlistComment(intent.getStringExtra("id")).observe(this, Observer { list ->

            recyclerViewComment.also {

                it.layoutManager = LinearLayoutManager(this@CommentActivity)
//                var revers=list.listComment;
//                Collections.reverse(revers)
                adabter = Adabter_Comment(list.listComment,this)
//                ListComment=list.listComment

                it.adapter = adabter




            }


        })
    }


    fun refreshPage(it:ArrayList<Comment>) {
      


        adabter!!.notifyDataSetChanged()
    }

    override fun setCommentInterface(comment: MutableLiveData<StatusComment>) {
        comment.observe(this, Observer {list->



            toast(list.status.toString())



           adabter!!.notifyDataSetChanged()





        })
    }
}