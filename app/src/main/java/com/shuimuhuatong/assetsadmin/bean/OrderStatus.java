package com.shuimuhuatong.assetsadmin.bean;

/**
 * Created by wangchong on 2018/7/11 11:15
 */
public class OrderStatus {
    public OrderStatus(String CODE_NAME, String CODE_VALUE, boolean isChecked) {
        this.CODE_NAME = CODE_NAME;
        this.CODE_VALUE = CODE_VALUE;
        this.isChecked = isChecked;
    }

    public String CODE_NAME ;
    public String CODE_VALUE ;
    public boolean isChecked ;
}
