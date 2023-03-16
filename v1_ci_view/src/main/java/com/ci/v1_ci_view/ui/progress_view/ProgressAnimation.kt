package com.ci.v1_ci_view.ui.progress_view

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.ci.v1_ci_view.R

class ProgressAnimation @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val mObjectAnimator = ObjectAnimator.ofFloat(mProgressView,"mProgress",0f,60f)
        mObjectAnimator.duration = 5000
        mObjectAnimator.start()

    }
    /** 進度元件 */
    private val mProgressView : ProgressView by lazy {
        findViewById(R.id.view_progress)
    }
}