package com.gordon.module_first

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

class HorizonAdapter(dataList: MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_horizon, dataList) {
    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_content, item)
    }
}