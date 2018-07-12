package com.shuimuhuatong.assetsadmin.ui.search

import android.os.Bundle
import kotlinx.android.synthetic.main.tool_bar.*

class LibActivity : BaseSearchActivity() {
    /**
     * 仓库
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {
        super.initView()
        initToolBar(toolbar,true,"仓库")
    }

    override fun initData() {
        super.initData()
    }

    override fun fetchData() {

    }



}
