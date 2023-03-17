package com.cube.cube_test.custom.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ci.v1_ci_view.ui.activity.CIActivity
import com.cube.cube_test.custom.fragment.CubeTestFragment
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

    override fun getMainView(): View {
        TODO("Not yet implemented")
    }
    // MARK:- ========================== View

    // MARK:- ========================== Data
    open fun getFragmentId(cls: Class<out CubeTestFragment?>): String? {
        return "Main_" + cls.simpleName
    }
    // MARK:- ========================== Event

    // MARK:- ========================== Method
    open fun loadData(request: TRequest?){

    }

    fun readIntentRequest(cls: Class<TRequest>?): TRequest{
        return Gson().fromJson(intent.getStringExtra(CubeTestConfig.Extra.KEY_JSON),cls)
    }


}


