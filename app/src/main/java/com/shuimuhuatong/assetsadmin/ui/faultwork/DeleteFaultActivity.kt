package com.shuimuhuatong.assetsadmin.ui.faultwork

import android.os.Bundle
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import kotlinx.android.synthetic.main.tool_bar.*

class DeleteFaultActivity : BaseActivity() {
    override fun initView() {
        initToolBar(toolbar,true,"车辆问题")
    }

    override fun initData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_fault)
    }
}
