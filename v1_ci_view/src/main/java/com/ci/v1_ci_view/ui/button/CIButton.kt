package com.ci.v1_ci_view.ui.button

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
/** CIButton */
class CIButton : AppCompatButton {
    constructor(context : Context) : super(context){

    }
    constructor(context: Context,attributeSet: AttributeSet) :super(context,attributeSet){

    }
    constructor(context: Context,attributeSet: AttributeSet,defStyle: Int) : super(context,attributeSet,defStyle) {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}