package project.android.ecommers.Repositry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Factory(app:App):ViewModelProvider.AndroidViewModelFactory (app){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return super.create(modelClass)
    }
}