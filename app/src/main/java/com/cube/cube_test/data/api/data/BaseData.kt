package com.cube.cube_test.data.api.data

import com.google.gson.annotations.SerializedName

open class BaseData {

    open class Request{

    }

    open class Response<JObject>{
        @SerializedName("total")
        private var mTotal: Int? = null
        @SerializedName("data")
        private var mData: JObject? = null
    }
}