package com.cube.cube_test.data.detail

import com.google.gson.annotations.SerializedName
/** 語言資料 data model */
class LanguageDetail {
    @SerializedName("id")
    var mId : String = "1"
    @SerializedName("name")
    var mName : String = "正體中文"
    @SerializedName("parameter")
    var mParameter : String = "zh-tw"
    @SerializedName("locale_language")
    var mLocaleLanguage : String = "zh"
    @SerializedName("locale_country")
    var mLocaleCountry : String = "TW"

}