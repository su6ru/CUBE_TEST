package com.ci.v1_ci_view.ui.extendlayout

import android.content.Context
import android.hardware.display.DisplayManager
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.Display
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop

/** 延展式layout (未完成 2023/3/13) */
class ExtendLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): ViewGroup(context, attrs, defStyleAttr){

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        //取得手機寬度、高度
        val phoneWidth = mDisplayMetrics.widthPixels
        val phoneHeight = mDisplayMetrics.heightPixels

        //設定ExtendLayout初始寬度、高度為0
        var groupWidth = 0
        var groupHeight = 0

        var thisLineMaxHeight = 0
        var thisLineMaxWidth = 0
        var haveNextLine = false

        //根據子View計算ExtendLayout的寬度、高度
        for(i in 0 until childCount){
            val childView = getChildAt(i)
            if (childView.visibility != GONE){
                measureChild(childView,widthMeasureSpec,heightMeasureSpec)

              //  groupWidth +=  childView.measuredWidth + childView.marginStart

                if (childView.measuredHeight > thisLineMaxHeight){
                    thisLineMaxHeight = childView.measuredHeight
                }
                groupWidth += childView.measuredWidth + childView.marginStart

                if (groupWidth > phoneWidth){
                    groupHeight += thisLineMaxHeight
                    thisLineMaxHeight = 0
                    haveNextLine = true
                }

            }
        }

       if (haveNextLine){
           groupWidth = phoneWidth
       }
        //修正寬度
        groupWidth = resolveSize(groupWidth,widthMeasureSpec)
        //修正高度
        groupHeight = resolveSize(groupHeight,heightMeasureSpec)
        //設定ExtendLayout寬度、高度
        setMeasuredDimension(groupWidth,groupHeight)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        //取得手機寬度、高度
        val phoneWidth = mDisplayMetrics.widthPixels

        var nowLeftPosition = 0
        var nowTopPosition = 0
        var thisLineMaxHeight = 0

        var lastViewMarginEnd = 0
        var lastViewMarginBottom = 0


        for (i in 0 until childCount){
            val childView = getChildAt(i)
            if (childView.visibility!=View.GONE){
                //元件寬度
                val childViewWidth = childView.measuredWidth
                //元件高度
                val childViewHeight = childView.measuredHeight


                //算出元件左方位子
                var viewLeft = getChildViewLeft(nowLeftPosition,childView,lastViewMarginEnd)
                //算出元件右方位子
                var viewRight = getChildViewRight(nowLeftPosition,childView,lastViewMarginEnd,childViewWidth)

                //當元件擺放的右邊超過螢幕寬 則將此元件改為放到下一行的最左邊
                if (viewRight > phoneWidth){
                    nowLeftPosition = 0
                    viewLeft = getChildViewLeft(nowLeftPosition,childView,lastViewMarginEnd)
                    viewRight = getChildViewRight(nowLeftPosition,childView,lastViewMarginEnd,childViewWidth)

                    nowTopPosition = thisLineMaxHeight
                    thisLineMaxHeight = 0
                }

                //算出元件上方位子
                val viewTop = getChildViewTop(nowTopPosition, childView)

                //算出元件下方位子
                val viewBottom = getChildViewBottom(nowTopPosition, childView,childViewHeight)

                if (thisLineMaxHeight < viewBottom) {
                    thisLineMaxHeight = viewBottom
                }

                childView.layout(viewLeft,viewTop,viewRight,viewBottom)

                nowLeftPosition = viewRight

                lastViewMarginEnd = childView.marginEnd

            }

        }

    }

    val mDisplayMetrics = resources.displayMetrics

    /** 子view左邊 位子 取得 */
    private fun getChildViewLeft(nowLeftPosition : Int,childView : View,lastViewMarginEnd : Int) : Int {

        return nowLeftPosition + childView.marginStart + lastViewMarginEnd
    }
    /** 子view右邊 位子 取得 */
    private fun getChildViewRight(nowLeftPosition : Int,childView : View,lastViewMarginEnd : Int,childViewWidth : Int) : Int {

        return nowLeftPosition + childView.marginStart + lastViewMarginEnd + childViewWidth
    }
    /** 子view上方 位子 取得 */
    private fun getChildViewTop(nowTopPosition : Int,childView : View) : Int {

        return nowTopPosition + childView.marginTop
    }
    /** 子view下方 位子 取得 */
    private fun getChildViewBottom(nowTopPosition : Int,childView : View,childViewHeight : Int) : Int {

        return nowTopPosition + childView.marginTop + childViewHeight
    }



    /** 用於讓子View可讀取設定的margin */
    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }
    /** 用於讓子View可讀取設定的margin */
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context,attrs)
    }
    /** 用於讓子View可讀取設定的margin */
    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT)
    }
}