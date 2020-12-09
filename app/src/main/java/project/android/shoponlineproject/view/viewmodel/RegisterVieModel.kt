package project.android.shoponlineproject.view.viewmodel

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import project.android.ecommers.Repositry.ApiService
import project.android.ecommers.Repositry.Repositry
import project.android.ecommers.model.LoginModel
import project.android.shoponlineproject.R
import java.util.regex.Pattern

class RegisterVieModel:ViewModel() {
    var name:String?=null
    var mobile:String?=null
    var email:String?=null
    var image:String?=null

    var password:String?=null
    var context: Context?=null
    var interFaceModel:InterFaceModel?=null
    var edit:EditText?=null
    var text:TextView?=null


    var com= CompositeDisposable()



    fun GetRegister(view: View){


        if(name.isNullOrEmpty()){
            interFaceModel?.Error("please Enter name")

        }else if(mobile.isNullOrEmpty()){
            interFaceModel?.Error("please Enter mobile")

        }else if(email.isNullOrEmpty()){
            interFaceModel?.Error("please Enter email")

        }else if(image.isNullOrEmpty()){
            interFaceModel?.Error("please set picture")
        }else if(password.isNullOrEmpty()){

            interFaceModel?.Error("please Enter password")

        }else if(!isValidEmailId(email.toString().trim())){

            interFaceModel?.Error("InValid Email Address")
            edit!!.background= ContextCompat.getDrawable(context!!, R.drawable.edit_change_ground)
            text!!.visibility=View.VISIBLE

        }else{
            interFaceModel?.insertData(setData())
//              interFaceModel?.Error("Susses")
            text!!.visibility=View.GONE
        }


    }

    private fun isValidEmailId(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }
    fun setData(): MutableLiveData<LoginModel> {

        var mutaLiveData: MutableLiveData<LoginModel>
        mutaLiveData= MutableLiveData<LoginModel>()
        Repositry.CustomResponse.Request(ApiService.invoke().GetRegister(name!!,mobile!!,email!!,password!!,image!!,name!!),com){


            mutaLiveData.value=it



        }
        return mutaLiveData





    }

    override fun onCleared() {
        com.clear()
        super.onCleared()
    }
}