package com.ci.v1_ci_view.ui.progress_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.ci.v1_ci_view.R
import kotlin.math.roundToInt
/** 動態進度子元件 */
class ProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawArc(200f,100f,400f,300f,90f,(3.6*mProgress).toFloat(),false,mCirclePaint)
        canvas?.drawText(mProgress.roundToInt().toString(),300f,200f,mTextPaint)
    }
    //MARK:-===================Data
    /** 進度圓 Paint屬性 */
    var mCirclePaint : Paint = Paint().apply {

        color = context.getColor(R.color.purple_200)

        style = Paint.Style.STROKE

    }
    /** 進度文字 Paint屬性 */
    var mTextPaint : Paint = Paint().apply {

        color = context.getColor(R.color.purple_200)

        style = Paint.Style.FILL

        textSize = 20f
    }
    /** 進度值 範圍0~100  */
    public var mProgress : Float = 0f
        get() {
            return field
        }
        public set(value) {
            if (mProgress<0){
                Toast.makeText(context,"輸入需為正值",Toast.LENGTH_LONG).show()
                return
            }
            if (mProgress>100){
                Toast.makeText(context,"數值超出範圍",Toast.LENGTH_LONG).show()
                return
            }
            field =value
            invalidate()
        }

}