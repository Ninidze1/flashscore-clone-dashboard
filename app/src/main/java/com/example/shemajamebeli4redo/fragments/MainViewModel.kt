package com.example.shemajamebeli5.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.HttpException
import com.example.shemajamebeli4redo.models.Action
import com.example.shemajamebeli4redo.models.Match
import com.example.shemajamebeli4redo.models.ResultHandle
import com.example.shualeduri.api.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.SocketTimeoutException

class MainViewModel : ViewModel() {

    private val matchInfo = MutableLiveData<ResultHandle<Match>>().apply {
        mutableListOf<ResultHandle<Match>>()
    }
    val _matchInfo: LiveData<ResultHandle<Match>> = matchInfo

    fun init() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                try {
                    val result = RetrofitInstance.api.getPost()
                    if (result.isSuccessful) {
                        val item = result.body()
                        matchInfo.postValue(ResultHandle.success(item))
                    } else {
                        matchInfo.postValue(ResultHandle.error(result.message()))
                    }
                } catch (e: HttpException) {
                    Log.d("timeoutEx", "${e.message}")
                }
            }
        }
    }

}