package com.ci.v1_ci_view.ui.until

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

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
    }
}