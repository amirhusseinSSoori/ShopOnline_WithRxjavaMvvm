package project.android.shoponlineproject.util

import android.content.Context

object LikeHeart {


    fun SetSharedUser(
        context: Context,
        like:String){
        val Sharedpreferences=context.getSharedPreferences("user",0)
        val editor=Sharedpreferences.edit()
        editor.putString("like",like)

        editor.apply()

    }
    fun GetShared_like(context: Context):String{
        val Sharedpreferences=context.getSharedPreferences("user",0)
        val like =Sharedpreferences.getString("like",null)
        return like.toString()

    }
}