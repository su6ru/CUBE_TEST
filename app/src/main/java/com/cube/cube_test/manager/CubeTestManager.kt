package com.cube.cube_test.manager

import android.content.Context
import com.cube.cube_test.data.detail.LanguageDetail
import com.google.gson.Gson
import java.security.AccessController.getContext

open class CubeTestManager(private val context: Context) {
    companion object{
        const val Language ="Language"
    }

    var mLanguageDetail : LanguageDetail? = null
        get() {
            var languageDetail = Gson().fromJson(readDisk(Language), LanguageDetail::class.java)
            if (languageDetail == null){
                languageDetail = LanguageDetail()
                languageDetail.mId = "1"
                languageDetail.mName = "正體中文"
                languageDetail.mParameter = "zh-tw"
            }
            return languageDetail
        }
        set(value) {
            field = value
            writeDisk(Language, Gson().toJson(value))

        }


    /** 寫入資料 */
    protected fun writeDisk(key: String, content: String) {
        context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE)
            .edit()
            .putString(key, content)
            .apply()
    }

    /** 讀取資料 */
    protected fun readDisk(key: String): String? {
        return context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE)
            .getString(key, null)
    }

}