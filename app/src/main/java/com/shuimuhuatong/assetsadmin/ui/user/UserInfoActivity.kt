package com.shuimuhuatong.assetsadmin.ui.user


import android.content.Intent
import android.os.Bundle
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.tool_bar.*

class UserInfoActivity : BaseActivity() {
    /**
     * 我的
     */
    override fun initView() {
        initToolBar(toolbar,true,"我的")
    }

    override fun initData() {
        workDate.setOnClickListener { startActivity(Intent(this,WorkDateActivity::class.java)) }
        userMore.setOnClickListener { startActivity(Intent(this,UpdateUserInfoActivity::class.java)) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
    }

    companion object {
        val TAG = "UserInfoActivity"
    }
}
