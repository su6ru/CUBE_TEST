package com.cube.cube_test.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AbsListView
import androidx.recyclerview.widget.RecyclerView
import com.ci.v1_ci_view.ui.recyclerview.CIRecyclerView
/** CubeTest工具類 */
class CubeTestUntil {
    companion object{
        /** 取得 RecyclerView 資料下一頁 頁碼*/
        fun getRecyclerViewNextPage(recyclerView: CIRecyclerView, status: Int, apiListSize: Int, nowListSize: Int, oldPage: String): String? {
            //當停止滑動
            if(status == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                //當滑到最底部
                if(!recyclerView.canScrollVertically(1)){

                    //當 API總比數 小於等於 現在列表資料數
                    if (apiListSize <= nowListSize){
                        return null
                    }
                    return (oldPage.toInt() + 1).toString()
                }
                return null
            }
            return null
        }

    }
}