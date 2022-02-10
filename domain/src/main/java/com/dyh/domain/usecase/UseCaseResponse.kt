package com.dyh.domain.usecase

import com.dyh.domain.entity.ApiError

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}

