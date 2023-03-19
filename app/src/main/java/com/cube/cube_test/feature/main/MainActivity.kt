package com.cube.cube_test.feature.main

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment
import com.cube.cube_test.R
import com.cube.cube_test.custom.activity.CubeTestActivity
import com.cube.cube_test.custom.application.CubeTestApplication
import com.cube.cube_test.custom.fragment.CubeTestFragment
import com.cube.cube_test.data.feature.FeatureData
import com.cube.cube_test.feature.attractions.AttractionsListFragment
import com.cube.cube_test.feature.news.NewsListFragment
import com.cube.cube_test.feature.setting.LanguageListActivity
import com.cube.cube_test.feature.setting.SettingFragment
import com.google.android.material.tabs.TabLayout
import java.util.*

/** 首頁 */
class MainActivity : CubeTestActivity<MainActivity.Data.Request>() {
    // MARK:- ===================== Define

    // MARK:- ===================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        mTabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    onTabChanged(tab.position)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })


        onTabChanged(0)
        loadData(readIntentRequest(Data.Request::class.java))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LanguageListActivity.REQUEST_CODE){
            if (resultCode == RESULT_OK){
                val dbLanguageDetail = CubeTestApplication.instance().mCubeTestManager.mLanguageDetail
                val dbLanguageId = dbLanguageDetail?.mId
                if (mOriginLanguageId == null){
                    return
                }
                if (mOriginLanguageId == dbLanguageId){
                    return
                }
                val localeLanguage = dbLanguageDetail?.mLocaleLanguage
                val localeCountry = dbLanguageDetail?.mLocaleCountry


                val locale = Locale(localeLanguage,localeCountry)
                val res: Resources = this.resources
                val dm: DisplayMetrics = res.displayMetrics
                val conf: Configuration = res.configuration.apply {
                    setLocale(locale)
                }
                res.updateConfiguration(conf, dm)
                this.onConfigurationChanged(conf)

            }
        }


    }

    // MARK:- ====================== View
    /** TabLayout */
    val mTabLayout : TabLayout by lazy {
        findViewById(R.id.tab_layout)
    }
    override fun getMainView(): View {
        return findViewById(R.id.layout_parent)
    }
    // MARK:- ====================== Data
    /** 頁面資料 */
    var mData = Data()
    /** 景點 fragment */
    var mAttractionsListFragment = AttractionsListFragment()
    /** 最新消息 fragment */
    var mNewsListFragment = NewsListFragment()
    /** 設定 fragment */
    var mSettingFragment = SettingFragment()
    /** 用於暫存當前切換的Fragment marker */
    var mTabPosition = 999
    var mOriginLanguageId :String? = null
    // MARK:- ====================== Event
    /** 當點擊 tab */
    private fun onTabChanged(position : Int){
        // 索引無變化 返回
        if (mTabPosition == position){
            return
        }
        mTabPosition = position
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        //定義不使用的頁面
        var oldFragment :CubeTestFragment? = null
        for (fragment : Fragment in supportFragmentManager.fragments){
            if (fragment is CubeTestFragment && fragment.isVisible){
                oldFragment = fragment as CubeTestFragment
                break
            }
        }
        //定義即將使用的頁面
        var nowFragment : CubeTestFragment? = null
        var id :String? = null

        when (position) {
            0 -> {
                id = getFragmentId(AttractionsListFragment ::class.java)
                val frgament = supportFragmentManager.findFragmentByTag(id)
                nowFragment = if (frgament == null){
                    mAttractionsListFragment
                }else{
                    frgament as CubeTestFragment
                }
            }
            1 -> {
                id = getFragmentId(NewsListFragment ::class.java)
                val frgament = supportFragmentManager.findFragmentByTag(id)
                nowFragment = if (frgament == null){
                    mNewsListFragment
                }else{
                    frgament as CubeTestFragment
                }
            }
            2 -> {
                id = getFragmentId(SettingFragment ::class.java)
                val frgament = supportFragmentManager.findFragmentByTag(id)
                nowFragment = if (frgament == null){
                    mSettingFragment
                }else{
                    frgament as CubeTestFragment
                }
            }
            else ->{
                throw RuntimeException("No define fragment")
            }
        }

        if (!nowFragment.isAdded){
            fragmentTransaction.add(R.id.layout_content,nowFragment,getFragmentId(nowFragment ::class.java))
        }

        //隱藏舊的fragment
        if (oldFragment != null){
            fragmentTransaction.hide(oldFragment)
        }
        //顯示新的fragment
        if (nowFragment != null){
            fragmentTransaction.show(nowFragment)
        }
        fragmentTransaction.commit()

        /*
        val fragmentManager = supportFragmentManager // 获取 FragmentManagerX 实例
        val fragmentTransaction = fragmentManager.beginTransaction() // 获取 FragmentTransactionX 实例
        val fragment = MyFragment() // 创建要添加的 Fragment
        fragmentTransaction.add(R.id.container, fragment) // 添加 Fragment 到容器中
        fragmentTransaction.commit() // 提交事务
        */

    }
    // MARK:- ====================== Method
    override fun loadData(request: Data.Request?) {
        super.loadData(request)
        val dbLanguageDetail = CubeTestApplication.instance().mCubeTestManager.mLanguageDetail
        mOriginLanguageId = dbLanguageDetail?.mId

    }

    // MARK:- ====================== Class Data
    class Data : FeatureData<Data.Request,Data.Response>(Request(),Response()){

        class Request(){

        }
        class Response(){

        }
    }
}