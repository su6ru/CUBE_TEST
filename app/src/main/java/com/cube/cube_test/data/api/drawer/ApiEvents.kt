package com.cube.cube_test.data.api.drawer

import com.cube.cube_test.data.api.data.BaseData
import com.cube.cube_test.data.detail.NewsDetail

/** 活動相關API資料 */
class ApiEvents {
    public class GetNews{
        public class Response : BaseData.Response<MutableList<NewsDetail>>() {

        }
    }
}