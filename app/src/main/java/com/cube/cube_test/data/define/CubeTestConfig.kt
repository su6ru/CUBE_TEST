package com.cube.cube_test.data.define

import com.cube.cube_test.BuildConfig
/** Config */
class CubeTestConfig {

    class Extra{
        companion object{
            const val KEY_JSON : String = "KEY_JSON"
        }
    }
    class Api{
        companion object{
            //API BASE URL
            val BASE_URL = if (BuildConfig.DEBUG) "https://www.travel.taipei/open-api/" else "https://www.travel.taipei/open-api/"
            //遊憩景點API URL後輟
            const val ATTRACTIONS_URL = "/Attractions/All?"
            //最新消息 URL後輟
            const val NEWS_URL = "/Events/News?"

            //API文件  https://www.travel.taipei/open-api/swagger/ui/index#/Media/Media_Audio
            class Property{
                companion object{
                    //預設頁數
                    const val DEFAULT_PAGE = "1"

                }

            }

        }
    }



}