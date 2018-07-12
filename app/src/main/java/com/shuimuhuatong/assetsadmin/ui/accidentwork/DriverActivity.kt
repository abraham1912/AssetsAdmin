package com.shuimuhuatong.assetsadmin.ui.accidentwork

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.view.View
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import kotlinx.android.synthetic.main.activity_dirver.*
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
import org.jetbrains.anko.toast
import java.io.File

class DriverActivity : BaseActivity() , TakePhoto.TakeResultListener, InvokeListener, View.OnClickListener {
    /**
     * 编辑驾驶人信息
     */
    override fun onClick(v: View?) {
        var id = v?.id
        when (id) {
            R.id.cardTop -> mTakePhoto?.onPickFromCaptureWithCrop(cardTopUri,cropOptions)
            R.id.cardBack -> mTakePhoto?.onPickFromCaptureWithCrop(cardBackUri,cropOptions)
            R.id.driverTop -> mTakePhoto?.onPickFromCaptureWithCrop(driverTopUri,cropOptions)
            R.id.driverBack -> mTakePhoto?.onPickFromCaptureWithCrop(driverBackUri,cropOptions)
        }

    }

    override fun takeSuccess(result: TResult?) {
        var iconPath = result?.image?.originalPath!!
        var bitmap = BitmapFactory.decodeFile(iconPath)
        when {
            iconPath.contains("cardTop") -> {
                cardTop.setImageBitmap(bitmap)
                map["cardTop"] = bitmap
            }
            iconPath.contains("cardBack") -> {
                cardBack.setImageBitmap(bitmap)
                map["cardBack"] = bitmap
            }
            iconPath.contains("driverTop") -> {
                driverTop.setImageBitmap(bitmap)
                map["driverTop"] = bitmap
            }
            iconPath.contains("driverBack") -> {
                driverBack.setImageBitmap(bitmap)
                map["driverBack"] = bitmap
            }
        }

    }


    var  map : HashMap<String,Bitmap> = HashMap()
    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        toast(msg!!)
    }

    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {
        var type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam!!.method)
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam = invokeParam
        }
        return type
    }

    override fun initView() {
     initToolBar(toolbar,true,"驾驶人信息")

    }

    override fun initData() {
        mTakePhoto = getTakePhoto()
        cardTopUri = getImageCropUri("cardTop")
        cardBackUri = getImageCropUri("cardTop")
        driverTopUri = getImageCropUri("driverTop")
        driverBackUri = getImageCropUri("driverBack")
        //设置裁剪参数
        cropOptions =  CropOptions.Builder().setAspectX(3).setAspectY(4).setWithOwnCrop(false).create()
        //设置压缩参数
        compressConfig= CompressConfig.Builder().setMaxSize(5*1024).setMaxPixel(800).create()
        mTakePhoto?.onEnableCompress(compressConfig,true) //设置为需要压缩
        cardTop.setOnClickListener(this)
        cardBack.setOnClickListener(this)
        driverTop.setOnClickListener(this)
        driverBack.setOnClickListener(this)

    }

    private var cardTopUri: Uri? = null //图片保存路径'
    private var cardBackUri: Uri? = null //图片保存路径'
    private var driverTopUri: Uri? = null //图片保存路径'
    private var driverBackUri: Uri? = null //图片保存路径'
    private var cropOptions: CropOptions? = null  //裁剪参数
    private var compressConfig: CompressConfig? = null //压缩参数
    private var invokeParam : InvokeParam? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getTakePhoto().onCreate(savedInstanceState)
        setContentView(R.layout.activity_dirver)
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



    //获得照片的输出保存Uri
    private fun getImageCropUri(name:String): Uri {
        val file = File(Environment.getExternalStorageDirectory(), "/temp/$name.jpg")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        return Uri.fromFile(file)
    }

}
