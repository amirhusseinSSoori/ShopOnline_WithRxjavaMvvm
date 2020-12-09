package project.android.shoponlineproject.view.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import project.android.shoponlineproject.model.Comment_List
import project.android.shoponlineproject.model.StatusComment

interface infaceComment {
    fun setCommentInterface(comment:MutableLiveData<StatusComment>)
}