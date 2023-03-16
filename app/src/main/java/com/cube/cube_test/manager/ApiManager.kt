package com.cube.cube_test.manager

import android.content.Context
import com.cube.cube_test.data.define.CubeTestConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ApiManager(context: Context) {

    init {

    }

    private val mIApiService = Retrofit.Builder()
        .baseUrl(CubeTestConfig.Api.URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(IApiService ::class.java)

    interface IApiService {

    }
}