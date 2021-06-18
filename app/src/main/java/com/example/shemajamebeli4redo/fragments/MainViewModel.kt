package com.example.shemajamebeli5.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shemajamebeli4redo.models.Action
import com.example.shemajamebeli4redo.models.Match
import com.example.shualeduri.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class MainViewModel : ViewModel() {

    private val matchInfo = MutableLiveData<Match>().apply {
        mutableListOf<Match>()
    }
    val _matchInfo: LiveData<Match> = matchInfo

    private val actionsInfo = MutableLiveData<Match>().apply {
        mutableListOf<Match>()
    }
    val _actionsInfo: LiveData<Match> = matchInfo


    fun init() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                try {
                    val result = RetrofitInstance.api.getPost()
                    if (result.isSuccessful) {
                        val item = result.body()
                        matchInfo.postValue(item)
                    }
                } catch (e: SocketTimeoutException) {
                    Log.d("timeoutEx", "${e.message}")
                }
            }
        }
    }

}