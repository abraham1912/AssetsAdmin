package com.shuimuhuatong.assetsadmin.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuimuhuatong.assetsadmin.R;
import com.shuimuhuatong.assetsadmin.bean.MyCity;
import com.shuimuhuatong.assetsadmin.bean.Task;

import java.util.List;

/**
 * Created by wangchong on 2018/6/14 17:37
 */
public class TaskListAdapter extends BaseQuickAdapter<Task.ContentBean.QueryVehicleListBean,BaseViewHolder> {
    public TaskListAdapter(int layoutResId, @Nullable List<Task.ContentBean.QueryVehicleListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Task.ContentBean.QueryVehicleListBean item) {
        helper.setText(R.id.license,item.getVehicleLiscense()+"/"+item.getVehicleTypeName());
        helper.setText(R.id.orderStatusName,item.getOrderStatusName());
        helper.setText(R.id.orderTypeName,item.getOrderTypeName());
        helper.setText(R.id.orderUpdateTime,"发起时间："+item.getOrderUpdateTime());
        helper.setText(R.id.orderOperateName,"发起人："+item.getOrderOperateName());
    }
}
