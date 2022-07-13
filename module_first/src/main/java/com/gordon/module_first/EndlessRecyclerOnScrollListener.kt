package com.gordon.module_first

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

abstract class EndlessRecyclerOnScrollListener : RecyclerView.OnScrollListener() {
    //用来标记是否正在向左滑动
    private var isSlidingToLeft = false
    private var isNeedLoadMore = false
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
        // 当不滑动时
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            // 获取最后一个完全显示的itemPosition
            val lastItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()
            val itemCount = layoutManager.itemCount

            // 判断是否滑动到了最后一个Item，并且是向左滑动
            if (lastItemPosition == itemCount - 1 && isSlidingToLeft && isNeedLoadMore) {
                // 加载更多
                onLoadMore()
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        // dx值大于0表示正在向左滑动，小于或等于0表示向右滑动或停止
        isSlidingToLeft = dx > 0
        if (isNeedLoadMore) {
            val layoutManager = recyclerView.layoutManager as? LinearLayoutManager ?: return
            val lastItemPosition = layoutManager.findLastVisibleItemPosition()
            val itemCount = layoutManager.itemCount

            // 判断是否滑动到了最后一个Item，并且是向左滑动
            if (lastItemPosition == itemCount - 1) {
                if (dx > 50) {
                    updateBottomAppear()
                } else {
                    updateBottomDisappear()
                }
            }
        }
    }

    fun needLoadMore(need: Boolean) {
        isNeedLoadMore = need
    }

    /**
     * 加载更多回调
     */
    abstract fun onLoadMore()

    abstract fun updateBottomAppear()
    abstract fun updateBottomDisappear()
}