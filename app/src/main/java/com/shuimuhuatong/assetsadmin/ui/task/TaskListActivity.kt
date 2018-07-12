package com.shuimuhuatong.assetsadmin.ui.task

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.AssetsAdmin
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.adapter.BasePagerAdapter
import com.shuimuhuatong.assetsadmin.bean.AppInit
import com.shuimuhuatong.assetsadmin.bean.SelectItem
import com.shuimuhuatong.assetsadmin.view.SelectDialog
import kotlinx.android.synthetic.main.activity_task_list.*
import kotlinx.android.synthetic.main.search_bar.*
import kotlinx.android.synthetic.main.tool_bar_search.*

class TaskListActivity : BaseActivity() {
    var titles = arrayOf("全部","故障","事故","救援","维修","风控","证件补办")
    private var fragmentList: ArrayList<Fragment>?= null

    private var adapter: BasePagerAdapter? = null

    var isPreDo : Boolean = false
    override fun initView() {
        initToolBar(toolbar,true,"任务列表")
        tabLayout.visibility = View.VISIBLE
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

    }

    override fun initData() {
        isPreDo = intent.getBooleanExtra("isPreDo",false)
        initTabs()
        adapter = BasePagerAdapter(supportFragmentManager, fragmentList, titles)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 15
//        choose.setOnClickListener {
//
//        }

        search.setOnClickListener {

        }
    }

    private fun initTabs() {
        fragmentList = arrayListOf()
        for (i in 0..6) {
            var fragment = TaskListFragment.newInstance("",i.toString(),isPreDo)
            fragmentList?.add(fragment)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
    }
}
