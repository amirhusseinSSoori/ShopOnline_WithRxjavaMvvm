package project.android.shoponlineproject.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import project.android.ecommers.Repositry.ApiService
import project.android.ecommers.Repositry.Repositry

import project.android.shoponlineproject.model.Cost
import project.android.shoponlineproject.model.Model_Cart

class CartViewModel():ViewModel() {


    var com=CompositeDisposable()
    var mutableLiveData=MutableLiveData<Cost>()
    var mutableLiveData_Cart=MutableLiveData<Model_Cart>()


    fun showGetCart(id: String):MutableLiveData<Model_Cart>{
        Repositry.CustomResponse.Request(ApiService.invoke().Get_Cart(id),com){


            mutableLiveData_Cart.value=it
        }
        return  mutableLiveData_Cart

    }


    fun showPrice(id:String):MutableLiveData<Cost>{

        Repositry.CustomResponse.Request(ApiService.invoke().Get_priceCart(id),com){


            mutableLiveData.value=it
        }
        return  mutableLiveData
    }


    override fun onCleared() {

        com.clear()
        super.onCleared()
    }
}