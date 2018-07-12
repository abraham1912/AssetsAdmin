package com.shuimuhuatong.assetsadmin.api;

import com.shuimuhuatong.assetsadmin.bean.AppInit;
import com.shuimuhuatong.assetsadmin.bean.CarDetail;
import com.shuimuhuatong.assetsadmin.bean.Task;
import com.shuimuhuatong.assetsadmin.bean.UserInfo;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by wangchong on 2018/6/26 15:27
 */
public interface ITaskApi {
    @POST("/urcarAssets/work/queryPage")
    @FormUrlEncoded
    Observable<Task> queryTask(@FieldMap HashMap<String, String> params);

    @POST("/urcarAssets/work/queryOrderDetailedInfo")
    @FormUrlEncoded
    Observable<CarDetail> queryOrderDetailedInfo(@FieldMap HashMap<String, String> params);

}
