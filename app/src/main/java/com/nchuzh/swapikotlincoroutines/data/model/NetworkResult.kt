package com.nchuzh.swapikotlincoroutines.data.model

sealed class NetworkResult<T>
class Fail<T>(val exception: Throwable) : NetworkResult<T>()
class Success<T>(val data: T) : NetworkResult<T>()