package project.android.shoponlineproject.view.actvity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import design.android.navigationdrawer.util.SetIntent
import design.android.navigationdrawer.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import project.android.ecommers.Repositry.App
import project.android.ecommers.Repositry.Factory
import project.android.ecommers.Repositry.Repositry
import project.android.ecommers.model.LoginModel
import project.android.shoponlineproject.R
import project.android.shoponlineproject.databinding.ActivityLoginBinding
import project.android.shoponlineproject.util.LogoutLogin
import project.android.shoponlineproject.view.viewmodel.InterFaceModel
import project.android.shoponlineproject.view.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() ,InterFaceModel{

    var name:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        name=LogoutLogin.GetShared_name(this)

        var viewmodel = ViewModelProvider(this, Factory(App())).get(LoginViewModel::class.java)
        var bind: ActivityLoginBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_login)
        bind.viewmodel = viewmodel
        viewmodel.interFaceModel = this
        viewmodel.context = this









    }
    fun setIntent(){
        if(name.equals("login")) {
            SetIntent(ProfileActivity::class.java)
            LogoutLogin.SetSharedUser(this, "profile")

        }
        else{
            SetIntent(ProfileActivity::class.java)
            LogoutLogin.SetSharedUser(this,"profile")
        }
    }


    override fun Error(msg: String) = toast(msg)
    override fun  insertData(UserLogin: MutableLiveData<LoginModel>) {
        UserLogin.observe(this, Observer {
            toast(it.status.toString())
            if(it.status.equals("ok")){
                Repositry.sharedpreferences.SetSharedUser(this, it.user_id.toString())
               setIntent()
                finish()
                finishAffinity()





            }else{

            }

        })
    }

    override fun onBackPressed() {
        SetIntent(MainActivity::class.java)
        LogoutLogin.SetSharedUser(this,"login")
        finish()
        finishAffinity()
        super.onBackPressed()
    }
}