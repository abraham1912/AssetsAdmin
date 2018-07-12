package com.shuimuhuatong.assetsadmin.ui.user

import android.app.Dialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import com.common.wangchong.commonutils.base.BaseActivity
import com.common.wangchong.commonutils.utils.BitmapUtils
import com.shuimuhuatong.assetsadmin.R
import kotlinx.android.synthetic.main.activity_update_user_info.*
import kotlinx.android.synthetic.main.tool_bar.*
import org.devio.takephoto.app.TakePhoto
import org.devio.takephoto.app.TakePhotoImpl
import org.devio.takephoto.compress.CompressConfig
import org.devio.takephoto.model.CropOptions
import org.devio.takephoto.model.InvokeParam
import org.devio.takephoto.model.TContextWrap
import org.devio.takephoto.model.TResult
import org.devio.takephoto.permission.InvokeListener
import org.devio.takephoto.permission.PermissionManager
import org.devio.takephoto.permission.TakePhotoInvocationHandler
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import java.io.File


class UpdateUserInfoActivity : BaseActivity(), TakePhoto.TakeResultListener, InvokeListener {
    override fun takeSuccess(result: TResult?) {
        var iconPath = result?.image?.originalPath
        var roundBitmap = BitmapUtils.toRoundBitmap(BitmapFactory.decodeFile(iconPath))
        avatar.setImageBitmap(roundBitmap)
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        toast(msg!!)
    }

    private var invokeParam : InvokeParam? = null

    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {
        var type= PermissionManager.checkPermission(TContextWrap.of(this),invokeParam!!.method)
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam = invokeParam
        }
        return type
    }


    override fun initView() {
        initToolBar(toolbar, true, "修改资料")
        save.visibility = View.VISIBLE
    }

    override fun initData() {
        mTakePhoto = getTakePhoto()
        imageUri = getImageCropUri()
        //设置裁剪参数
        cropOptions =  CropOptions.Builder().setAspectX(1).setAspectY(1).setWithOwnCrop(false).create()
        //设置压缩参数
        compressConfig= CompressConfig.Builder().setMaxSize(50*1024).setMaxPixel(800).create()
        mTakePhoto?.onEnableCompress(compressConfig,true) //设置为需要压缩

        phoneNum.setOnClickListener {
            startActivity(Intent(this, ChangePhoneActivity::class.java))
        }
        save.setOnClickListener {

        }

        changeCardView.setOnClickListener {
           showDialog()
        }
    }

    var alertDialog : Dialog? = null
    private var imageUri: Uri? = null //图片保存路径'
    private var cropOptions: CropOptions? = null  //裁剪参数
    private var compressConfig: CompressConfig? = null //压缩参数

    private fun showDialog() {
        val width = windowManager.defaultDisplay.width
        if (alertDialog==null){
            alertDialog = Dialog(this, R.style.Dialog_Transparent)
        }
        val params = alertDialog?.window?.attributes
        params?.width = width
        params?.horizontalWeight = 1f
        alertDialog?.window?.attributes = params
        alertDialog?.window?.decorView?.setPadding(0, 0, 0, 0)
        alertDialog?.window?.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        val view = View.inflate(this, R.layout.select_carmmer, null)
        view.find<TextView>(R.id.selectPhoto).setOnClickListener {
            alertDialog?.dismiss()
            mTakePhoto?.onPickFromGalleryWithCrop(imageUri,cropOptions)

        }
        view.find<TextView>(R.id.takePhoto).setOnClickListener {
            mTakePhoto?.onPickFromCaptureWithCrop(imageUri,cropOptions)
            alertDialog?.dismiss()

        }
        view.find<TextView>(R.id.cancel).setOnClickListener { alertDialog?.dismiss() }
        alertDialog?.addContentView(view, params)
        alertDialog?.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getTakePhoto().onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user_info)
    }

    //获得照片的输出保存Uri
    private fun getImageCropUri(): Uri {
        val file = File(Environment.getExternalStorageDirectory(), "/temp/" + "avatar" + ".jpg")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        return Uri.fromFile(file)
    }

    /**
     *  获取TakePhoto实例
     * @return
     */
    var mTakePhoto : TakePhoto? = null
    fun getTakePhoto() :TakePhoto {

        if (mTakePhoto==null){
            mTakePhoto= TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this,this)) as TakePhoto?
        }
        return mTakePhoto !!

    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        getTakePhoto().onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getTakePhoto().onActivityResult(requestCode,resultCode,data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //以下代码为处理Android6.0、7.0动态权限所需
        var type=PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults)
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this)

    }

}
