package project.android.shoponlineproject.view.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import design.android.navigationdrawer.util.SetIntent
import design.android.navigationdrawer.util.toast
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_comment.*
import project.android.ecommers.Repositry.ApiService
import project.android.ecommers.Repositry.Repositry
import project.android.shoponlineproject.model.Comment_List
import project.android.shoponlineproject.model.StatusComment
import project.android.shoponlineproject.util.SaveDate
import project.android.shoponlineproject.view.actvity.LoginActivity

class CommentViewModel: ViewModel() {


    var com= CompositeDisposable()
    var mutableLiveData= MutableLiveData<Comment_List>()
    var mutableLiveDatastatus= MutableLiveData<StatusComment>()
    var setInterface:infaceComment?=null
    var EnterComment:String?=null
    var _Context:Context?=null
    var id:String?=null


     fun setComment(view:View){
         val user = Repositry.sharedpreferences.GetSharedUser(_Context!!)
         val username= SaveDate.GetShared_name(_Context!!)

         if(username.equals("null")){
             _Context!!.SetIntent(LoginActivity::class.java)
         }else{
             if (EnterComment.isNullOrEmpty()) {
                 _Context!!.toast("please Enter Something")

             } else {
                 setInterface!!.setCommentInterface(SetlistComment(user,username,EnterComment!!,id!!))



//
             }
         }

     }




    fun showlistComment(idProduct:String): MutableLiveData<Comment_List> {

        Repositry.CustomResponse.Request(ApiService.invoke().Get_Comment(idProduct),com){


            mutableLiveData.value=it
        }
        return  mutableLiveData
    }

    fun SetlistComment(user:String,username:String,comment:String,idproduct:String): MutableLiveData<StatusComment> {

        Repositry.CustomResponse.Request(ApiService.invoke().Add_Comment(user,username,comment,idproduct),com){


            mutableLiveDatastatus.value=it
        }
        return  mutableLiveDatastatus
    }






    override fun onCleared() {

        com.clear()
        super.onCleared()
    }
}