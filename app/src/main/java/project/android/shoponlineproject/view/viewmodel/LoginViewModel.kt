package project.android.shoponlineproject.view.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import design.android.navigationdrawer.util.SetIntent
import io.reactivex.disposables.CompositeDisposable
import project.android.ecommers.Repositry.ApiService
import project.android.ecommers.Repositry.Repositry.*
import project.android.ecommers.model.LoginModel
import project.android.shoponlineproject.view.actvity.RegisterActivity


class LoginViewModel() : ViewModel() {


    var mobile: String? = null
    var pass: String? = null
    var interFaceModel: InterFaceModel? = null
    var context: Context? = null
    val Com = CompositeDisposable()


    fun setIntent(view: View) {
        context!!.SetIntent(RegisterActivity::class.java)
    }


    fun GetLogin(view: View) {
//
        if (!mobile.isNullOrEmpty()) {
            if (!pass.isNullOrEmpty()) {

                interFaceModel?.insertData(setData(mobile!!,pass!!))
            } else {
                interFaceModel?.Error("Enter your password")
            }


        } else {
            if (!pass.isNullOrEmpty()) {
                interFaceModel?.Error("Enter Mobile")
            } else {
                interFaceModel?.Error("Enter Mobile & password")
            }

        }


    }

    fun setData(mobile:String,password:String): MutableLiveData<LoginModel> {
        var insert = MutableLiveData<LoginModel>()
        CustomResponse.Request(
            ApiService.invoke().Get_Login(mobile!!, password!!),
            Com
        ) {
            insert.value = it

        }


        return insert
    }


    override fun onCleared() {
        Com.clear()
        super.onCleared()
    }

}


