package com.cube.cube_test.custom.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatTextView

import androidx.appcompat.widget.LinearLayoutCompat
import com.ci.v1_ci_view.ui.`interface`.IOnOptionLister
import com.ci.v1_ci_view.ui.button.CIButton
import com.ci.v1_ci_view.ui.layout.CILinearLayout
import com.ci.v1_ci_view.ui.textview.CITextView
import com.ci.v1_ci_view.ui.toolbar.CIToolbar
import com.cube.cube_test.R

/** CubeTestToolbar */
class CubeTestToolbar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {



    // MARK:-================= View
    /** 主layout */
    private val mMainLayout : LinearLayoutCompat by lazy {
        findViewById(R.id.layout_main)
    }
    /** 標題 */
    private val mTitleTextView : AppCompatTextView by lazy {
        findViewById(R.id.text_title_toolbar)
    }
    /** 右邊按鈕 */
    private val mRightButton : CITextView by lazy {
        findViewById(R.id.button_right)
    }
    /** 左邊 返回按鈕 */
    private val mBackButton : ImageButton by lazy {
        findViewById(R.id.button_back)
    }
    // MARK:-================= Data

    // MARK:-================= Event
    /** 右邊按鈕 */
    private var mRightButtonClickLister : IOnOptionLister<CubeTestToolbar>? = null
    fun getRightButtonClickListener(): IOnOptionLister<CubeTestToolbar>? {
        return mRightButtonClickLister
    }
    fun setRightButtonClickListener(onReloadListener: IOnOptionLister<CubeTestToolbar>?) {
        mRightButtonClickLister = onReloadListener
    }

    /** 返回 */
    private var mBackButtonClickLister : IOnOptionLister<CubeTestToolbar>? = null
    fun getBackButtonClickListener(): IOnOptionLister<CubeTestToolbar>? {
        return mBackButtonClickLister
    }
    fun setBackButtonClickListener(onReloadListener: IOnOptionLister<CubeTestToolbar>?) {
        mBackButtonClickLister = onReloadListener
    }
    // MARK:-================= Method

    fun setTitle(title : String){
        mTitleTextView.text = title
    }

    init {

        inflate(context, R.layout.view_cube_test_toolbar, this)

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
            //是否顯示右邊按鈕
            val isVisible = attributes.getBoolean(R.styleable.CIToolbar_showRightButton,true)
            mRightButton.visibility = if (isVisible){
                VISIBLE
            }else{
                INVISIBLE
            }

        }
        mRightButton.setOnClickListener {
            mRightButtonClickLister?.onExecute(this)
        }
        mBackButton.setOnClickListener {
            mBackButtonClickLister?.onExecute(this)
        }
    }

}