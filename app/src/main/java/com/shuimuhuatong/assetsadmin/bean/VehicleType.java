package com.shuimuhuatong.assetsadmin.bean;

/**
 * Created by wangchong on 2018/7/11 11:14
 */
public class VehicleType {
    public VehicleType(String ID, String VEHICLE_TYPE_NAME, boolean isChecked) {
        this.ID = ID;
        this.VEHICLE_TYPE_NAME = VEHICLE_TYPE_NAME;
        this.isChecked = isChecked;
    }

    public String ID ;
    public String VEHICLE_TYPE_NAME ;
    public boolean isChecked ;
}
