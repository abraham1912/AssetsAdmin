package com.shuimuhuatong.assetsadmin.ui.user

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import com.common.wangchong.commonutils.base.BaseActivity
import com.common.wangchong.commonutils.utils.DigestUtils
import com.common.wangchong.commonutils.utils.HandlerHttpError
import com.common.wangchong.commonutils.utils.RetrofitFactory
import com.shuimuhuatong.assetsadmin.AssetsAdmin
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.api.ILoginApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.toast


class LoginActivity : BaseActivity() {


    override fun initView() {
        //initToolBar(toolbar,false,"登录")
        login.setOnClickListener {
            if (TextUtils.isEmpty(phone.text) || TextUtils.isEmpty(password.text)){
                alert("请填写手机号/密码") {
//                    customTitle {
//                        verticalLayout {
//                            imageView(R.mipmap.ic_launcher)//方便的设置内容
//                            editText { hint = "hint_title" }
//                        }
//                    }
                    okButton {  }
                    //cancelButton { toast("button-cancel") }
                }.show()
                return@setOnClickListener
            }
            val map = hashMap
            map["phone"] = phone.text.trim().toString()
            map["password"] = password.text.trim().toString()
            map["sign"] = DigestUtils.makeVeriSign(map)
            RetrofitFactory.getRetrofit().create(ILoginApi::class.java).loginIn(map)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .`as`(this.bindAutoDispose())
                    .subscribe({ userInfo ->
                        run {
                            if (userInfo.ret != 0) {
                                toast(userInfo.msg)
                            }else{   //登录成功
                                AssetsAdmin.getInstance().userInfo = userInfo.content
                                finish()
                            }
                        }
                    }, { e -> HandlerHttpError.handlerNetError(e) })
        }

    }

    override fun initData() {
        back.setOnClickListener { finish() }
        forgetPsd.setOnClickListener { startActivity(Intent(this,PasswordActivity::class.java)) }
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }


}
