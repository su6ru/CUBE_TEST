package com.cube.cube_test.feature.news

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.ui.`interface`.IOnOptionLister
import com.ci.v1_ci_view.ui.recyclerview.CIRecyclerView
import com.ci.v1_ci_view.ui.recyclerview.CIRecyclerViewAdapter
import com.ci.v1_ci_view.ui.textview.CITextView
import com.cube.cube_test.R
import com.cube.cube_test.custom.application.CubeTestApplication
import com.cube.cube_test.custom.fragment.CubeTestFragment
import com.cube.cube_test.data.api.drawer.ApiEvents
import com.cube.cube_test.data.define.CubeTestConfig
import com.cube.cube_test.data.detail.NewsDetail
import com.cube.cube_test.data.feature.FeatureData
import com.cube.cube_test.feature.main.MainActivity
import com.cube.cube_test.util.CubeTestUntil

/** 最新消息列表 */
class NewsListFragment : CubeTestFragment(R.layout.fragment_news_list) {
    // MARK:- ========================== Define

    // MARK:- ========================== Life
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Data
        run {
            mRecyclerView.layoutManager = LinearLayoutManager(context)
            mRecyclerView.adapter = mListAdapter
        }
        //Event
        run{
            //onRecyclerViewSlide
            mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (recyclerView is CIRecyclerView) {
                        onRecyclerViewSlide(recyclerView as CIRecyclerView, newState)
                    }
                }
            })
        }

        loadData(null)

    }

    // MARK:- ========================== View
    /** 資料列表 */
    private val mRecyclerView : CIRecyclerView by lazy {
        requireView().findViewById(R.id.list_content)
    }
    // MARK:- ========================== Data
    /** 頁面資料 */
    private var mData = Data()
    /** 當目前為RecyclerView的最上層 若此時往下拉 防止重複觸發 拉至頂刷新列表的防呆布林  */
    private var isRecyclerViewSlideTopLock = true
    /** 資料配接器 */
    private val mListAdapter = object : CIRecyclerViewAdapter<NewsListViewHolder, NewsDetail>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
            val newsListViewHolder = NewsListViewHolder(parent.context)
            //onItemListClick
            newsListViewHolder.itemView.setOnClickListener {
                onItemListClick(getItem(newsListViewHolder.adapterPosition))
            }

            return newsListViewHolder
        }

        override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {

            val newsDetail = getItem(position)

            holder.loadData(newsDetail)
        }



    }
    // MARK:- ========================== Event
    /** 觸發語言切換 */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        loadData(null)

    }
    /** 點擊任一最新消息 */
    private fun onItemListClick(newsDetail: NewsDetail){
        val request = NewsContentActivity.Data.Request()

        request.newsDetail = newsDetail

        if (activity is MainActivity) {

            val mainActivity = activity as MainActivity

            NewsContentActivity.startActivity(mainActivity,request)
        }

    }
    /** 當滑動列表 */
    private fun onRecyclerViewSlide(recyclerView : CIRecyclerView, status : Int) {
        if (recyclerView.isRecyclerViewSlideTop()){
            if(isRecyclerViewSlideTopLock){
                isRecyclerViewSlideTopLock = false
                return
            }
            loadData(null)
            return
        }
        val oldPage = mData.mRequest.mPage

        val newsResponse = mData.mResponse.mGetNewsResponse ?: return

        val newsDetailList = newsResponse.mData ?: return

        val apiListSize = newsResponse.mTotal
        val nowListSize = newsDetailList.size
        val nextPage = CubeTestUntil.getRecyclerViewNextPage(recyclerView,status,apiListSize,nowListSize, oldPage)
            ?: return
        mData.mRequest.mPage = nextPage


        val languageDetail= CubeTestApplication.instance().mCubeTestManager.mLanguageDetail

        val url = languageDetail?.mParameter+ CubeTestConfig.Api.NEWS_URL
        //呼叫API
        setLoading(true)
        CubeTestApplication
            .instance()
            .mApiManager
            .APIEventsDrawer()
            .callGetNews(url.toString(),nextPage,object :
                IOnOptionLister<ApiEvents.GetNews.Response> {
                override fun onExecute(option: ApiEvents.GetNews.Response) {
                    val apiNewsList = option.mData
                    val dataNewsResponse = mData.mResponse.mGetNewsResponse ?: return

                    val dataNewsList = dataNewsResponse.mData ?: return

                    if (apiNewsList != null) {
                        mListAdapter.addItemList(apiNewsList)
                        dataNewsList.addAll(apiNewsList)
                    }

                }
            },object : IOnOptionLister<String> {
                override fun onExecute(option: String) {
                    showToast(option)

                }
            },object : IOnOptionLister<Void?> {
                override fun onExecute(option: Void?) {
                    isRecyclerViewSlideTopLock = true
                    setLoading(false)
                }

            })

    }
    // MARK:- ========================== Method

    override fun loadData(request: Any?) {
        super.loadData(request)
        //API呼叫 並將資料套到列表中
        kotlin.run {
            val languageDetail= CubeTestApplication.instance().mCubeTestManager.mLanguageDetail
            val url = languageDetail?.mParameter+ CubeTestConfig.Api.NEWS_URL

            val page: String = CubeTestConfig.Api.Companion.Property.DEFAULT_PAGE
            setLoading(true)
            CubeTestApplication
                .instance()
                .mApiManager
                .APIEventsDrawer()
                .callGetNews(url.toString(),page,
                    object : IOnOptionLister<ApiEvents.GetNews.Response> {
                        override fun onExecute(option: ApiEvents.GetNews.Response) {
                            mListAdapter.setItemList(option.mData!!)
                            mData.mResponse.mGetNewsResponse = option
                        }
                    },
                    object : IOnOptionLister<String> {
                        override fun onExecute(option: String) {
                            showToast(option)

                        }
                    },
                    object : IOnOptionLister<Void?> {
                        override fun onExecute(option: Void?) {
                            isRecyclerViewSlideTopLock = true
                            setLoading(false)
                        }

                    })
        }
    }
    // MARK:- ========================== NewsListViewHolder
    class NewsListViewHolder(context: Context) : RecyclerView.ViewHolder(View.inflate(context,R.layout.item_news_list,null)) {
        /** 標題 */
        private val mTitleTextView : CITextView by lazy {
            itemView.findViewById(R.id.text_title)
        }
        /** 說明 */
        private val mDescriptionTextView : CITextView by lazy {
            itemView.findViewById(R.id.text_description)
        }
        fun loadData(newsDetail: NewsDetail){
            mTitleTextView.text = HtmlCompat.fromHtml(newsDetail.mTitle.toString(), HtmlCompat.FROM_HTML_MODE_COMPACT)

            mDescriptionTextView.text = HtmlCompat.fromHtml(newsDetail.mDescription, HtmlCompat.FROM_HTML_MODE_COMPACT)

        }



    }

    // MARK:- ========================== Class Data
    class Data : FeatureData<Data.Request, Data.Response>(Data.Request(),Data.Response()){

        class Request(){
            var mPage : String = "1"
        }
        class Response(){
            var mGetNewsResponse : ApiEvents.GetNews.Response? = null
        }
    }

}