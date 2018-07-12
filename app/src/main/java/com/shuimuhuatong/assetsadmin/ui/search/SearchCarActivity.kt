package com.shuimuhuatong.assetsadmin.ui.search

import android.os.Bundle
import com.chad.library.adapter.base.BaseQuickAdapter
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.adapter.SearchCarAdapter
import kotlinx.android.synthetic.main.activity_lib.*
import kotlinx.android.synthetic.main.tool_bar.*

class SearchCarActivity : BaseSearchActivity() {
    /**
     * 选择车辆
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        super.initView()
        initToolBar(toolbar,true,"选择车辆")
    }

    override fun initData() {
        super.initData()
        var mdAdapter = SearchCarAdapter(R.layout.item_search_car,null)
        recyclerView.adapter = mdAdapter
        mdAdapter.setOnLoadMoreListener(BaseQuickAdapter.RequestLoadMoreListener {
            fetchData()
        },recyclerView)
    }

    override fun fetchData() {   //请求数据

    }

    companion object {
        val SEARCH_CAR = 1
    }
}
