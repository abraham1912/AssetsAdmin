package com.shuimuhuatong.assetsadmin.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission

class SplashActivity : BaseActivity() {
    override fun initView() {
       Handler().postDelayed(Runnable {
           startActivity(Intent(this,MainActivity::class.java))
           finish()
       },1500);
    }

    override fun initData() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }


}
