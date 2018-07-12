package com.shuimuhuatong.assetsadmin.ui.task;

import com.common.wangchong.commonutils.base.IBaseListView;
import com.common.wangchong.commonutils.base.IBasePresenter;
import com.shuimuhuatong.assetsadmin.bean.MyCity;

import java.util.List;

/**
 * Created by wangchong on 2018/7/3 16:27
 */
public interface ITask {
    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();


    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String... category);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MyCity> dataBeen);

        /**
         * 加载完毕
         */
        void doShowNoMore();
    }
}
