package com.gordon.module_first

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class InterceptRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun fling(velocityX: Int, velocityY: Int): Boolean {
        val dx = (velocityX * 0.5).toInt()
        return super.fling(dx, velocityY)
    }
}