package com.shuimuhuatong.assetsadmin.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.widget.Toast
import com.baidu.location.BDLocation
import com.common.wangchong.commonutils.base.BaseActivity
import com.common.wangchong.commonutils.utils.HandlerHttpError
import com.common.wangchong.commonutils.utils.LocationManager
import com.common.wangchong.commonutils.utils.RxBus
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.ui.user.LoginActivity
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.okButton


class MainActivity : BaseActivity(), LocationManager.LocationCallBack {
    override fun onLocationResult(loc : BDLocation?) {

    }

    var observable : Observable<Boolean>? = null
    companion object {
        val TAG = "MainActivity"
    }
    override fun initView() {
        requestLocationPermission()
        requestStoragePermission()
        preDoRecyclerView.layoutManager = GridLayoutManager(this,3) as RecyclerView.LayoutManager?
        preDoRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        preGetRecyclerView.layoutManager = GridLayoutManager(this,3)as RecyclerView.LayoutManager?
        preGetRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
       // operateLib.setOnClickListener {  }  //准运库
       // lib.setOnClickListener {  }  //仓库
       // my.setOnClickListener {  }  //我的
//        button.setOnClickListener {
//            startActivity(Intent(this,TaskListActivity::class.java))
//        }
//        button2.setOnClickListener {
//            startActivity(Intent(this,HistoryListActivity::class.java))
//        }
//        button3.setOnClickListener {
//            startActivity(Intent(this,LoginActivity::class.java))
//        }
    }

    override fun initData() {
        observable = RxBus.getInstance().register(HandlerHttpError.TAG)
        observable?.subscribe { neadLogin ->
            if (neadLogin) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        leftButton.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }  //发起工单
        rightButton.setOnClickListener {  } //告警
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun requestStoragePermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .onGranted { permissions ->
                    // Storage permission are allowed
                }
                .onDenied { permissions ->
                    // Storage permission are not allowed.  不同意每次都会进入
                    if (AndPermission.hasAlwaysDeniedPermission(this, permissions)) {
                        val settingService = AndPermission.with(this).runtime().setting()
                        alert(R.string.permission_write_rationale){
                            okButton { settingService.start() }
                            cancelButton {  }
                        }.show()
                    }
                }
                .start()
    }

    private fun requestLocationPermission() {
        AndPermission.with(this)
                .runtime()
                .permission(Permission.Group.LOCATION)
                .onGranted { permissions ->
                    LocationManager.getInstance().startLocation(this)
                }
                .onDenied { permissions ->
                    // LOCATION permission are not allowed.  不同意每次都会进入
                    if (AndPermission.hasAlwaysDeniedPermission(this, permissions)) {
                        val settingService = AndPermission.with(this).runtime().setting()
                        alert(R.string.permission_location){
                            okButton { settingService.start() }
                            cancelButton {  }
                        }.show()
                    }
                }
                .start()
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.getInstance().unregister(HandlerHttpError.TAG,observable!!)
        LocationManager.getInstance().stopLocation(this)
        //System.exit(0)
    }

    private var exit = false
    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {
                exit = false
            }
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!exit) {
                exit = true
                handler.sendEmptyMessageDelayed(1, 2000)
                Toast.makeText(this, resources.getString(R.string.exit_application), Toast.LENGTH_SHORT).show()
                return true
            }
        }

        return super.onKeyUp(keyCode, event)
    }

}
