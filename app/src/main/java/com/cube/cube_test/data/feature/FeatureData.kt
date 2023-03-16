package com.cube.cube_test.data.feature

open class FeatureData<TRequest, TResponse>  {
    var mRequest: TRequest? = null

    var mResponse: TResponse? = null
}
/*
class FeatureData<TRequest, TResponse> {
    var request: TRequest? = null
        private set

    fun setRequest(request: TRequest) {
        this.request = request
    }

    var response: TResponse? = null
        private set

    fun setResponse(response: TResponse) {
        this.response = response
    }

}*/