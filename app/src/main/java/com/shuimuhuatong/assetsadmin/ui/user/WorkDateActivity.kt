package com.shuimuhuatong.assetsadmin.ui.user

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import cn.aigestudio.datepicker.bizs.calendars.DPCManager
import cn.aigestudio.datepicker.bizs.decors.DPDecor
import cn.aigestudio.datepicker.bizs.themes.DPTManager
import cn.aigestudio.datepicker.cons.DPMode
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.view.DateTheme
import kotlinx.android.synthetic.main.activity_work_date.*
import org.jetbrains.anko.dip
import org.jetbrains.anko.sp
import java.util.*


class WorkDateActivity : BaseActivity() {
    /**
     * 排班表
     */
    override fun initView() {
        picker.setDate(2018,6)
        picker.setMode(DPMode.NONE)
       //picker.setDeferredDisplay(false)
       // picker.setHolidayDisplay(false)
      //  picker.setFestivalDisplay(false)
        val tmp = arrayListOf<String>()
        tmp.add("2018-6-21")
        tmp.add("2018-6-22")
        tmp.add("2018-6-23")
        DPCManager.getInstance().setDecorBG(tmp)
        picker.setDPDecor(object : DPDecor() {
            override fun drawDecorBG(canvas: Canvas?, rect: Rect?, paint: Paint?, data: String?) {
                super.drawDecorBG(canvas, rect, paint, data)
                paint?.color = resources.getColor(R.color.main_color)
                canvas?.drawCircle(rect!!.centerX().toFloat(), rect.centerY().toFloat(), rect.width() / 2f, paint)
                paint?.color = resources.getColor(R.color.white)
                paint?.textSize = sp(13).toFloat()
                canvas?.drawText("早班",rect!!.centerX().toFloat(),rect.bottom.toFloat()-dip(10),paint)
                //canvas?.drawText(data,rect!!.centerX().toFloat(),rect.bottom.toFloat()-dip(20),paint)
            }
        })
    }

    override fun initData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_date)
    }
}
