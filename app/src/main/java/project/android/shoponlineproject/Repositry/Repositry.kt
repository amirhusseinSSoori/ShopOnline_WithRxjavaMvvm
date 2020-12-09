package project.android.ecommers.Repositry

import android.content.Context
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class Repositry {


    object CustomResponse {
        fun <T : Any> Request(api: Single<T>, com: CompositeDisposable, CallBack: (T) -> Unit) {
            com.add(
                api.subscribeOn(Schedulers.newThread())

                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribeWith(object : DisposableSingleObserver<T>() {
                        override fun onSuccess(t: T) {
                            CallBack.invoke(t)
                        }

                        override fun onError(e: Throwable) {

                        }

                    })
            )


        }


    }
    object sharedpreferences{
        fun SetSharedUser(
            context: Context,
            id:String){
            val Sharedpreferences=context.getSharedPreferences("user",0)
            val editor=Sharedpreferences.edit()
            editor.putString("user_id",id)
            editor.apply()

        }


        fun GetSharedUser(context: Context):String{
            val Sharedpreferences=context.getSharedPreferences("user",0)
            val userid =Sharedpreferences.getString("user_id",null)
            return userid.toString()

        }


    }


}

