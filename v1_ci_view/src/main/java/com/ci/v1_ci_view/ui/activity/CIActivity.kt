package com.ci.v1_ci_view.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ci.v1_ci_view.ui.alert.CILoadingAlert
import com.ci.v1_ci_view.ui.fragment.CIFragment

/** CIActivity */
abstract class CIActivity : AppCompatActivity() {
    // MARK:- ========================== Define

    // MARK:- ========================== Constructor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoadingAlert  = CILoadingAlert(this)
    }

    override fun onResume() {
        super.onResume()
    }
    // MARK:- ========================== View
    /** 用於顯示Loading中的 Alert */
    private var mLoadingAlert : CILoadingAlert? = null
    /** 取得activity最外圍的layout */
    abstract fun getMainView():View
    // MARK:- ========================== Data
    /** Loading 狀態的count ，當大於0代表Loading中 */
    private var mLoadingAlertCount = 0
    public fun isLoading() : Boolean{
        return mLoadingAlertCount != 0
    }
    /** 設定是否顯示Loading */
    public fun setLoading(enable: Boolean){
        if (enable){
            showLoadingAlert()
        }else{
            hideLoadingAlert()
        }
    }


    // MARK:- ========================== Event

    // MARK:- ========================== Method
    /** 顯示Loading標誌 */
    private fun showLoadingAlert(){

        mLoadingAlertCount += 1

        Log.d("loading", "show")

        if (mLoadingAlertCount == 0) {
            return
        }

        if (mLoadingAlert == null) {
            mLoadingAlert = CILoadingAlert(this)
        }

        if (!mLoadingAlert!!.mIsShow) {
            val view = getMainView()
            if (view is FrameLayout) {
                mLoadingAlert!!.show(view)
            }
        }
    }
    /** 隱藏Loading標誌 */
    private fun hideLoadingAlert(){
        mLoadingAlertCount -= 1

        Log.d("loading", "hide")

        if (mLoadingAlertCount != 0) {
            return
        }

        if (mLoadingAlert == null) {
            return
        }

        mLoadingAlert!!.hide()
    }
    /** 顯示Toast */
    fun showToast(text: String){
        Toast.makeText(this,text, Toast.LENGTH_SHORT).show()
    }

}