package com.shuimuhuatong.assetsadmin.ui.other

import android.os.Bundle
import com.common.wangchong.commonutils.base.BaseActivity
import com.common.wangchong.commonutils.utils.DigestUtils
import com.common.wangchong.commonutils.utils.HandlerHttpError
import com.common.wangchong.commonutils.utils.RetrofitFactory
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.api.ILoginApi
import com.shuimuhuatong.assetsadmin.api.ITaskApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_car_detail.*
import kotlinx.android.synthetic.main.activity_user_info.*
import kotlinx.android.synthetic.main.tool_bar.*

class CarDetailActivity : BaseActivity() {
    /**
     * 车辆详情
     */

    var orderNo = ""

    override fun initView() {
        initToolBar(toolbar, true, "车辆详情")
    }

    override fun initData() {
        orderNo = intent.getStringExtra("orderNo")

        val map = hashMap
        map["orderNo"] = orderNo
        map["sign"] = DigestUtils.makeVeriSign(map)
        RetrofitFactory.getRetrofit().create(ITaskApi::class.java).queryOrderDetailedInfo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(this.bindAutoDispose())
                .subscribe({ carDetail ->
                    run {
                        val info = carDetail.content.orderDetailedInfo
                       license.text = info.vehicleLiscense+"/"+info.vehicleTypeName
                        status.text = info.vehicleOnlineStatus
                        continueText.text = "续航："+info.cruisingRadius+"km"
                        //carState.text = info.  是否熄火
                        miles.text = info.distance
                        time.text = info.orderUpdateTime
                        operator.text = "处理人"+info.orderOperateName
                        workType.text = info.orderTypeName
                        workStatus.text = info.orderStatusName
                        address.text = info.currentStationAddress
                    }
                }, { e -> HandlerHttpError.handlerNetError(e) })

        findCar.setOnClickListener { }
        openDoor.setOnClickListener { }
        closeDoor.setOnClickListener { }
        cutDian.setOnClickListener { }
        resumeDian.setOnClickListener { }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_detail)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()

    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()

    }
}
