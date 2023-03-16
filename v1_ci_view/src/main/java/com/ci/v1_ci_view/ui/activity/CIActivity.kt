package com.ci.v1_ci_view.ui.activity

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ci.v1_ci_view.ui.alert.CILoadingAlert

/** CIActivity */
open class CIActivity : AppCompatActivity() {
    // MARK:- ========================== Define

    // MARK:- ========================== Constructor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }
    // MARK:- ========================== View
    /** 用於顯示Loading中的 Alert */
    private var mLoadingAlert : CILoadingAlert? = CILoadingAlert(this)


    // MARK:- ========================== Data
    /** Loading 狀態的count ，當大於0代表Loading中 */
    private var mLoadingAlertCount = 0
    public fun isLoading() : Boolean{
        return mLoadingAlertCount != 0
    }
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

        if (mLoadingAlertCount == 0){
            return
        }

        if (mLoadingAlert == null){
            mLoadingAlert = CILoadingAlert(this)
        }

        if (!mLoadingAlert.isShown)
    }
    /** 隱藏Loading標誌 */
    private fun hideLoadingAlert(){

    }


}