package com.cube.cube_test.data.feature

open class FeatureData<TRequest, TResponse> (mTRequest : TRequest,mTResponse : TResponse) {
    var mRequest: TRequest = mTRequest

    var mResponse: TResponse = mTResponse
}
