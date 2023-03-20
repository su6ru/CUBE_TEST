package com.cube.cube_test.feature.webview


import android.os.Bundle
import android.webkit.WebView
import com.ci.v1_ci_view.ui.`interface`.IOnOptionLister
import com.cube.cube_test.R
import com.cube.cube_test.custom.activity.CubeTestActivity
import com.cube.cube_test.custom.view.CubeTestToolbar
import com.cube.cube_test.data.detail.WebViewDetail
import com.cube.cube_test.data.feature.FeatureData


/** webview */
class WebViewActivity : CubeTestActivity<WebViewActivity.Data.Request>() {
    // MARK:- ====================== Define
    companion object{
        fun startActivity(activity: CubeTestActivity<*>,request:Data.Request){
            startActivity(activity, WebViewActivity::class.java, request, null)

        }
    }
    // MARK:- ====================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        //View
        run {

        }
        //Data
        run {

        }
        //Event
        run{
            //onBackClick
            mToolbar.setBackButtonClickListener(object : IOnOptionLister<CubeTestToolbar> {
                override fun onExecute(option: CubeTestToolbar) {
                    onBackClick()
                }
            })
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
    /** WebView */
    private val mWebView : WebView by lazy {
        findViewById(R.id.webview)
    }

    // MARK:- ====================== Data
    /** 頁面資料 */
    private var mData = Data()
    // MARK:- ====================== Event
    /** 點擊 toolbar的返回 */
    private fun onBackClick(){
        finish()
    }
    // MARK:- ====================== Method
    override fun loadData(request: Data.Request?) {
        super.loadData(request)

        if (request == null){
            return
        }

        mData.mRequest = request

        val webViewDetail = request.webViewDetail ?: return

        val url = webViewDetail.mUrl
        if (url == null || url.isEmpty()){
            return
        }

        var title = webViewDetail.mTitle
        if (title == null){
            title = ""
        }

        mToolbar.setTitle(title)
        mWebView.loadUrl(url)

    }

    // MARK:- ====================== Class Data
    class Data : FeatureData<Data.Request,Data.Response>(Request(),Response()){

        class Request(){
            var webViewDetail: WebViewDetail? = null
        }
        class Response(){

        }
    }
}