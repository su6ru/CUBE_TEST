package com.cube.cube_test.feature.setting

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.ui.`interface`.IOnOptionLister
import com.ci.v1_ci_view.ui.recyclerview.CIRecyclerView
import com.ci.v1_ci_view.ui.recyclerview.CIRecyclerViewAdapter
import com.ci.v1_ci_view.ui.textview.CITextView
import com.ci.v1_ci_view.ui.until.CIUntil
import com.cube.cube_test.R
import com.cube.cube_test.custom.activity.CubeTestActivity
import com.cube.cube_test.custom.application.CubeTestApplication
import com.cube.cube_test.custom.view.CubeTestToolbar
import com.cube.cube_test.data.detail.LanguageDetail
import com.cube.cube_test.data.feature.FeatureData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/** 語言設定 */
class LanguageListActivity : CubeTestActivity<LanguageListActivity.Data.Request>() {
    // MARK:- ====================== Define
    companion object{
        const val REQUEST_CODE = 20
        fun startActivity(activity: CubeTestActivity<*>,requestCode : Int){
            startActivity(activity, LanguageListActivity::class.java, null, requestCode)

        }
    }
    // MARK:- ====================== Life
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)
        //View
        run {

        }
        //Data
        run {

        }
        //Data
        run {
            mRecyclerView.layoutManager = LinearLayoutManager(this@LanguageListActivity)
            mRecyclerView.adapter = mListAdapter
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
        run {
            loadData(readIntentRequest(Data.Request::class.java))
        }
    }


    // MARK:- ====================== View
    /** Toolbar */
    private val mToolbar : CubeTestToolbar by lazy {
        findViewById(R.id.toolbar)
    }
    /** 資料列表 */
    private val mRecyclerView : CIRecyclerView by lazy {
        findViewById(R.id.list_content)
    }

    // MARK:- ====================== Data
    /** 資料配接器 */
    private val mListAdapter = object : CIRecyclerViewAdapter<LanguageListViewHolder, LanguageDetail>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageListViewHolder {

            val languageListViewHolder = LanguageListViewHolder(parent.context)
            //onLanguageListItemClick
            languageListViewHolder.itemView.setOnClickListener {
                onLanguageListItemClick(getItem(languageListViewHolder.adapterPosition))
            }

            return languageListViewHolder
        }

        override fun onBindViewHolder(holder: LanguageListViewHolder, position: Int) {

            val languageDetail = getItem(position)

            holder.loadData(languageDetail)
        }



    }
    // MARK:- ====================== Event
    /** 點擊 toolbar的返回 */
    private fun onBackClick(){
        finish()
    }
    /** 點擊任一語言 */
    private fun onLanguageListItemClick(languageDetail: LanguageDetail){
        CubeTestApplication.instance().mCubeTestManager.mLanguageDetail = languageDetail

        setResult(RESULT_OK)

        finish()

        /*
            val locale = Locale("vi") // 新的語言設為越南語
            Locale.setDefault(locale)
            val configuration = Configuration()
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
            recreate() // 重新載入 Activity 以應用新的語言

        val locale = Locale("vi")
        val res: Resources = this.resources
        val dm: DisplayMetrics = res.displayMetrics
        val conf: Configuration = res.configuration.apply {
            setLocale(locale)
        }
        res.updateConfiguration(conf, dm)
        this.onConfigurationChanged(conf)
        setResult(RESULT_OK)
        finish()*/
    }
    // MARK:- ====================== Method
    override fun loadData(request: Data.Request?) {
        super.loadData(request)
        //將R.raw.language的資料套到列表中
        val languageRawStr = CIUntil.byRawResource(this@LanguageListActivity,R.raw.language)
        val languageRawList :MutableList<LanguageDetail> =
            Gson().fromJson(languageRawStr,object : TypeToken<List<LanguageDetail>>(){}.type)
        mListAdapter.setItemList(languageRawList)
    }
    // MARK:- ====================== LanguageListViewHolder
    class LanguageListViewHolder(context: Context) : RecyclerView.ViewHolder(View.inflate(context,R.layout.item_language_list,null)) {
        init {

        }
        private val mLanguageTextView : CITextView by lazy {
            itemView.findViewById(R.id.languageTextView)
        }

        fun loadData(languageDetail: LanguageDetail){
            mLanguageTextView.text = languageDetail.mName
        }



    }
    // MARK:- ====================== Class Data
    class Data : FeatureData<Data.Request,Data.Response>(Request(),Response()){

        class Request(){

        }
        class Response(){

        }
    }
}