package com.shuimuhuatong.assetsadmin.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import com.common.wangchong.commonutils.base.BaseFragment

import com.shuimuhuatong.assetsadmin.R
import org.jetbrains.anko.find

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarAreaFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CarAreaFragment : BaseFragment(), CompoundButton.OnCheckedChangeListener {
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked){
            for (checkbox in checkboxs){
                if (checkbox.id!=buttonView?.id){
                    checkbox.isChecked = false
                }
            }
        }
    }

    /**
     * 选择车辆的问题区域
     */
    var checkboxs : MutableList<CheckBox> = arrayListOf()
    override fun attachLayoutId(): Int {
        return R.layout.fragment_car_area
    }

    override fun initView(view: View?) {
        var leftTop = view!!.find<CheckBox>(R.id.leftTop)
        var leftCenter = view!!.find<CheckBox>(R.id.leftCenter)
        var leftDown = view!!.find<CheckBox>(R.id.leftDown)

        var centerTop = view!!.find<CheckBox>(R.id.centerTop)
        var center = view!!.find<CheckBox>(R.id.center)
        var centerDown = view!!.find<CheckBox>(R.id.centerDown)

        var rightTop = view!!.find<CheckBox>(R.id.rightTop)
        var rightCenter = view!!.find<CheckBox>(R.id.rightCenter)
        var rightDown = view!!.find<CheckBox>(R.id.rightDown)
        checkboxs.add(leftTop)
        checkboxs.add(leftCenter)
        checkboxs.add(leftDown)
        checkboxs.add(centerTop)
        checkboxs.add(center)
        checkboxs.add(centerDown)
        checkboxs.add(rightTop)
        checkboxs.add(rightCenter)
        checkboxs.add(rightDown)

        var leftTopCount = view!!.find<TextView>(R.id.leftTopCount)
        var leftCenterCount = view!!.find<TextView>(R.id.leftCenterCount)
        var leftDownCount = view!!.find<TextView>(R.id.leftDownCount)

        var centerTopCount = view!!.find<TextView>(R.id.centerTopCount)
        var centerCount = view!!.find<TextView>(R.id.centerCount)
        var centerDownCount = view!!.find<TextView>(R.id.centerDownCount)

        var rightTopCount = view!!.find<TextView>(R.id.rightTopCount)
        var rightCenterCount = view!!.find<TextView>(R.id.rightCenterCount)
        var rightDownCount = view!!.find<TextView>(R.id.rightDownCount)

        for (checkbox in checkboxs){
            checkbox.isEnabled = true
            checkbox.setOnCheckedChangeListener(this)
        }

    }

    override fun initData() {

    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                CarAreaFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
