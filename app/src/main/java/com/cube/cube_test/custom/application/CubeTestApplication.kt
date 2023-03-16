package com.cube.cube_test.custom.application

import android.app.Application
import com.cube.cube_test.manager.ApiManager
import com.cube.cube_test.manager.CubeTestManager
import com.cube.cube_test.manager.DbManager
/** 此專案的Application  */

class CubeTestApplication : Application() {
    // MARK:- ====================== Define
    companion object{
        private lateinit var mCubeApplication : CubeTestApplication
        fun instance():CubeTestApplication{
            return mCubeApplication
        }
    }
    // MARK:- ====================== Constructor
    override fun onCreate() {
        super.onCreate()
        mCubeApplication =  this
    }
    // MARK:- ====================== Data
    /** CubeManager */
    var mCubeTestManager = CubeTestManager(this)
    /** ApiManager */
    var mApiManager = ApiManager(this)
    /** DbManager */
    var mDbManager = DbManager(this)
}