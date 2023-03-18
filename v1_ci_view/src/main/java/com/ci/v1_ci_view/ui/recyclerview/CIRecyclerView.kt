package com.ci.v1_ci_view.ui.recyclerview

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
/** RecyclerView  */
class CIRecyclerView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : RecyclerView(context,attributeSet) {


    override fun onScrollStateChanged(state: Int) {
        super.onScrollStateChanged(state)
    }

    override fun addOnScrollListener(listener: OnScrollListener) {
        super.addOnScrollListener(listener)
    }

    //當滑到最頂
    fun isRecyclerViewSlideTop(): Boolean {
        return !canScrollVertically(-1)
    }
}