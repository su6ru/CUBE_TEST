package com.cube.cube_test.data.detail

import com.google.gson.annotations.SerializedName
/** 最新消息 data model */
class NewsDetail {

    @SerializedName("id")
    var mId : Int? = null
    /** 標題 */
    @SerializedName("title")
    var mTitle : String? = null
    /** 說明 */
    @SerializedName("description")
    var mDescription : String? = null
    /** 開放時間 */
    @SerializedName("begin")
    var mBegin : String? = null
    /** 截止時間 */
    @SerializedName("end")
    var mEnd : String? = null
    /** 發布時間 */
    @SerializedName("posted")
    var mPosted : String? = null
    /** 最後修改時間 */
    @SerializedName("modified")
    var mModified : String? = null
    /** 資料源網址 */
    @SerializedName("url")
    var mUrl : String? = null
    /** 附件 */
    @SerializedName("files")
    var mFilesDetail : List<FilesDetail>? = null
    class FilesDetail{
        /** 附件連結 */
        @SerializedName("src")
        var mSrc : String? = null
        /** subject */
        @SerializedName("subject")
        var mSubject : String? = null
        /** 副檔名 如".jpg" */
        @SerializedName("ext")
        var mExt : String? = null
    }
    /** links */
    @SerializedName("links")
    var mLinksDetail : List<LinksDetail>? = null
    class LinksDetail{
        /** 相關連結 */
        @SerializedName("src")
        var mSrc : String? = null
        /** subject */
        @SerializedName("subject")
        var mSubject : String? = null
    }

}