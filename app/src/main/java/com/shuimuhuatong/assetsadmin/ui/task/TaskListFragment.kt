package com.shuimuhuatong.assetsadmin.ui.task


import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.common.wangchong.commonutils.base.BaseListFragment
import com.common.wangchong.commonutils.utils.DigestUtils
import com.common.wangchong.commonutils.utils.HandlerHttpError
import com.common.wangchong.commonutils.utils.RetrofitFactory

import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.adapter.TaskListAdapter
import com.shuimuhuatong.assetsadmin.api.ITaskApi
import com.shuimuhuatong.assetsadmin.AppConsts
import com.shuimuhuatong.assetsadmin.AssetsAdmin
import com.shuimuhuatong.assetsadmin.bean.*
import com.shuimuhuatong.assetsadmin.ui.other.CarDetailActivity
import com.shuimuhuatong.assetsadmin.view.SelectDialog
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "SEARCH_STRING"
private const val ARG_PARAM2 = "ORDER_TYPE"
private const val ARG_PARAM3 = "IS_PRE_DO"

/**
 * 任务列表
 */
class TaskListFragment : BaseListFragment<ITask.Presenter>() ,ITask.View, BaseQuickAdapter.RequestLoadMoreListener {
    override fun onLoadMoreRequested() {
        fetchData()
    }


    var mAppInit = AssetsAdmin.getInstance().appInit
    private val milesItmes = arrayListOf<SelectItem>()
    private val timeItmes = arrayListOf<SelectItem>()

    private val orderStatus = arrayListOf<OrderStatus>()
    private val vehicleTypes = arrayListOf<VehicleType>()
    private var  mAdapter : TaskListAdapter? = null

    var datas : MutableList< Task.ContentBean.QueryVehicleListBean> = arrayListOf()
    override fun setPresenter(presenter: ITask.Presenter?) {
        if (presenter==null){
            this.presenter = TaskPresenter(this)
        }
    }


    override fun onLoadData() {

    }



    override fun initData() {
        milesItmes.add(SelectItem("0-30km", "1", false))
        milesItmes.add(SelectItem("30-50km", "1", false))
        milesItmes.add(SelectItem("50-100km", "1", false))
        milesItmes.add(SelectItem(">=100km", "1", false))

        timeItmes.add(SelectItem("0-2小时", "1", false))
        timeItmes.add(SelectItem("2-6小时", "2", false))
        timeItmes.add(SelectItem("6-12小时", "3", false))
        timeItmes.add(SelectItem("12-24小时", "4", false))
        timeItmes.add(SelectItem(">=24小时", "5", false))

        for (item in mAppInit!!.orderStatus){
            orderStatus.add(OrderStatus(item.CODE_NAME,item.CODE_VALUE,false))
        }
        for (item in mAppInit!!.vehicleType){
            vehicleTypes.add(VehicleType(item.ID,item.VEHICLE_TYPE_NAME,false))
        }
        arguments.let {
            searchString = it?.getString(ARG_PARAM1)
            orderType =  it?.getString(ARG_PARAM2)
            isPreDo = it?.getBoolean(ARG_PARAM3)!!
        }

        onShowLoading()
    }

    override fun onShowNetError() {

    }

    override fun onShowNoMore() {

    }

    override fun showChooseDialog() {
        var dialog = SelectDialog(mContext,R.style.Dialog_Transparent,milesItmes,timeItmes,
                isPreDo,orderStatus,vehicleTypes)
        dialog.show()
        dialog.setOnSeleteFinished { vehicleTypes, orderStatus, milesItems, timeItems ->
            fetchData()
        }
    }

    override fun fetchData() {
        super.fetchData()
        var map = hashMapOf<String,String>()
        var carTypeIDs : MutableList<String> = arrayListOf()
        for (item in vehicleTypes){
            if(item.isChecked){
                carTypeIDs.add(item.ID)
            }
        }
        var orderStatu : MutableList<String> = arrayListOf()
        for (item in orderStatus){
            if (item.isChecked){
                orderStatu.add(item.CODE_VALUE)
            }
        }

        if (carTypeIDs.size!=0){
            var v1 = carTypeIDs.toString().replace("[", "", true)
            var types = v1.replace("]", "", true)
            map["vehicleTypes"] = types
        }

        if (orderStatu.size !=0){
            var v1 = orderStatu.toString().replace("[", "", true)
            var types = v1.replace("]", "", true)
            map["orderStatus"] = types
        }

        for (item in milesItmes){
            if (item.isChecked){
                map["enduranceType"] = item.value
            }
        }

        for (item in timeItmes){
            if (item.isChecked){
                map["continueType"] = item.value
            }
        }

        if (!TextUtils.isEmpty(searchString)){
            map["license"] = searchString!!
        }

        if("0" != orderType){
            map["orderType"] = orderType!!
        }
        map["pageNum"] = pageNo.toString()
        map["pageSize"] = AppConsts.PAGE_SIZE.toString()
        map["sign"] = DigestUtils.makeVeriSign(map)
        RetrofitFactory.getRetrofit().create(ITaskApi::class.java).queryTask(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .`as`(this.bindAutoDispose())
                .subscribe({ task ->
                    count.text = "您有共"+task.content.queryVehicleNum+"辆车未处理"
                    pageNo ++
                    run {
                        if (swipeRefreshLayout.isRefreshing){  //上拉刷新
                            datas = task.content.queryVehicleList
                            onHideLoading()
                        }else{
                            datas.addAll(task.content.queryVehicleList)
                        }

                        if (mAdapter==null){
                            mAdapter = TaskListAdapter(R.layout.item_task_list, datas)
                            mAdapter?.setOnLoadMoreListener(this,recyclerView)
                            recyclerView?.adapter = mAdapter
                            mAdapter?.setOnItemClickListener { adapter, view, position ->
                                var mIntent = Intent(mContext,CarDetailActivity::class.java)
                                mIntent.putExtra("orderNo",datas.get(position).orderNo)
                                startActivity(mIntent)
                            }
                        }else{
                            mAdapter?.notifyDataSetChanged()
                        }

                        if (datas.size<AppConsts.PAGE_SIZE){
                            //加载结束
                            mAdapter?.loadMoreEnd()
                        }else{
                            mAdapter?.loadMoreComplete()
                        }

                    }
                }, { e ->
                    HandlerHttpError.handlerNetError(e)
                    onHideLoading()
                    mAdapter?.loadMoreFail()
                })
    }


    override fun onSetAdapter(list: MutableList<*>?) {

    }

    private var searchString: String? = null
    private var orderType: String? = null
    var isPreDo : Boolean = false

     var mView : View? = null

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String,isPreDo:Boolean) =
                TaskListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                        putBoolean(ARG_PARAM3, isPreDo)
                    }
                }
    }
}
