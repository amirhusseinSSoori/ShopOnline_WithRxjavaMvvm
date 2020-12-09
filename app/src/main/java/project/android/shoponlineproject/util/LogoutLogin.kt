package project.android.shoponlineproject.util

import android.content.Context

object LogoutLogin{
    fun SetSharedUser(
        context: Context,
        name:String){
        val Sharedpreferences=context.getSharedPreferences("user",0)
        val editor=Sharedpreferences.edit()
        editor.putString("reza",name)

        editor.apply()

    }


    fun GetShared_name(context: Context):String{
        val Sharedpreferences=context.getSharedPreferences("user",0)
        val name =Sharedpreferences.getString("reza",null)
        return name.toString()

    }

    }

