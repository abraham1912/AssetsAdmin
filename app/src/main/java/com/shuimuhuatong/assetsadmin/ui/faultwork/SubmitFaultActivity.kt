package com.shuimuhuatong.assetsadmin.ui.faultwork

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.ui.fragment.CarAreaFragment
import kotlinx.android.synthetic.main.activity_submit_fault.*
import kotlinx.android.synthetic.main.tool_bar.*

class SubmitFaultActivity : BaseActivity() {
    override fun initView() {
        initToolBar(toolbar,true,"车辆问题")
        save.text = "提交"
        save.visibility = View.VISIBLE
        var selectCarArea = CarAreaFragment.newInstance("","")
        var begin = supportFragmentManager.beginTransaction()
        begin.replace(R.id.content,selectCarArea)
        begin.commit()
    }

    override fun initData() {
        save.setOnClickListener {  }
        addError.setOnClickListener {  }
        deleteError.setOnClickListener {
            var mIntent = Intent(this,DeleteFaultActivity::class.java)
            startActivity(mIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_fault)
    }
}
