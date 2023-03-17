package com.cube.cube_test.data.api.drawer

import com.cube.cube_test.data.api.data.BaseData
import com.cube.cube_test.data.detail.AttractionsDetail
import com.google.gson.annotations.SerializedName

class ApiAttractions {
    public class GetAttractions{
        public class Response : BaseData.Response<List<AttractionsDetail>>() {

        }
    }
}