package com.cube.cube_test.data.define

import com.cube.cube_test.BuildConfig

class CubeTestConfig {

    class Extra{
        companion object{
            const val KEY_JSON : String = "KEY_JSON"
        }
    }
    class Api{
        companion object{
            val URL = if (BuildConfig.DEBUG) "https://www.travel.taipei/open-api" else "https://www.travel.taipei/open-api"


        }
    }



}