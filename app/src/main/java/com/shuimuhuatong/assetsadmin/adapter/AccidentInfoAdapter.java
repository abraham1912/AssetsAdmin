package com.shuimuhuatong.assetsadmin.adapter;

import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuimuhuatong.assetsadmin.R;
import com.shuimuhuatong.assetsadmin.bean.AccidentCarInfo;
import com.shuimuhuatong.assetsadmin.bean.MyCity;

import java.util.List;

/**
 * Created by wangchong on 2018/6/14 17:37
 */
public class AccidentInfoAdapter extends BaseQuickAdapter<AccidentCarInfo,BaseViewHolder> {
    private List<AccidentCarInfo> datas ;
    private OnDriverImageClicked onDriverImageClicked ;
    public interface  OnDriverImageClicked{
        void onDriverImage(AccidentCarInfo info,int position,ImageView imageView);
    }

    public void setOnDriverImageClicked(OnDriverImageClicked onDriverImageClicked){
        this.onDriverImageClicked = onDriverImageClicked ;
    }



    public AccidentInfoAdapter(int layoutResId, @Nullable List<AccidentCarInfo> data) {
        super(layoutResId, data);
        this.datas = data ;
    }

    @Override
    protected void convert(BaseViewHolder helper, AccidentCarInfo item) {
        int position = helper.getLayoutPosition();
        helper.setText(R.id.license,item.license);
        helper.setText(R.id.name,item.name);
        helper.setText(R.id.phone,item.phone);
        EditText license = helper.getView(R.id.license);
        license.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                  item.license = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText name = helper.getView(R.id.name);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.name = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        EditText phone = helper.getView(R.id.phone);
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                item.phone = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        if (item.driverPhoto!=null){
            helper.setImageBitmap(R.id.driverImage,item.driverPhoto) ;
        }else {
            helper.setImageResource(R.id.driverImage,R.mipmap.driver) ;
        }
        ImageView iv = helper.getView(R.id.driverImage);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDriverImageClicked!=null){
                    onDriverImageClicked.onDriverImage(item,position,iv);
                }
            }
        });
        helper.getView(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (datas.size()>1){
                    datas.remove(item);
                    refresh(datas);
                }
            }
        });
    }

    public void refresh(List<AccidentCarInfo> datas){
        this.datas = datas ;
        notifyDataSetChanged();
    }
}
