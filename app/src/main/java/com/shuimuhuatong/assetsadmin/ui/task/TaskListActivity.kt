package com.shuimuhuatong.assetsadmin.ui.task

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.View
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.adapter.BasePagerAdapter
import kotlinx.android.synthetic.main.activity_task_list.*
import kotlinx.android.synthetic.main.tool_bar_search.*

class TaskListActivity : BaseActivity() {

    private var fragmentList: ArrayList<Fragment>?= null
    private var titleList: ArrayList<String>? = null

    private var adapter: BasePagerAdapter? = null

    override fun initView() {
        initToolBar(toolbar,true,"123")
        tabLayout.visibility = View.VISIBLE
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE
    }

    override fun initData() {
        initTabs()
        adapter = BasePagerAdapter(supportFragmentManager, fragmentList, titleList)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 15
    }

    private fun initTabs() {
        fragmentList = arrayListOf()
        titleList= arrayListOf()
        for (i in 0..7) {
            var fragment = TaskListFragment.newInstance("fragment",i.toString())
            var title = "fragment".plus(i)
            fragmentList?.add(fragment)
            titleList?.add(title)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
    }
}
