package com.common.wangchong.commonutils.base;

import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by wangchong on 2018/6/5 14:29
 */
public class BaseApplication extends MultiDexApplication {
    public static String currentCity = "北京" ;
    public static double currentLat = 0.0 ;
    public static double currentLng = 0.0 ;
    // 提供一个单例
    private static BaseApplication mApplication;

    @Override
    public void onCreate()
    {
        super.onCreate();
        mApplication = this;
        SDKInitializer.initialize(this);
    }

    /**
     * 取得Application单件
     *
     * @return
     */
    public static BaseApplication getInstance()
    {
        return mApplication;
    }
}
