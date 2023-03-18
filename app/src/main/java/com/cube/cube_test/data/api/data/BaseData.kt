package com.cube.cube_test.data.api.data

import com.google.gson.annotations.SerializedName

open class BaseData {

    open class Request{

    }

    open class Response<JObject>{
        /** 資料比數 */
        @SerializedName("total")
        public var mTotal: Int = 0
        @SerializedName("data")
        public var mData: JObject? = null
    }
}