package com.common.wangchong.commonutils.utils;

import android.util.Log;
import android.widget.Toast;

import com.common.wangchong.commonutils.BuildConfig;
import com.common.wangchong.commonutils.base.BaseApplication;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class HandlerHttpError {
    public static String TAG = "HandlerHttpError" ;
    public static void handlerNetError(Throwable e) {
        String msg = "";
        if(e instanceof HttpException){
            if (BuildConfig.DEBUG){
                ResponseBody body = ((HttpException) e).response().errorBody();
                try {
                    Log.e("Throwable",body.string());
                } catch (IOException IOe) {
                    IOe.printStackTrace();
                }
            }
            int responseCode = ((HttpException) e).code();
            switch (responseCode){
                case 400 : //Bad Request/错误请求
                    msg = "请求错误" ;
                    break;
                case 401 : //Unauthorized/未授权
                    msg = "未登录" ;
                    RxBus.getInstance().post(HandlerHttpError.TAG,true);
                    break;
                case 404 : //Not Found/未找到
                    msg = "无法连接服务器" ;
                    break;
                case 408 : //Request Timeout/请求超时
                    msg = "网络连接异常" ;
                    break;
                case 500 : //Internal Server Error/内部服务器错误
                    msg = "服务器内部错误" ;
                    break;
                case 502 : //Bad Gateway/错误的网关
                    msg = "错误的网关" ;
                    break;
                default:
                    msg = "未知错误" ;
            }
        }else{
            msg = "未知异常" ;
        }
        Toast.makeText(BaseApplication.getInstance(),msg,Toast.LENGTH_SHORT).show();
    }
}
