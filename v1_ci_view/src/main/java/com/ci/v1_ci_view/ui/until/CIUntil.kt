package com.ci.v1_ci_view.ui.until

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RawRes
import java.io.InputStream

class CIUntil {
    companion object {
        /** 隱藏軟體鍵盤  */
        fun hideSoftKeyboard(v: View?) {
            if (v == null) {
                return
            }
            val manager =
                v.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                    ?: return

            manager.hideSoftInputFromWindow(v.windowToken, 0)
        }
        /** 讀取.json本地資料，並以string方式回傳  */
        fun byRawResource(context:Context, @RawRes id: Int): String {
            var in_s: InputStream? = null
            try {
                val res = context.resources

                in_s = res.openRawResource(id)

                val b = ByteArray(in_s.available())

                in_s.read(b)

                return String(b)
            } catch (e: Exception) {
                return ""
            } finally {
                try {
                    in_s?.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}