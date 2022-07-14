package com.gordon.module_first

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.ToastUtils
import com.gordon.module_first.databinding.ActivityFirstBinding
import com.gordon.module_first.databinding.ActivityFirstBinding.*
import com.gordon.module_first.databinding.ItemBottomBinding

/**
 * @author gaofeng
 */
class FirstActivity : AppCompatActivity() {
    lateinit var binding: ActivityFirstBinding
    lateinit var horizonAdapter: HorizonAdapter
    lateinit var bottomBinding: ItemBottomBinding
    private val scrollListener: EndlessRecyclerOnScrollListener by lazy {
        object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                ToastUtils.showShort("滑动到底部了")
            }

            override fun updateBottomAppear() {
                bottomBinding.tvBottom.setText("释放更多")

            }

            override fun updateBottomDisappear() {
                bottomBinding.tvBottom.setText("查看更多")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        bottomBinding = ItemBottomBinding.inflate(layoutInflater)
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(this@FirstActivity, RecyclerView.HORIZONTAL, false)
            horizonAdapter = HorizonAdapter(mutableListOf())
            adapter = horizonAdapter
            addOnScrollListener(scrollListener)
        }
        updateData()
    }


    private fun updateData() {
        val dataList = mutableListOf<String>()
        (1..15).forEach {
            dataList.add(it.toString())
        }
        horizonAdapter.setList(dataList)
        if (horizonAdapter.itemCount > 12) {
            horizonAdapter.setFooterView(bottomBinding.root, -1, LinearLayout.HORIZONTAL)
            scrollListener.needLoadMore(true)
        } else {
            scrollListener.needLoadMore(false)
            horizonAdapter.removeAllFooterView()
        }
    }
}