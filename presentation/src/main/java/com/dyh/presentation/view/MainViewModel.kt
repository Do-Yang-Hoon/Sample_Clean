package com.dyh.presentation.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dyh.domain.entity.ApiError
import com.dyh.domain.entity.Post
import com.dyh.domain.usecase.GetPostsUseCase
import com.dyh.domain.usecase.UseCaseResponse
import com.dyh.presentation.view.base.BaseViewModel
import kotlinx.coroutines.cancel


class MainViewModel constructor(private val getPostsUseCase: GetPostsUseCase) : BaseViewModel() {

    val postsData = MutableLiveData<List<Post>>()
    val messageData = MutableLiveData<String>()

    fun getPosts() {
        setLoading(true)
        getPostsUseCase.invoke(viewModelScope, null, object : UseCaseResponse<List<Post>> {
                override fun onSuccess(result: List<Post>) {
                    postsData.value = result
                    setLoading(false)
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    setLoading(false)
                }
            },
        )
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}