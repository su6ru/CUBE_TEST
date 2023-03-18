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
            //API URL
            val URL = if (BuildConfig.DEBUG) "https://www.travel.taipei/open-api/" else "https://www.travel.taipei/open-api/"

            //API文件  https://www.travel.taipei/open-api/swagger/ui/index#/Media/Media_Audio
            class Property{
                companion object{
                    //預設頁數
                    val DEFAULT_PAGE = "1"
                    //每次呼叫API 取得幾筆資料
                    val DATA_COUNT = 30
                }

            }

        }
    }



}