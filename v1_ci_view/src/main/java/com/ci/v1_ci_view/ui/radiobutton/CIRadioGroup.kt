package com.ci.v1_ci_view.ui.radiobutton

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup

class CIRadioGroup :RadioGroup {

    constructor(context: Context):super(context){
        Log.d("","")

    }
    constructor(context: Context,attr: AttributeSet): super(context,attr){
        Log.d("","")

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var wid=MeasureSpec.getSize(widthMeasureSpec)
        var heigh=MeasureSpec.getSize(heightMeasureSpec)
        Log.d("","")

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        Log.d("","")
        for (i in 0..childCount){
            val childView = getChildAt(i)
            if (childView.visibility != View.GONE) {
                var width = childView.measuredWidth
                var height = childView.measuredHeight

            }
        }
    }
}