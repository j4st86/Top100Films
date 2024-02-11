package com.example.top100films

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.top100films.api.createKinopoiskApi
import com.example.top100films.api.createOkHttpClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val api by lazy { createKinopoiskApi(createOkHttpClient()) }

    private val _hasInternetConnection = MutableLiveData<Boolean>()
    val hasInternetConnection: LiveData<Boolean>
        get() = _hasInternetConnection

    init {
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetworkInfo
        _hasInternetConnection.value = activeNetwork?.isConnectedOrConnecting == true

    }

    fun getFilms() {
        GlobalScope.launch {
            try {
                api.getTop100Films("TOP_100_POPULAR_FILMS", 1)
            } catch (e: Exception) {
                println(e)
            }
        }
    }
}