package com.ci.v1_ci_view.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.ci.v1_ci_view.ui.activity.CIActivity

open class CIFragment(layoutId : Int) : Fragment(layoutId) {
    init {

    }
    // MARK:- ===================== Life

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("///", this.javaClass.simpleName + " onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)

    }
    // MARK:- ====================== View

    // MARK:- ====================== Data

    // MARK:- ====================== Event

    // MARK:- ====================== Method
    fun setLoading(enable : Boolean){
        val cIActivity = activity as CIActivity
        cIActivity.setLoading(enable)
    }
    fun showToast(text: String){
        val cIActivity = activity as CIActivity
        cIActivity.showToast(text)
    }
}