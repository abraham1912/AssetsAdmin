package com.shuimuhuatong.assetsadmin

import android.support.v7.app.AlertDialog
import android.widget.Toast
import cn.aigestudio.datepicker.bizs.themes.DPTManager
import com.common.wangchong.commonutils.base.BaseApplication
import com.shuimuhuatong.assetsadmin.bean.AppInit
import com.shuimuhuatong.assetsadmin.bean.UserInfo
import com.shuimuhuatong.assetsadmin.view.DateTheme
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission


/**
 * Created by wangchong on 2018/6/13 10:49
 */
class AssetsAdmin : BaseApplication() {
    var userInfo : UserInfo.Content = UserInfo.Content()
    var appInit :AppInit.Content?= null
    override fun onCreate() {
        super.onCreate()
        assetsAdmin = this
        DPTManager.getInstance().initCalendar(DateTheme())  //日历主题
    }

    companion object {
        // 提供一个单例
        private var assetsAdmin: AssetsAdmin? = null
        fun getInstance(): AssetsAdmin {
            return assetsAdmin!!
        }
    }

}
