package com.shuimuhuatong.assetsadmin.ui.faultwork

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.bean.FaultWork
import com.shuimuhuatong.assetsadmin.ui.search.SearchCarActivity
import kotlinx.android.synthetic.main.activity_start_fault_work.*
import kotlinx.android.synthetic.main.click_car_area.*
import kotlinx.android.synthetic.main.tool_bar.*

class StartFaultWorkActivity : BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        var id = v?.id
        var area = ""
        when(id){
            R.id.leftTop -> area = ""
            R.id.leftCenter -> area = ""
            R.id.leftDown -> area = ""

            R.id.centerTop -> area = ""
            R.id.center -> area = ""
            R.id.centerDown -> area = ""

            R.id.rightTop -> area = ""
            R.id.rightCenter -> area = ""
            R.id.rightDown -> area = ""
        }
        var mIntent = Intent(this,SelectErrorActivity::class.java)
        mIntent.putExtra("area",area)
        startActivity(mIntent)
    }

    var fault : FaultWork? = null

    /**
     * 发起故障工单选择车辆和区域
     */
    override fun initView() {
        initToolBar(toolbar, true, "发起故障工单")
        //save.visibility = View.VISIBLE
    }

    override fun initData() {
        var fromStartFaultWorkActivity = intent.getBooleanExtra("fromStartFaultWorkActivity",false)
        if(fromStartFaultWorkActivity){  //
            license.text = ""
            moreImage.visibility = View.GONE
            license.isEnabled = false
            fault = FaultWork()  //故障项
        }
            license.setOnClickListener {   //选择车辆
                startActivityForResult(Intent(this,SearchCarActivity::class.java),START_FAULT_WORK)
            }
        leftTop.setOnClickListener(this)
        leftCenter.setOnClickListener(this)
        leftDown.setOnClickListener(this)

        centerTop.setOnClickListener(this)
        center.setOnClickListener(this)
        centerDown.setOnClickListener(this)

        rightTop.setOnClickListener(this)
        rightCenter.setOnClickListener(this)
        rightDown.setOnClickListener(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_fault_work)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==START_FAULT_WORK && resultCode==SearchCarActivity.SEARCH_CAR){
            var mIntent = Intent(this,StartFaultWorkActivity::class.java)
            mIntent.putExtra("fromStartFaultWorkActivity",true)
            startActivity(Intent(mIntent))
        }
    }

    companion object {
        val START_FAULT_WORK = 1
    }
}
