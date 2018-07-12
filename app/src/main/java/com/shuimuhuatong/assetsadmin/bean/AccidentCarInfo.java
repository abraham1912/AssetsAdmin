package com.shuimuhuatong.assetsadmin.bean;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by wangchong on 2018/7/9 11:01
 */
public class AccidentCarInfo implements Serializable{
    public String license ;
    public String name ;
    public String phone ;
    public Bitmap driverPhoto ;

    public AccidentCarInfo(String license, String name, String phone, Bitmap driverPhoto) {
        this.license = license;
        this.name = name;
        this.phone = phone;
        this.driverPhoto = driverPhoto;
    }


}
