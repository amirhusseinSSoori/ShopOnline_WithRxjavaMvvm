package project.android.shoponlineproject.util

import android.content.Context

object SaveDate{
    fun SetSharedUser(
        context: Context,
        name:String,mobile:String,email:String,image:String){
        val Sharedpreferences=context.getSharedPreferences("user",0)
        val editor=Sharedpreferences.edit()
        editor.putString("name",name)
        editor.putString("mobile",mobile)
        editor.putString("email",email)
        editor.putString("Image",image)
        editor.apply()

    }


    fun GetShared_name(context: Context):String{
        val Sharedpreferences=context.getSharedPreferences("user",0)
        val name =Sharedpreferences.getString("name",null)
        return name.toString()

    }
    fun GetShared_mobile(context: Context):String{
        val Sharedpreferences=context.getSharedPreferences("user",0)
        val mobile =Sharedpreferences.getString("mobile",null)
        return mobile.toString()

    }
    fun GetShared_email(context: Context):String{
        val Sharedpreferences=context.getSharedPreferences("user",0)
        val email =Sharedpreferences.getString("email",null)
        return email.toString()

    }
    fun GetShared_Image(context: Context):String{
        val Sharedpreferences=context.getSharedPreferences("user",0)
        val image =Sharedpreferences.getString("Image",null)
        return image.toString()

    }

}