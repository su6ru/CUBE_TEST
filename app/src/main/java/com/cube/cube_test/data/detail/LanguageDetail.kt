package com.cube.cube_test.data.detail

import com.google.gson.annotations.SerializedName
/** 語言資料 data model */
class LanguageDetail {
    /** id */
    @SerializedName("id")
    var mId : String = "1"
    /** 語言 */
    @SerializedName("name")
    var mName : String = "正體中文"
    /** url參數 */
    @SerializedName("parameter")
    var mParameter : String = "zh-tw"
    /** locale language 參數 */
    @SerializedName("locale_language")
    var mLocaleLanguage : String = "zh"
    /** locale country 參數 */
    @SerializedName("locale_country")
    var mLocaleCountry : String = "TW"

}