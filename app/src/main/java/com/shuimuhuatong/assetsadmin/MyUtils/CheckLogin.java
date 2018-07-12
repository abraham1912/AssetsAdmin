package com.shuimuhuatong.assetsadmin.MyUtils;

import com.shuimuhuatong.assetsadmin.AssetsAdmin;

/**
 * Created by wangchong on 2018/7/6 14:27
 */
public class CheckLogin {
    public static boolean unLogin(){
        return AssetsAdmin.Companion.getInstance().getUserInfo().userPhone == null ;
    }
}
