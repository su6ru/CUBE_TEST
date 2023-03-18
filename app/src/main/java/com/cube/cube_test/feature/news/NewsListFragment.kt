package com.cube.cube_test.feature.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cube.cube_test.R
import com.cube.cube_test.custom.fragment.CubeTestFragment
/** 最新消息列表 */
class NewsListFragment : CubeTestFragment(R.layout.fragment_news_list) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return super.onCreateView(inflater, container, savedInstanceState)
    }

    /** 列表 */
}