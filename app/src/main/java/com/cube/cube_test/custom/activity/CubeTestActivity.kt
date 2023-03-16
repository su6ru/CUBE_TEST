package com.cube.cube_test.custom.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.ci.v1_ci_view.ui.activity.CIActivity
import com.cube.cube_test.data.define.CubeTestConfig
import com.google.gson.Gson
/** 主繼承類Activity */
open class CubeTestActivity<TRequest> : CIActivity() {
    // MARK:- ====================== Define
    companion object{
        fun startActivity(activity: CIActivity,cls: Class<out CIActivity?>?,request: Any?,requestCode: Int?){
            val intent = Intent(activity,cls)
            if (request != null){
                intent.putExtra(CubeTestConfig.Extra.KEY_JSON, Gson().toJson(request))
            }

            if (requestCode != null){
                activity.startActivityForResult(intent,requestCode)
            }else{
                activity.startActivity(intent)
            }

        }
    }

    // MARK:- ==========================Constructor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    // MARK:- ========================== View

    // MARK:- ========================== Data

    // MARK:- ========================== Event

    // MARK:- ========================== Method
    open fun loadData(request: TRequest?){

    }

    fun readIntentRequest(cls: Class<TRequest>?): TRequest{
        return Gson().fromJson(intent.getStringExtra(CubeTestConfig.Extra.KEY_JSON),cls)
    }
    /** 顯示Toast */
    fun showToast(text: String){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }

}


