package com.shuimuhuatong.assetsadmin.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuimuhuatong.assetsadmin.R;
import com.shuimuhuatong.assetsadmin.bean.AppInit;
import com.shuimuhuatong.assetsadmin.bean.OrderStatus;

import java.util.List;

/**
 * Created by wangchong on 2018/6/14 17:37
 */
public class SelectStatusAdapter extends BaseQuickAdapter<OrderStatus,BaseViewHolder> {
    public SelectStatusAdapter(int layoutResId, @Nullable List<OrderStatus> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderStatus item) {
        helper.setText(R.id.checkText,item.CODE_NAME);
        helper.setChecked(R.id.checkText,item.isChecked) ;
    }
}
