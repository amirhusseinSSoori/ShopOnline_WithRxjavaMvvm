package project.android.shoponlineproject.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import project.android.ecommers.Repositry.ApiService
import project.android.ecommers.Repositry.Repositry
import project.android.ecommers.model.ModelDetails

class DetailsViewModel : ViewModel() {

    var mutableLiveData = MutableLiveData<ModelDetails>()

    var com = CompositeDisposable()

    var id: String? = null



    fun setViewModelDetail(id: String): MutableLiveData<ModelDetails> {


        Repositry.CustomResponse.Request(ApiService.invoke().Get_Details(id), com) {

            mutableLiveData.value = it
        }

        return mutableLiveData
    }





    override fun onCleared() {

        com.clear()
        super.onCleared()
    }

}