package com.cube.cube_test.feature.attractions

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.ui.`interface`.IOnOptionLister
import com.ci.v1_ci_view.ui.recyclerview.CIRecyclerView
import com.ci.v1_ci_view.ui.recyclerview.CIRecyclerViewAdapter
import com.ci.v1_ci_view.ui.textview.CITextView
import com.cube.cube_test.R
import com.cube.cube_test.custom.application.CubeTestApplication
import com.cube.cube_test.custom.fragment.CubeTestFragment
import com.cube.cube_test.data.api.drawer.ApiAttractions
import com.cube.cube_test.data.define.CubeTestConfig
import com.cube.cube_test.data.detail.AttractionsDetail
import com.cube.cube_test.data.feature.FeatureData
import com.cube.cube_test.util.CubeTestUntil

/** 遊憩景點 列表 */
class AttractionsListFragment : CubeTestFragment(R.layout.fragment_attractions_list) {

    // MARK:- ===================== Define

    // MARK:- ===================== Life
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Data
        run {
            mRecyclerView.layoutManager = LinearLayoutManager(context)
            mRecyclerView.adapter = mListAdapter
        }
        //Event
        run{
            //addOnScrollListener
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
    // MARK:- ====================== View
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
    private val mListAdapter = object : CIRecyclerViewAdapter<AttractionsListViewHolder,AttractionsDetail>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): AttractionsListViewHolder {
            val attractionsListViewHolder = AttractionsListViewHolder(context!!)
            attractionsListViewHolder.itemView.setOnClickListener {

            }

            return attractionsListViewHolder
        }

        override fun onBindViewHolder(holder: AttractionsListViewHolder, position: Int) {

            val attractionsDetail = getItem(position)

            holder.loadData(attractionsDetail,context!!)
        }



    }
    // MARK:- ========================== Event
    /** 當滑動列表 */
    private fun onRecyclerViewSlide(recyclerView : CIRecyclerView,status : Int) {
        if (recyclerView.isRecyclerViewSlideTop()){
            if(isRecyclerViewSlideTopLock){
                isRecyclerViewSlideTopLock = false
                return
            }
            loadData(null)
            return
        }
        val oldPage = mData.mRequest.mPage

        val attractionsResponse = mData.mResponse.mGetAttractionsResponse ?: return

        val attractionsDetailList = attractionsResponse.mData ?: return

        val apiListSize = attractionsResponse.mTotal
        val nowListSize = attractionsDetailList.size
        val nextPage = CubeTestUntil.getRecyclerViewNextPage(recyclerView,status,apiListSize,nowListSize, oldPage)
            ?: return
        mData.mRequest.mPage = nextPage

        setLoading(true)
        CubeTestApplication
            .instance()
            .mApiManager
            .APIAttractionsDrawer()
            .callGetAttractions(nextPage,object : IOnOptionLister<ApiAttractions.GetAttractions.Response> {
                override fun onExecute(option: ApiAttractions.GetAttractions.Response) {
                    Log.d("","")
                    val apiAttractionsList = option.mData
                    val dataAttractionsResponse = mData.mResponse.mGetAttractionsResponse ?: return

                    val dataAttractionsList = dataAttractionsResponse.mData ?: return

                    if (apiAttractionsList != null) {
                        mListAdapter.addItemList(apiAttractionsList)
                        dataAttractionsList.addAll(apiAttractionsList)
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
            val page: String = CubeTestConfig.Api.Companion.Property.DEFAULT_PAGE
            setLoading(true)
            CubeTestApplication
                .instance()
                .mApiManager
                .APIAttractionsDrawer()
                .callGetAttractions(page,
                    object : IOnOptionLister<ApiAttractions.GetAttractions.Response> {
                        override fun onExecute(option: ApiAttractions.GetAttractions.Response) {
                            Log.d("", "")
                            mListAdapter.setItemList(option.mData!!)
                            mData.mResponse.mGetAttractionsResponse = option
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
    // MARK:- ====================== AttractionsListViewHolder
    class AttractionsListViewHolder(context:Context) : RecyclerView.ViewHolder(View.inflate(context,R.layout.item_attractions_list,null)) {
        init {

        }
        private val mIntroductionTextView : CITextView by lazy {
            itemView.findViewById(R.id.introductionTextView)
        }
        private val mTitleTextView : CITextView by lazy {
            itemView.findViewById(R.id.titleTextView)
        }
        fun loadData(attractionsDetail: AttractionsDetail,context: Context){
            mIntroductionTextView.text = attractionsDetail.mIntroduction
            mTitleTextView.text = attractionsDetail.mName
        }



    }

    // MARK:- ====================== Class Data
    class Data : FeatureData<Data.Request, Data.Response>(Data.Request(),Data.Response()){
        /*init {
            mRequest = Request()
            mResponse = Response()
        }*/
        class Request(){
            var mPage : String = "1"
        }
        class Response(){
            var mGetAttractionsResponse : ApiAttractions.GetAttractions.Response? = null
        }
    }
}