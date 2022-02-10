package com.dyh.presentation.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dyh.presentation.utils.Event
import com.orhanobut.logger.BuildConfig
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

abstract class BaseViewModel : ViewModel() {

    // Loading
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun setLoading(isLoading: Boolean) = _isLoading.postValue(isLoading)
}