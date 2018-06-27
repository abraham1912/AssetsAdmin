package com.common.wangchong.commonutils.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by wangchong on 2018/6/21 11:00
 */
public class BrowserUpdateApp {
    /**
     * 打开浏览器更新下载新版本apk
     * @param apkUrl    apk托管地址
     */
    public static void openBrowserUpdate(@NonNull String apkUrl, @NonNull Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri apk_url = Uri.parse(apkUrl);
        intent.setData(apk_url);
        context.startActivity(intent);//打开浏览器
    }
}
