package com.common.wangchong.commonutils.utils;

import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.common.wangchong.commonutils.base.BaseApplication;

import java.util.ArrayList;

/**
 * Created by wangchong on 2018/6/20 15:10
 */
public class LocationManager extends BDAbstractLocationListener {

    private static LocationManager locationManager ;
    private LocationClient locationClient ;
    /**
     * 最好不要开启多次定位
     */
    private ArrayList<LocationCallBack> locationCallBacks ;
    @Override
    public void onReceiveLocation(BDLocation loc) {
        BaseApplication.currentCity = loc.getCity();
        BaseApplication.currentLat = loc.getLatitude();
        BaseApplication.currentLng = loc.getLongitude();
       for (LocationCallBack  callBack : locationCallBacks ){
           callBack.onLocationResult(loc);
       }
    }

    public interface LocationCallBack{
        void onLocationResult(BDLocation loc);
    }

    private LocationManager(){
        super();
        locationClient = new LocationClient(BaseApplication.getInstance());
        locationClient.registerLocationListener((BDAbstractLocationListener)this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd0911");
        option.setIsNeedAddress(true);
        option.setScanSpan(10 * 1000);
        locationClient.setLocOption(option);
        locationCallBacks = new ArrayList<>();
    }

    public static LocationManager getInstance(){
        if (locationManager== null){
            locationManager = new LocationManager();
        }
        return  locationManager ;
    }

    public void startLocation(LocationCallBack callBack){
        if (NetWorkUtil.isNetworkConnected(BaseApplication.getInstance())){
            if (!locationCallBacks.contains(callBack)){
                locationCallBacks.add(callBack);
            }
            locationClient.start();
        }else {
            Toast.makeText(BaseApplication.getInstance(),"网络连接异常,定位失败",Toast.LENGTH_SHORT).show();
        }
    }

    public void stopLocation (LocationCallBack callBack){
        locationClient.stop();
        locationClient.unRegisterLocationListener((BDAbstractLocationListener)this);
        locationCallBacks.remove(callBack);
    }


}
