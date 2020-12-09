package project.android.shoponlineproject.view.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import project.android.ecommers.model.LoginModel


interface InterFaceModel {

    fun Error(msg:String)
    fun insertData(UserLogin:MutableLiveData<LoginModel>)




}