package com.shuimuhuatong.assetsadmin.ui.user

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import kotlinx.android.synthetic.main.activity_password.*
import kotlinx.android.synthetic.main.tool_bar.*

class PasswordActivity : BaseActivity(), TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        sendCode.isEnabled = s?.length==11
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    override fun initView() {
        initToolBar(toolbar,false,"忘记密码")
        rightImage.visibility = View.VISIBLE

    }

    override fun initData() {
        next.setOnClickListener {
          startActivityForResult(Intent(this,NewPasswordActivity::class.java),1)
        }
        rightImage.setOnClickListener {
           finish()
        }

        phone.addTextChangedListener(this)

        sendCode.setOnClickListener {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1 && resultCode==1){
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
    }
}
