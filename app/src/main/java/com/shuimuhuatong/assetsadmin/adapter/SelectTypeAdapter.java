package com.shuimuhuatong.assetsadmin.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuimuhuatong.assetsadmin.R;
import com.shuimuhuatong.assetsadmin.bean.AppInit;
import com.shuimuhuatong.assetsadmin.bean.SelectItem;
import com.shuimuhuatong.assetsadmin.bean.VehicleType;

import java.util.List;

/**
 * Created by wangchong on 2018/6/14 17:37
 */
public class SelectTypeAdapter extends BaseQuickAdapter<VehicleType,BaseViewHolder> {
    public SelectTypeAdapter(int layoutResId, @Nullable List<VehicleType> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VehicleType item) {
        helper.setText(R.id.checkText,item.VEHICLE_TYPE_NAME);
            helper.setChecked(R.id.checkText,item.isChecked) ;
    }
}
