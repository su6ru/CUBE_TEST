package com.ci.v1_ci_view.ui.banner

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.ci.v1_ci_view.R
import com.ci.v1_ci_view.ui.imageview.CIImageView
import com.ci.v1_ci_view.ui.recyclerview.CIRecyclerViewAdapter
import com.ci.v1_ci_view.ui.textview.CITextView

class CIBannerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs){
    // MARK:-================= View
    private val mImagePageView : ViewPager2 by lazy {
        findViewById(R.id.page_image)
    }
    private val mProgressView : CITextView by lazy {
        findViewById(R.id.text_progress)
    }
    // MARK:-================= Data

    private var mSelectPositionLock: Boolean = false

    private var mSelectPosition = -1

    private val mImagePageAdapter = object :CIRecyclerViewAdapter<ImageHolder,String>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {

            val holder = ImageHolder(parent.context)

            holder.itemView.layoutParams = RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT)

            return holder
        }

        override fun onBindViewHolder(holder: ImageHolder, position: Int) {

            val item = getItem(position)

            holder.loadData(item)

        }
    }

    // MARK:-================= Event
    private fun onPageChangeClick(addPosition : Int){
        val size = mImagePageAdapter.itemCount
        if (size == 0){
            return
        }
        val nowPosition = mImagePageView.currentItem

        val newPosition = (nowPosition + (addPosition % size) + size) % size

        mImagePageView.currentItem = newPosition

        mProgressView.text = String.format("%d/%d",newPosition+1,size)
    }
    // MARK:-================= Method
    init {

        inflate(context, R.layout.view_ci_banner, this)

        mImagePageView.adapter = mImagePageAdapter
        //onPageChangeClick
        findViewById<CIImageView>(R.id.button_last).setOnClickListener {
            onPageChangeClick(-1)
        }
        //onPageChangeClick
        findViewById<CIImageView>(R.id.button_next).setOnClickListener {
            onPageChangeClick(1)
        }
    }

    public fun loadData(urlList : MutableList<String>,selectPosition : Int){
        mSelectPositionLock = true

        mImagePageAdapter.setItemList(urlList)

        if (0 <= selectPosition && selectPosition < urlList.size){

            mSelectPosition = selectPosition

            mImagePageView.setCurrentItem(selectPosition,false)
        }else{
            mSelectPosition = -1
        }
        updateSelectPositionText()

        mSelectPositionLock = false
    }

    fun updateSelectPositionText(){

        val size = mImagePageAdapter.itemCount

        mProgressView.text = String.format("%d/%d",mSelectPosition+1,size)
    }

    // MARK:-================= ImageHolder
    class ImageHolder(val context: Context) : RecyclerView.ViewHolder(CIImageView(context,null)) {
        init {
            getView().scaleType = ImageView.ScaleType.FIT_CENTER

        }

        public fun getView() : CIImageView{
            return itemView as CIImageView
        }

        fun loadData(data: Any){
           if (data is String){
               val url = data as String
               val imageView  = itemView as CIImageView
               Glide.with(context)
                   .load(url)
                   .into(imageView)
           }
        }



    }

}