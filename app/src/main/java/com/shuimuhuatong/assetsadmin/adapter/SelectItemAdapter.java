package com.shuimuhuatong.assetsadmin.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuimuhuatong.assetsadmin.R;
import com.shuimuhuatong.assetsadmin.bean.SelectItem;

import java.util.List;

/**
 * Created by wangchong on 2018/6/14 17:37
 */
public class SelectItemAdapter extends BaseQuickAdapter<SelectItem,BaseViewHolder> {
    public SelectItemAdapter(int layoutResId, @Nullable List<SelectItem> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectItem item) {
        helper.setText(R.id.checkText,item.name);
        helper.setChecked(R.id.checkText,item.isChecked) ;
    }
}
