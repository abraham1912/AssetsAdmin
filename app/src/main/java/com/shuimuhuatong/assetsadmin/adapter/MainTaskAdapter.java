package com.shuimuhuatong.assetsadmin.adapter;

import android.support.annotation.Nullable;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuimuhuatong.assetsadmin.R;
import com.shuimuhuatong.assetsadmin.bean.MyCity;

import java.util.List;

/**
 * Created by wangchong on 2018/6/14 17:37
 */
public class MainTaskAdapter extends BaseQuickAdapter<MyCity,BaseViewHolder> {
    public MainTaskAdapter(int layoutResId, @Nullable List<MyCity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCity item) {
        //helper.setText(R.id.name,item.name);
        helper.setChecked(R.id.checkImage,true);
        helper.setChecked(R.id.checkText,true);
        CheckBox checkBox = helper.getView(R.id.checkImage);
        //checkBox.setButtonDrawable();

    }
}
