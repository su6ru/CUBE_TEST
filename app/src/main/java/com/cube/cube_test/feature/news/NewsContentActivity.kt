package com.cube.cube_test.feature.news


import android.os.Bundle
import androidx.core.text.HtmlCompat
import com.ci.v1_ci_view.ui.`interface`.IOnOptionLister
import com.ci.v1_ci_view.ui.textview.CITextView
import com.cube.cube_test.R
import com.cube.cube_test.custom.activity.CubeTestActivity
import com.cube.cube_test.custom.view.CubeTestToolbar
import com.cube.cube_test.data.detail.NewsDetail
import com.cube.cube_test.data.detail.WebViewDetail
import com.cube.cube_test.data.feature.FeatureData
import com.cube.cube_test.feature.webview.WebViewActivity

/** 最新消息內頁 */
class NewsContentActivity : CubeTestActivity<NewsContentActivity.Data.Request>() {
    // MARK:- ====================== Define
    companion object{
        fun startActivity(activity: CubeTestActivity<*>,request:Data.Request){
            startActivity(activity, NewsContentActivity::class.java, request, null)

        }
    }
    // MARK:- ====================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_content)
        //View
        run {

        }
        //Data
        run {

        }
        //Event
        run{
            //onGoWebButtonClick
            mToolbar.setRightButtonClickListener(object :IOnOptionLister<CubeTestToolbar>{
                override fun onExecute(option: CubeTestToolbar) {
                    onGoWebClick()
                }
            })
            //onBackClick
            mToolbar.setBackButtonClickListener(object :IOnOptionLister<CubeTestToolbar>{
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
    /** 標題 */
    private val mTitleTextView : CITextView by lazy {
        findViewById(R.id.text_title)
    }
    /** 內文 */
    private val mContentTextView : CITextView by lazy {
        findViewById(R.id.text_content)
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

        val newsDetail = mData.mRequest.newsDetail ?: return

        val url = newsDetail.mUrl

        if (url == null || url.isEmpty()){
            showToast(getString(R.string.msg_this_item_no_data_web))
            return
        }
        val title = newsDetail.mTitle


        val webViewDetail = WebViewDetail()
        webViewDetail.mUrl = url
        webViewDetail.mTitle = title

        val request = WebViewActivity.Data.Request()
        request.webViewDetail = webViewDetail

        WebViewActivity.startActivity(this@NewsContentActivity,request)

    }
    // MARK:- ====================== Method
    override fun loadData(request: Data.Request?) {
        super.loadData(request)

        if (request == null){
            return
        }

        mData.mRequest = request

        val newsDetail = request.newsDetail ?: return

        mToolbar.setTitle(newsDetail.mTitle.toString())

        mTitleTextView.text = HtmlCompat.fromHtml(newsDetail.mTitle.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)
        mContentTextView.text = HtmlCompat.fromHtml(newsDetail.mDescription, HtmlCompat.FROM_HTML_MODE_COMPACT)


    }

    // MARK:- ====================== Class Data
    class Data : FeatureData<Data.Request,Data.Response>(Request(),Response()){

        class Request(){
            var newsDetail: NewsDetail? = null
        }
        class Response(){

        }
    }
}