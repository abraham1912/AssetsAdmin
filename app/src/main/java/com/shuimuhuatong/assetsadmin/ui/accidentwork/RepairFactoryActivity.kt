package com.shuimuhuatong.assetsadmin.ui.accidentwork

import android.os.Bundle
import android.view.View
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import kotlinx.android.synthetic.main.tool_bar.*

class RepairFactoryActivity : BaseActivity() {
    override fun initView() {
       initToolBar(toolbar,true,"选择维修厂")
        save.visibility = View.VISIBLE
    }

    override fun initData() {
        save.setOnClickListener {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_unpull)
    }
}
