package com.common.wangchong.commonutils.base;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.common.wangchong.commonutils.R;
import com.jaeger.library.StatusBarUtil;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.HashMap;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        StatusBarUtil.setTransparent(this);
       // StatusBarUtil.setColor(this,getResources().getColor(R.color.white));
        initView();
        initData();

    }

    /**
     * 初始化 Toolbar
     */
    protected void initToolBar(@NonNull Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle("");
        TextView titleText = toolbar.findViewById(R.id.title);
        titleText.setText(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected abstract void initView() ;
    protected abstract void initData() ;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public HashMap<String,String> getHashMap(){
        return new HashMap<>() ;
    }


    protected void toast(int resourceId){
        Toast.makeText(this,resourceId,Toast.LENGTH_SHORT).show();
    }

    public <X> AutoDisposeConverter<X> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }


}
