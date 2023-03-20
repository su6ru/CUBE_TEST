package com.cube.cube_test.feature.attractions

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.ui.`interface`.IOnOptionLister
import com.ci.v1_ci_view.ui.banner.CIBannerView
import com.ci.v1_ci_view.ui.recyclerview.CIRecyclerView
import com.ci.v1_ci_view.ui.recyclerview.CIRecyclerViewAdapter
import com.ci.v1_ci_view.ui.textview.CITextView
import com.ci.v1_ci_view.ui.until.CIUntil
import com.cube.cube_test.R
import com.cube.cube_test.custom.activity.CubeTestActivity
import com.cube.cube_test.custom.application.CubeTestApplication
import com.cube.cube_test.custom.view.CubeTestToolbar
import com.cube.cube_test.data.detail.AttractionsDetail
import com.cube.cube_test.data.detail.LanguageDetail
import com.cube.cube_test.data.detail.NewsDetail
import com.cube.cube_test.data.detail.WebViewDetail
import com.cube.cube_test.data.feature.FeatureData
import com.cube.cube_test.feature.attractions.AttractionsListFragment
import com.cube.cube_test.feature.news.NewsContentActivity
import com.cube.cube_test.feature.webview.WebViewActivity
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.*
import kotlin.collections.ArrayList

/** 遊憩景點內頁 */
class AttractionsContentActivity : CubeTestActivity<AttractionsContentActivity.Data.Request>() {
    // MARK:- ====================== Define
    companion object{
        fun startActivity(activity: CubeTestActivity<*>,request:Data.Request){
            startActivity(activity, AttractionsContentActivity::class.java, request, null)

        }
    }
    // MARK:- ====================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attractions_content)
        //View
        run {

        }
        //Data
        run {

        }
        //Event
        run{
            //onGoWebButtonClick
            mToolbar.setRightButtonClickListener(object : IOnOptionLister<CubeTestToolbar> {
                override fun onExecute(option: CubeTestToolbar) {
                    onGoWebClick()
                }
            })
            //onBackClick
            mToolbar.setBackButtonClickListener(object : IOnOptionLister<CubeTestToolbar> {
                override fun onExecute(option: CubeTestToolbar) {
                    onBackClick()
                }
            })
            //onNavigationClick
            mNavigationTextView.setOnClickListener {
                onNavigationClick()
            }
        }
        //Init
        run{
            loadData(readIntentRequest(Data.Request::class.java))
        }
    }


    // MARK:- ====================== View
    /** Toolbar */
    private val mToolbar : CubeTestToolbar by lazy {
        findViewById(R.id.toolbar)
    }
    /** 圖片banner */
    private val mBannerView : CIBannerView by lazy {
        findViewById(R.id.view_banner)
    }
    /** 標題 */
    private val mTitleTextView : CITextView by lazy {
        findViewById(R.id.text_title)
    }
    /** 內文 */
    private val mContentTextView : CITextView by lazy {
        findViewById(R.id.text_content)
    }
    /** 用google map方式開啟 */
    private val mNavigationTextView : CITextView by lazy {
        findViewById(R.id.text_navigation)
    }
    // MARK:- ====================== Data
    /** 頁面資料 */
    private var mData = Data()
    // MARK:- ====================== Event
    /** 點擊 toolbar的返回 */
    private fun onBackClick(){
        finish()
    }
    /** 點擊 前往網頁 */
    private fun onGoWebClick(){

        val attractionsDetail = mData.mRequest.mAttractionsDetail ?: return

        val url = attractionsDetail.mUrl

        if (url == null || url.isEmpty()){
            showToast(getString(R.string.msg_this_item_no_data_web))
            return
        }
        val title = attractionsDetail.mName

        val webViewDetail = WebViewDetail()
        webViewDetail.mUrl = url
        webViewDetail.mTitle = title

        val request = WebViewActivity.Data.Request()
        request.webViewDetail = webViewDetail

        WebViewActivity.startActivity(this@AttractionsContentActivity,request)

    }
    /** 點擊 點擊此處開啟導航 */
    private fun onNavigationClick(){
        val attractionsDetail = mData.mRequest.mAttractionsDetail ?: return
        //北緯
        val latitude = attractionsDetail.mNlat
        //東經
        val longitude = attractionsDetail.mElong

        val uri = Uri.parse("geo:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setClassName("com.google.android.apps.maps",
            "com.google.android.maps.MapsActivity")
        startActivity(mapIntent)

    }
    // MARK:- ====================== Method
    override fun loadData(request: Data.Request?) {
        super.loadData(request)

        if (request == null){
            return
        }

        mData.mRequest = request

        val attractionsDetail = request.mAttractionsDetail ?: return

        mToolbar.setTitle(attractionsDetail.mName)

        mTitleTextView.text = HtmlCompat.fromHtml(attractionsDetail.mName, HtmlCompat.FROM_HTML_MODE_COMPACT)
        mContentTextView.text = HtmlCompat.fromHtml(attractionsDetail.mIntroduction, HtmlCompat.FROM_HTML_MODE_COMPACT)
        //北緯
        val nlat = attractionsDetail.mNlat
        //東經
        val elong = attractionsDetail.mElong
        if (nlat == null){
            mNavigationTextView.visibility = View.GONE
        }
        if (elong == null){
            mNavigationTextView.visibility = View.GONE
        }

        val imagesDetailList = attractionsDetail.mImagesDetailList

        if (imagesDetailList!=null && imagesDetailList.isNotEmpty()) {
            mBannerView.visibility = View.VISIBLE

            val urlList : MutableList<String> = ArrayList()

            for (imageDetail in imagesDetailList){

                val src = imageDetail.mSrc

                if (src != null && src.isNotEmpty()){
                    urlList.add(src)
                }
            }


                mBannerView.loadData(urlList,0)
        }else{
            mBannerView.visibility = View.GONE
        }
    }

    // MARK:- ====================== Class Data
    class Data : FeatureData<Data.Request,Data.Response>(Request(),Response()){

        class Request(){
            var mAttractionsDetail: AttractionsDetail? = null
        }
        class Response(){

        }
    }
}