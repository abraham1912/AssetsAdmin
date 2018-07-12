package com.shuimuhuatong.assetsadmin.ui.search

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.DividerItemDecoration
import android.text.TextUtils
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.AppConsts
import kotlinx.android.synthetic.main.activity_lib.*
import kotlinx.android.synthetic.main.search_bar.*

open class BaseSearchActivity : BaseActivity() {
    override fun initView() {
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.main_color))
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        getData()
    }

    override fun initData() {
        search.setOnClickListener {  //开始搜索
           if (!TextUtils.isEmpty(searchText.text)){

           }else{

           }
        }

        swipeRefreshLayout.setOnRefreshListener {
           getData()
        }
    }

    fun getData(){
        Handler().postDelayed(Runnable {
            swipeRefreshLayout.isRefreshing = false
            fetchData()
        }, AppConsts.REFRESH_TIME)
    }

    open fun fetchData(){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lib)
    }
}
