package com.shuimuhuatong.assetsadmin.ui.faultwork

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.ui.fragment.SelectPhotoFragment
import kotlinx.android.synthetic.main.activity_select_error.*
import kotlinx.android.synthetic.main.tool_bar.*

class SelectErrorActivity : BaseActivity() {
    override fun initView() {
        initToolBar(toolbar, true, "发起故障工单")
        save.visibility = View.VISIBLE
        var selectCarArea = SelectPhotoFragment.newInstance("", "")
        var begin = supportFragmentManager.beginTransaction()
        begin.replace(R.id.content, selectCarArea)
        begin.commit()
    }

    override fun initData() {
        var areaText = intent.getStringExtra("area")
        area.text = areaText
        license.text = ""
        save.setOnClickListener {
            var mIntent = Intent(this,SubmitFaultActivity::class.java)
            startActivity(mIntent)
        }

        addError.setOnClickListener {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_error)
    }
}
