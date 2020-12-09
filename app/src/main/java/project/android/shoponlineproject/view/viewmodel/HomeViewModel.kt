package project.android.shoponlineproject.view.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import project.android.ecommers.Repositry.ApiService
import project.android.ecommers.Repositry.Repositry
import project.android.ecommers.model.Data_Main_Data



class HomeViewModel() : ViewModel() {
    var mutableLiveData=MutableLiveData<Data_Main_Data>()
    var mutableLiveData_home=MutableLiveData<Data_Main_Data>()
    var mutableLiveData_Movie=MutableLiveData<Data_Main_Data>()
    var mutableLiveData_Main=MutableLiveData<Data_Main_Data>()


    var _Context : Context?=null
    val com = CompositeDisposable()


    fun Get_List_Main(code:String):MutableLiveData<Data_Main_Data> {
        Repositry.CustomResponse.Request(ApiService.invoke().Show_Main_List(code), com) {




            mutableLiveData_Main.value=it





        }
        if(code.equals("859")){
            return Get_List_Main_bestSeller("859")
        }else if(code.equals("123")){
            return Get_List_Main_Movie("123")

        }else{
            return mutableLiveData_Main

        }



    }

    fun Get_List_Main_Movie(code:String):MutableLiveData<Data_Main_Data> {
        Repositry.CustomResponse.Request(ApiService.invoke().Show_Main_List(code), com) {

            mutableLiveData_Movie.value = it


        }
        return mutableLiveData_Movie

    }
    fun Get_List_Main_bestSeller(code:String):MutableLiveData<Data_Main_Data> {
        Repositry.CustomResponse.Request(ApiService.invoke().Show_Main_List(code), com) {

            mutableLiveData_home.value = it


        }
        return mutableLiveData_home

    }


    override fun onCleared() {
        com.clear()
        super.onCleared()
    }


}