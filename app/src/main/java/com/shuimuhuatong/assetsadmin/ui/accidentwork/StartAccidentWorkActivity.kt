package com.shuimuhuatong.assetsadmin.ui.accidentwork

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.adapter.DutyItemAdapter
import com.shuimuhuatong.assetsadmin.bean.AccidentCarInfo
import com.shuimuhuatong.assetsadmin.bean.SelectItem
import com.shuimuhuatong.assetsadmin.ui.fragment.SelectPhotoFragment
import com.shuimuhuatong.assetsadmin.ui.search.SearchCarActivity
import kotlinx.android.synthetic.main.activity_start_accident_work.*
import kotlinx.android.synthetic.main.tool_bar.*
import java.util.ArrayList

class StartAccidentWorkActivity : BaseActivity(), BaseQuickAdapter.OnItemClickListener {
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        dutyTypes.get(position).isChecked = !dutyTypes.get(position).isChecked
        for (i in dutyTypes.indices) {
            if (i != position) {
                dutyTypes[i].isChecked = false
            }
        }
        mAdapter?.notifyDataSetChanged()

    }

    var dutyTypes = ArrayList<SelectItem>()
    var accidentInfos : ArrayList<AccidentCarInfo> = arrayListOf()

    override fun initView() {
        initToolBar(toolbar,true,"发起故障工单")
        var scenePhoto = SelectPhotoFragment.newInstance("", "")
        var dutyPhoto = SelectPhotoFragment.newInstance("", "")
        var begin = supportFragmentManager.beginTransaction()
        begin.replace(R.id.sceneContent, scenePhoto)
        begin.replace(R.id.dutyContent, dutyPhoto)
        begin.commit()

    }
    var mAdapter : DutyItemAdapter? = null
    override fun initData() {
        accidentInfos.add(AccidentCarInfo("","","",null))

        dutyTypes.add(SelectItem("无责","1",false))
        dutyTypes.add(SelectItem("全责","2",false))
        dutyTypes.add(SelectItem("主责","3",false))
        dutyTypes.add(SelectItem("次责","4",false))
        dutyTypes.add(SelectItem("等责","5",false))
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        mAdapter = DutyItemAdapter(R.layout.item_duty_checkbox,dutyTypes)
        recyclerView.adapter = mAdapter
        mAdapter!!.onItemClickListener = this

        selectCar.setOnClickListener {
            startActivityForResult(Intent(this,SearchCarActivity::class.java),CAR_REQUEST_CODE)
        }

        driverRel.setOnClickListener {
            startActivityForResult(Intent(this,DriverActivity::class.java),DRIVER_REQUEST_CODE)
        }

        accidentInfoRel.setOnClickListener {
            var mIntent = Intent(this,AddAccidentInfoActivity::class.java)
            var bundle = Bundle()
            bundle.putSerializable("infos",accidentInfos)
            mIntent.putExtra("bundle",bundle)
            startActivityForResult(mIntent,ADD_ACCIDENT_CODE)

        }

        repairInfoRel.setOnClickListener {
            var mIntent = Intent(this,RepairFactoryActivity::class.java)
            startActivityForResult(mIntent,REPAIR_FACTORY_CODE)
        }

        submit.setOnClickListener {   //提交工单

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAR_REQUEST_CODE){  //选择车辆返回
            license.text=""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_accident_work)
    }

    companion object {
        val CAR_REQUEST_CODE = 1
        val DRIVER_REQUEST_CODE = 2
        val ADD_ACCIDENT_CODE = 3
        val REPAIR_FACTORY_CODE = 4

    }
}
