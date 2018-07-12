package com.shuimuhuatong.assetsadmin.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.baidu.location.BDLocation
import com.common.wangchong.commonutils.base.BaseActivity
import com.common.wangchong.commonutils.utils.*
import com.shuimuhuatong.assetsadmin.AppConsts
import com.shuimuhuatong.assetsadmin.AssetsAdmin
import com.shuimuhuatong.assetsadmin.MyUtils.CheckLogin
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.adapter.MainTaskAdapter
import com.shuimuhuatong.assetsadmin.api.ILoginApi
import com.shuimuhuatong.assetsadmin.bean.MyCity
import com.shuimuhuatong.assetsadmin.ui.accidentwork.StartAccidentWorkActivity
import com.shuimuhuatong.assetsadmin.ui.faultwork.StartFaultWorkActivity
import com.shuimuhuatong.assetsadmin.ui.search.LibActivity
import com.shuimuhuatong.assetsadmin.ui.search.PreLibActivity
import com.shuimuhuatong.assetsadmin.ui.task.TaskListActivity
import com.shuimuhuatong.assetsadmin.ui.user.LoginActivity
import com.shuimuhuatong.assetsadmin.ui.user.UserInfoActivity
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.find
import org.jetbrains.anko.okButton


class MainActivity : BaseActivity(), LocationManager.LocationCallBack, View.OnClickListener {
    override fun onClick(v: View?) {
        if (CheckLogin.unLogin()){
            startActivity(Intent(this,LoginActivity::class.java))
        }else{
            var id = v!!.id
            when(id){
                R.id.my ->{ startActivity(Intent(this,UserInfoActivity::class.java))}  //我的
                R.id.lib ->{startActivity(Intent(this,LibActivity::class.java))}  //仓库
                R.id.rightButton ->{}  //告警
                R.id.operateLib ->{startActivity(Intent(this, PreLibActivity::class.java))} //准运库
                R.id.leftButton ->{  //发起工单
                    var dialog = Dialog(this)
                    var view = View.inflate(this,R.layout.dialog_work,null)
                    view.find<ImageView>(R.id.dismiss).setOnClickListener { dialog.dismiss() }
                    view.find<TextView>(R.id.breakWork).setOnClickListener { startActivity(Intent(this,StartFaultWorkActivity::class.java))}
                    view.find<TextView>(R.id.accidentWork).setOnClickListener {startActivity(Intent(this, StartAccidentWorkActivity::class.java)) }
                    view.find<TextView>(R.id.helpWork).setOnClickListener { }
                    view.find<TextView>(R.id.cardWork).setOnClickListener { }
                    view.find<TextView>(R.id.controlWork).setOnClickListener { }
                    dialog.setContentView(view)
                    dialog.setCancelable(false)
                    dialog.show()
                }
                R.id.preDidTask ->{   //待处理任务
                    var mIntent = Intent(this,TaskListActivity::class.java)
                    mIntent.putExtra("isPreDo",true)
                    startActivity(mIntent)
                }
                R.id.preGetTask ->{  //待领取任务
                    var mIntent = Intent(this,TaskListActivity::class.java)
                    mIntent.putExtra("isPreDo",false)
                    startActivity(mIntent)
                }
            }
        }
    }

    var intArray = intArrayOf(R.drawable.selector_task_item_break,R.drawable.selector_task_item_accident,
            R.drawable.selector_task_item_help,  R.drawable.selector_task_item_repair,
            R.drawable.selector_task_item_control,R.drawable.selector_task_item_card)
    override fun onLocationResult(loc : BDLocation?) {

    }

    var observable : Observable<Boolean>? = null
    companion object {
        val TAG = "MainActivity"
    }
    override fun initView() {
        requestLocationPermission()
        requestStoragePermission()
        swipeRefreshLayout.isRefreshing = true
        swipeRefreshLayout.setColorSchemeColors(resources.getColor(R.color.main_color))

        preDoRecyclerView.layoutManager = GridLayoutManager(this,3) as RecyclerView.LayoutManager?
        //preDoRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        preGetRecyclerView.layoutManager = GridLayoutManager(this,3)as RecyclerView.LayoutManager?
       //preGetRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

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

        my.setOnClickListener(this)
        lib.setOnClickListener(this)
        rightButton.setOnClickListener(this)
        leftButton.setOnClickListener(this)
        operateLib.setOnClickListener(this)

        preDidTask.setOnClickListener(this)
        preGetTask.setOnClickListener(this)

         swipeRefreshLayout.setOnRefreshListener {
             getData()
        }

        var citys = arrayListOf<MyCity>()
        for (i in 0 ..5){
            var city = MyCity()
            citys.add(city)
        }

        var preDidtaskAdapter = MainTaskAdapter(R.layout.item_main_task,citys)
        preDoRecyclerView.adapter = preDidtaskAdapter

        var preGettaskAdapter = MainTaskAdapter(R.layout.item_main_task,citys)
        preGetRecyclerView.adapter = preGettaskAdapter
    }

    fun getData(){
        Handler().postDelayed(Runnable {
            swipeRefreshLayout.isRefreshing = false
            var map = hashMap
            map["time"] = System.currentTimeMillis().toString()
            map["sign"] = DigestUtils.makeVeriSign(map)
            RetrofitFactory.getRetrofit().create(ILoginApi::class.java).initApp(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .`as`(this.bindAutoDispose())
                    .subscribe({ appInit ->
                        run {
                            AssetsAdmin.getInstance().appInit = appInit.content
                           if (appInit.content.alarmNum>0){
                               alarmCount.visibility = View.VISIBLE
                               alarmCount.text = appInit.content.alarmNum.toString()
                           }else{
                               alarmCount.visibility = View.GONE
                           }

                           if (appInit.content.waitProcess.size>0){
                               hasPreDid.visibility = View.VISIBLE
                           } else{
                               hasPreDid.visibility = View.GONE
                           }

                            if (appInit.content.waitReceive.size>0){
                                hasPreGet.visibility = View.VISIBLE
                            } else{
                                hasPreGet.visibility = View.GONE
                            }

                        }
                    }, { e -> HandlerHttpError.handlerNetError(e) })

        }, AppConsts.REFRESH_TIME)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        getData()
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
