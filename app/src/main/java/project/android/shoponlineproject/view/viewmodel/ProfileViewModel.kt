package project.android.shoponlineproject.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import project.android.ecommers.Repositry.ApiService
import project.android.ecommers.Repositry.Repositry
import project.android.ecommers.model.ProfileModel

class ProfileViewModel:ViewModel() {
    var com = CompositeDisposable()
    var mutableLiveData = MutableLiveData<ProfileModel>()

    //
    fun profile(id:String): MutableLiveData<ProfileModel> {
        Repositry.CustomResponse.Request(ApiService.invoke().GetUserInfo(id), com) {

            mutableLiveData.value = it


        }

        return mutableLiveData
    }


    override fun onCleared() {

        com.clear()
        super.onCleared()
    }
}