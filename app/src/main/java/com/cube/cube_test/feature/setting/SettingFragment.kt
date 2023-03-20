package com.cube.cube_test.feature.setting

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat.recreate
import com.ci.v1_ci_view.ui.layout.CILinearLayout
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
            mLanguageLayout.setOnClickListener {

                LanguageListActivity.startActivity(activity as CubeTestActivity<*>,LanguageListActivity.REQUEST_CODE)
                // onLanChangeClick()
            }
        }
        //Init
        run{
            loadData(null)
        }

    }



    // MARK:- ====================== View
    /** 語言layout */
    private val mLanguageLayout: CILinearLayout by lazy {
        requireView().findViewById(R.id.layout_language)
    }
    /** 語言設定title */
    private val mLanguageTitleTextView: CITextView by lazy {
        requireView().findViewById(R.id.text_language_title)
    }
    /** 當前語言 */
    private val mLanguageTextView: CITextView by lazy {
        requireView().findViewById(R.id.text_language)
    }
    // MARK:- ========================== Data
    /** 頁面資料 */
    private var mData = Data()

    // MARK:- ========================== Event

    /** 觸發語言設定 */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val languageDetail = CubeTestApplication.instance().mCubeTestManager.mLanguageDetail
        mLanguageTextView.text = languageDetail?.mName
        mLanguageTitleTextView.text = getText(R.string.n_language_setting)
    }
    // MARK:- ========================== Method

    override fun loadData(request: Any?) {
        super.loadData(request)
        val languageDetail = CubeTestApplication.instance().mCubeTestManager.mLanguageDetail
        mLanguageTextView.text = languageDetail?.mName

    }

    // MARK:- ====================== Class Data
    class Data : FeatureData<Data.Request, Data.Response>(Data.Request(),Data.Response()){

        class Request(){
        }
        class Response(){
        }
    }

}