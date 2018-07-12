package com.shuimuhuatong.assetsadmin.ui.task

import com.shuimuhuatong.assetsadmin.bean.MyCity

/**
 * Created by wangchong on 2018/7/3 16:31
 */
class TaskPresenter : ITask.Presenter {
    override fun doSetAdapter(dataBeen: MutableList<MyCity>?) {

    }

    var view : ITask.View? = null

    constructor(view: ITask.View?) {
        this.view = view
    }

    override fun doRefresh() {

    }

    override fun doLoadData(vararg category: String?) {

    }

    override fun doLoadMoreData() {

    }

    override fun doShowNoMore() {

    }

    override fun doShowNetError() {

    }
}