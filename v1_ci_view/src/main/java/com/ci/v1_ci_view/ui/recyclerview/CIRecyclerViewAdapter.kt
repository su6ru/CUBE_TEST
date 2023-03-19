package com.ci.v1_ci_view.ui.recyclerview

import androidx.recyclerview.widget.RecyclerView
/** RecyclerViewAdapter */
abstract class CIRecyclerViewAdapter <THolder : RecyclerView.ViewHolder, TObject> : RecyclerView.Adapter<THolder>() {

    var mObjectList = ArrayList<TObject>()
    /** 取得adapter Size */
    override fun getItemCount(): Int {
        return mObjectList.size
    }

    /** get */
    fun getItem(position: Int) : TObject {
        return mObjectList[position]
    }
    fun getItemList() : List<TObject> {
        return mObjectList
    }
    /** set */
    fun setItemList(objectList : List<TObject>){
        mObjectList.clear()

        mObjectList.addAll(objectList)

        notifyDataSetChanged()
    }
    /** add */
    fun addItem(tObject : TObject){

        val startPosition = mObjectList.size

        mObjectList.add(tObject)

        notifyItemRangeChanged(startPosition,mObjectList.size)
    }
    fun addItemList(objectList : List<TObject>){
        val starPosition = mObjectList.size

        mObjectList.addAll(objectList)

        notifyItemRangeChanged(starPosition,mObjectList.size)
    }
    /** remove */
    fun removeItem(position : Int){
        mObjectList.removeAt(position)

        notifyItemRemoved(position)
    }
    fun removeItem(item : TObject){
        val index = mObjectList.indexOf(item)

        if (index != -1){
            mObjectList.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}