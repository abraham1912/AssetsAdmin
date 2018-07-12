package com.shuimuhuatong.assetsadmin.bean;

/**
 * Created by wangchong on 2018/7/3 10:57
 */
public class SelectItem {
    public String name ;
    public String value ;
    public boolean isChecked ;

    public SelectItem(String name, String value, boolean isChecked) {
        this.name = name;
        this.value = value;
        this.isChecked = isChecked;
    }
}
