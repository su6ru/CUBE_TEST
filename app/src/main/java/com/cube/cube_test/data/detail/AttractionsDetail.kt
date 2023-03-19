package com.cube.cube_test.data.detail

import com.google.gson.annotations.SerializedName
/** 遊憩景點 data model */
class AttractionsDetail {

    @SerializedName("id")
    var mId : Int? = null
    /** 地點名稱 */
    @SerializedName("name")
    var mName : String = ""
    @SerializedName("open_status")
    var mOpenStatus : Int? = null
    /** 介紹 */
    @SerializedName("introduction")
    var mIntroduction : String = ""
    /** 開放時間 */
    @SerializedName("open_time")
    var mOpenTime : String? = null
    /** 郵遞區號 */
    @SerializedName("zipcode")
    var mZipcode : String? = null
    /** 行政區(如中山區) */
    @SerializedName("distric")
    var mDistric : String? = null
    /** 完整地址 */
    @SerializedName("address")
    var mAddress : String? = null
    /** 電話 */
    @SerializedName("tel")
    var mTel : String? = null
    /** 傳真 */
    @SerializedName("fax")
    var mFax : String? = null
    /** email */
    @SerializedName("email")
    var mEmail : String? = null
    /** 月份 */
    @SerializedName("months")
    var mMonths : String? = null
    /** 北緯 */
    @SerializedName("nlat")
    var mNlat : Float? = null
    /** 東經 */
    @SerializedName("elong")
    var mElong : Float? = null

    /** 官方網址 */
    @SerializedName("official_site")
    var mOfficialSite : String? = null
    /** 臉書粉專網址 */
    @SerializedName("facebook")
    var mFacebook : String? = null
    /** 收費說明 */
    @SerializedName("ticket")
    var mTicket : String? = null

    /** 旅遊叮嚀 */
    @SerializedName("remind")
    var mRemind : String? = null
    /** 停留時間 */
    @SerializedName("staytime")
    var mStayTime : String? = null
    /** 最後修改時間 */
    @SerializedName("modified")
    var mModified : String? = null
    /** 資料源網址 */
    @SerializedName("url")
    var mUrl : String? = null
    /** 分類 */
    @SerializedName("category")
    var mCategoryDetail : List<CategoryDetail>? = null
    class CategoryDetail{
        /** 種類id */
        @SerializedName("id")
        var mId : Int? = null
        /** 種類 */
        @SerializedName("name")
        var mName : String? = null
    }
    /** 對象 */
    @SerializedName("target")
    var mTargetDetail : List<TargetDetail>? = null
    class TargetDetail{
        /** 種類id */
        @SerializedName("id")
        var mId : Int? = null
        /** 種類 */
        @SerializedName("name")
        var mName : String? = null
    }
    /** 設施 */
    @SerializedName("service")
    var mServiceDetail : List<ServiceDetail>? = null
    class ServiceDetail{
        /** 種類id */
        @SerializedName("id")
        var mId : Int? = null
        /** 種類 */
        @SerializedName("name")
        var mName : String? = null
    }
    /** 友善服務 */
    @SerializedName("friendly")
    var mFriendlyDetail : List<FriendlyDetail>? = null
    class FriendlyDetail{
        /** 種類id */
        @SerializedName("id")
        var mId : Int? = null
        /** 種類 */
        @SerializedName("name")
        var mName : String? = null
    }
    /** 圖片 */
    @SerializedName("images")
    var mImagesDetail : List<ImagesDetail>? = null
    class ImagesDetail{
        /** 圖片連結 */
        @SerializedName("src")
        var mSrc : String? = null
        /** subject */
        @SerializedName("subject")
        var mSubject : String? = null
        /** 副檔名 如".jpg" */
        @SerializedName("ext")
        var mExt : String? = null
    }
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
        /** 圖片連結 */
        @SerializedName("src")
        var mSrc : String? = null
        /** subject */
        @SerializedName("subject")
        var mSubject : String? = null
    }

}