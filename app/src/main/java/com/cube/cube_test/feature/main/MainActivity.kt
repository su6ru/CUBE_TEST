package com.cube.cube_test.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cube.cube_test.R
import com.cube.cube_test.custom.activity.CubeTestActivity
import com.cube.cube_test.data.feature.FeatureData
/** 首頁 */
class MainActivity : CubeTestActivity<MainActivity.Data.Request>() {
    // MARK:- ===================== Define

    // MARK:- ===================== Constructor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    // MARK:- ====================== View

    // MARK:- ====================== Data

    // MARK:- ====================== Event

    // MARK:- ====================== Method



    // MARK:- ====================== Class Data
    class Data : FeatureData<Data.Request,Data.Response>(){
        init {
            Request()
            Response()
        }
        class Request(){

        }
        class Response(){

        }
    }
}