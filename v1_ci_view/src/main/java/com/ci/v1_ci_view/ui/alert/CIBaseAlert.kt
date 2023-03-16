package com.ci.v1_ci_view.ui.alert

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.ci.v1_ci_view.R
import com.ci.v1_ci_view.ui.progress_view.ProgressView
import com.ci.v1_ci_view.ui.until.CIUntil
/** 客製化Alert的繼承基礎類 */
open class CIBaseAlert @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    @LayoutRes layout: Int = 0
) : FrameLayout(context,attributeSet) {

    init {
        inflate(context,layout,this)


        setBackgroundColor(-0x67000000)

        isClickable = true

        isFocusableInTouchMode = true

        isFocusable = true
        /*
        if (findViewById<View>(R.id.alert_content) != null) {
            findViewById<View>(R.id.alert_content).setOnClickListener(null)
        }
        */

        if (findViewById<View>(R.id.alert_parent) != null) {
            findViewById<View>(R.id.alert_parent).setOnClickListener(null)
        }


    }
    // MARK:- ========================== View
    /** 父view */
    public val mParentView : View by lazy {
        findViewById(R.id.alert_parent)
    }


    // MARK:- ========================== Data
    private var mIsShow = false
    // MARK:- ========================== Event
    /** 顯示alert */
    public fun show(parent : FrameLayout){
        if (mIsShow){
            return
        }
        parent.addView(this,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)

        if (requestFocus()){
            CIUntil.hideSoftKeyboard(this)
        }

        val am = AlphaAnimation(0.2f,1f).apply {
            this.duration = 200
        }

        mParentView.startAnimation(am)

        mIsShow = true

    }
    /** 關閉alert */
    public fun show(){
        if (parent !is ViewGroup){
            return
        }
        val parentLayout = parent as ViewGroup

        if (requestFocus()){
            CIUntil.hideSoftKeyboard(this)
        }

        parentLayout.removeView(this)

        mIsShow = false

    }

    // MARK:- ========================== Method

}