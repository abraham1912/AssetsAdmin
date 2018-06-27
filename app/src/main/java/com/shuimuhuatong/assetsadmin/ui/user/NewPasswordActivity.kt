package com.shuimuhuatong.assetsadmin.ui.user

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import kotlinx.android.synthetic.main.activity_new_password.*
import kotlinx.android.synthetic.main.tool_bar.*
import org.jetbrains.anko.toast

class NewPasswordActivity : BaseActivity() {
    override fun initView() {
        initToolBar(toolbar, false, "忘记密码")
        rightImage.visibility = View.VISIBLE
    }

    override fun initData() {
        rightImage.setOnClickListener {
            finish()
        }
        eyeCheck.setOnCheckedChangeListener { buttonView, isChecked ->
            run {
                if(isChecked){
                    //设置EditText的密码为可见的
                    password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                }else{
                    //设置密码为隐藏的
                    password.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }
        }
        submit.setOnClickListener {
            if (password.text.length<8){
                toast("您的密码太短")
                return@setOnClickListener
            }
            setResult(1)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_password)
    }

    companion object {
        val TAG = "NewPasswordActivity"
    }
}
