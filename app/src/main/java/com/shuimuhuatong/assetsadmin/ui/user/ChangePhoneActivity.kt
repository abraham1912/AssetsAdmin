package com.shuimuhuatong.assetsadmin.ui.user

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import kotlinx.android.synthetic.main.activity_change_phone.*
import kotlinx.android.synthetic.main.tool_bar.*

class ChangePhoneActivity : BaseActivity(), TextWatcher {
    /**
     * 修改手机号
     */
    override fun afterTextChanged(s: Editable?) {
        sendCode.isEnabled = newPhoneNum.text?.length==11 && oldPhoneNum.text?.length==11
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun initView() {
        initToolBar(toolbar,true,"修改手机号")
        save.visibility = View.VISIBLE
    }

    override fun initData() {
       oldPhoneNum.addTextChangedListener(this)
       newPhoneNum.addTextChangedListener(this)
        save.setOnClickListener {  }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_phone)
    }
}
