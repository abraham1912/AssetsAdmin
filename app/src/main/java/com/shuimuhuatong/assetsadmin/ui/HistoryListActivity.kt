package com.shuimuhuatong.assetsadmin.ui

import android.os.Bundle
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.adapter.TaskListAdapter
import com.shuimuhuatong.assetsadmin.bean.MyCity
import kotlinx.android.synthetic.main.list_pull.*

class HistoryListActivity : BaseActivity() {
    override fun initView() {
//        var citys = arrayListOf<MyCity>()
//        for (i in 0 ..10){
//            var city = MyCity()
//            city.name = "wangcong".plus(i)
//            citys.add(city)
//        }
//        var mAdapter = TaskListAdapter(R.layout.item_city, citys)
//        recyclerView.adapter = mAdapter
    }

    override fun initData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_pull)
    }
}
