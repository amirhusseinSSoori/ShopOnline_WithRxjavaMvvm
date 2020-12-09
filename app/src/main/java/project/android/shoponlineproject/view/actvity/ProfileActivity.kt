package project.android.shoponlineproject.view.actvity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import design.android.navigationdrawer.util.SetIntent
import design.android.navigationdrawer.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_profile.*
import project.android.ecommers.Repositry.App
import project.android.ecommers.Repositry.Factory
import project.android.ecommers.Repositry.Repositry
import project.android.ecommers.model.ProfileModel
import project.android.shoponlineproject.R
import project.android.shoponlineproject.databinding.ActivityProfileBinding
import project.android.shoponlineproject.util.SaveDate
import project.android.shoponlineproject.util.LogoutLogin
import project.android.shoponlineproject.view.viewmodel.ProfileViewModel


class ProfileActivity : AppCompatActivity() {

    lateinit var profileViewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        val bind: ActivityProfileBinding =
            DataBindingUtil.setContentView(this,
                R.layout.activity_profile
            )
        profileViewModel =
            ViewModelProvider(this, Factory(App())).get(ProfileViewModel::class.java)

        var id = Repositry.sharedpreferences.GetSharedUser(this)
        profileViewModel.profile(id).observe(this, Observer {

            bind.data = ProfileModel(it.email, it.mobile, it.name, it.Image)
            Glide.with(imageProfile).load(it.Image).into(imageProfile)
            Glide.with(imageProfile).load(it.Image).into(imageBack)

            SaveDate.SetSharedUser(this, it.name, it.mobile, it.email,it.Image)


        })


        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        set_Logout()
    }


    fun set_Logout(){
        logout.setOnClickListener {
            SaveDate.SetSharedUser(this,"","","","")

            SetIntent(MainActivity::class.java)
            LogoutLogin.SetSharedUser(this,"login")
            Repositry.sharedpreferences.SetSharedUser(this,"null")
            finish()
            finishAffinity()
        }
    }

    override fun onBackPressed() {
      SetIntent(MainActivity::class.java)
        finish()
        finishAffinity()
        super.onBackPressed()
    }
}