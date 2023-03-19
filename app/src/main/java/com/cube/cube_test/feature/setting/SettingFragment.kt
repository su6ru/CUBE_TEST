package com.cube.cube_test.feature.setting

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat.recreate
import com.ci.v1_ci_view.ui.textview.CITextView
import com.ci.v1_ci_view.ui.until.CIUntil
import com.cube.cube_test.R
import com.cube.cube_test.custom.activity.CubeTestActivity
import com.cube.cube_test.custom.application.CubeTestApplication
import com.cube.cube_test.custom.fragment.CubeTestFragment
import com.cube.cube_test.data.detail.LanguageDetail
import com.cube.cube_test.data.feature.FeatureData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/** 設定 */
class SettingFragment : CubeTestFragment(R.layout.fragment_setting) {


    // MARK:- ===================== Define

    // MARK:- ===================== Life
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //View
        run {

        }
        //Data
        run {

        }
        //Event
        run{
            //onLanChangeClick
            mLanTextView.setOnClickListener {

                LanguageListActivity.startActivity(activity as CubeTestActivity<*>,LanguageListActivity.REQUEST_CODE)
               // onLanChangeClick()
            }
        }
        //Method
        run{
            loadData(null)
        }

    }



    // MARK:- ====================== View
    /** 當前語言 */
    private val mLanTextView : CITextView by lazy {
        requireView().findViewById(R.id.lanTextView)
    }
    // MARK:- ========================== Data
    /** 頁面資料 */
    private var mData = Data()

    // MARK:- ========================== Event
    private fun onLanChangeClick(){
        val languageRawStr = CIUntil.byRawResource(requireContext(),R.raw.language)
        val languageRawList :MutableList<LanguageDetail> =
            Gson().fromJson(languageRawStr,object :TypeToken<List<LanguageDetail>>(){}.type)

        val dbLanguageDetail = CubeTestApplication.instance().mCubeTestManager.mLanguageDetail
        val dbName = dbLanguageDetail?.mName


        var position = 0
        for (i in 0..languageRawList.size){
            val languageDetail = languageRawList[i]
            val name = languageDetail.mName
            if (dbName.equals(name)){
                position = i
                break
            }
        }
        position += 1

        if (position >= languageRawList.size){
            position = 0
        }

        val languageDetail = languageRawList[position]

        CubeTestApplication.instance().mCubeTestManager.mLanguageDetail = languageDetail
        //更換語言顯示
        mLanTextView.text = languageDetail.mName

        recreate(requireActivity())


        Log.d("","")
    }
    // MARK:- ========================== Method

    override fun loadData(request: Any?) {
        super.loadData(request)
        val languageDetail = CubeTestApplication.instance().mCubeTestManager.mLanguageDetail
        mLanTextView.text = languageDetail?.mName

    }

    // MARK:- ====================== Class Data
    class Data : FeatureData<Data.Request, Data.Response>(Data.Request(),Data.Response()){

        class Request(){
        }
        class Response(){
        }
    }

}