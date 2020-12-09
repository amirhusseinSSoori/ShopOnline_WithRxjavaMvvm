package project.android.shoponlineproject.view.actvity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import design.android.navigationdrawer.util.SetIntent
import design.android.navigationdrawer.util.toast
import kotlinx.android.synthetic.main.activity_register.*
import project.android.ecommers.Repositry.App
import project.android.ecommers.Repositry.Factory
import project.android.ecommers.Repositry.Repositry
import project.android.ecommers.model.LoginModel
import project.android.shoponlineproject.R
import project.android.shoponlineproject.databinding.ActivityRegisterBinding
import project.android.shoponlineproject.view.viewmodel.InterFaceModel
import project.android.shoponlineproject.view.viewmodel.RegisterVieModel
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() ,InterFaceModel{

    var codecheck: Int = 1
    var bitmap: Bitmap? = null
    var Image_select: String? = null
    lateinit var viewmodel: RegisterVieModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        viewmodel= ViewModelProvider(this, Factory(App())).get(RegisterVieModel::class.java)
        var bind: ActivityRegisterBinding = DataBindingUtil.setContentView(this,R.layout.activity_register)
        viewmodel.interFaceModel=this
        viewmodel.context=this
        viewmodel.edit=edit_email
        viewmodel.text=txt_valid_email
        bind.viewmodel=viewmodel


        pic_image!!.setOnClickListener {Getgalley()}


    }
     fun Getgalley() {
         val intent = Intent()
         intent.type = "image/*"
         intent.action = Intent.ACTION_GET_CONTENT
         startActivityForResult(intent, codecheck)
     }
     fun Camparse() {
         var size = (bitmap!!.height * (812.0 / bitmap!!.width)).toInt()
         var b = Bitmap.createScaledBitmap(bitmap!!, 812, size, true)
         var by = ByteArrayOutputStream()
         b.compress(Bitmap.CompressFormat.JPEG, 100, by)
         var bytes: ByteArray = by.toByteArray()
         Image_select = Base64.encodeToString(bytes, 0)
         viewmodel.image = Image_select.toString()

     }

    override fun Error(msg: String) =toast(msg)

     override fun insertData(UserLogin: MutableLiveData<LoginModel>) {
         UserLogin.observe(this, Observer {


             if(it.status.equals("ok")){
                 Repositry.sharedpreferences.SetSharedUser(this, it.user_id.toString())

                 SetIntent(LoginActivity::class.java)


             }else{
                 toast("error")
             }

         })
     }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == codecheck && resultCode == Activity.RESULT_OK && data != null) {
            val uri = data.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri)
                setimageProfile!!.setImageBitmap(bitmap)
                Camparse()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

 }