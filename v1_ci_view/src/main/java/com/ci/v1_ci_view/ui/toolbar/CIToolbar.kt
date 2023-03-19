package com.ci.v1_ci_view.ui.toolbar

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.ci.v1_ci_view.R
/** CIToolbar */
class CIToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {



    // MARK:-================= View
    private val mMainLayout : LinearLayoutCompat by lazy {
        findViewById(R.id.layout_main)
    }
    private val mTitleTextView : AppCompatTextView by lazy {
        findViewById(R.id.text_title)
    }
    // MARK:-================= Data
    // MARK:-================= Event

    // MARK:-================= Method
    init {

        inflate(context, R.layout.view_ci_toolbar, this)

        if (attrs != null) {
            val attributes = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.CIToolbar,
                0, 0
            )
            //背景色
            mMainLayout.background = attributes.getDrawable(R.styleable.CIToolbar_android_background)
            //title文字
            mTitleTextView.text = attributes.getText(R.styleable.CIToolbar_title_text)


        }
    }

}