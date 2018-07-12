package com.shuimuhuatong.assetsadmin.ui.accidentwork

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.common.wangchong.commonutils.base.BaseActivity
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.adapter.AccidentInfoAdapter
import com.shuimuhuatong.assetsadmin.bean.AccidentCarInfo
import kotlinx.android.synthetic.main.list_unpull.*
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
import java.util.*

class AddAccidentInfoActivity : BaseActivity() , TakePhoto.TakeResultListener, InvokeListener{
    override fun takeSuccess(result: TResult?) {
        var iconPath = result?.image?.originalPath!!
        var bitmap = BitmapFactory.decodeFile(iconPath)
        info?.driverPhoto = bitmap
        imageView?.setImageBitmap(bitmap)
    }

    override fun takeCancel() {

    }

    override fun takeFail(p0: TResult?, msg: String?) {
        toast(msg!!)
    }

    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {
        var type=PermissionManager.checkPermission(TContextWrap.of(this),invokeParam!!.method)
        if(PermissionManager.TPermissionType.WAIT.equals(type)){
            this.invokeParam = invokeParam
        }
        return type
    }
    private var uri: Uri? = null //图片保存路径'
    private var cropOptions: CropOptions? = null  //裁剪参数
    private var compressConfig: CompressConfig? = null //压缩参数
    private var invokeParam : InvokeParam? = null
    override fun initView() {
        initToolBar(toolbar, true, "事故方车辆信息")
        addCar.visibility = View.VISIBLE
        save.visibility = View.VISIBLE
    }

    var infos: ArrayList<AccidentCarInfo>? = null  //添加的事故方信息列表
    var info : AccidentCarInfo? = null
    var imageView : ImageView? = null
    override fun initData() {
        mTakePhoto = getTakePhoto()
        //设置裁剪参数
        cropOptions =  CropOptions.Builder().setAspectX(3).setAspectY(4).setWithOwnCrop(false).create()
        //设置压缩参数
        compressConfig= CompressConfig.Builder().setMaxSize(5*1024).setMaxPixel(800).create()
        mTakePhoto?.onEnableCompress(compressConfig,true) //设置为需要压缩
        infos = intent.getBundleExtra("bundle").getSerializable("infos") as ArrayList<AccidentCarInfo>
        var mAdapter = AccidentInfoAdapter(R.layout.item_add_accident,infos)
        recyclerView.adapter = mAdapter
        mAdapter.setOnDriverImageClicked { info, position, imageView ->
            this.info = info
            this.imageView = imageView
            uri = getImageCropUri(position.toString())
            mTakePhoto?.onPickFromCaptureWithCrop(uri,cropOptions)
        }
        addCar.setOnClickListener {
            var hasEnptyLicense = false
            for (item in infos!!){
                if (TextUtils.isEmpty(item.license)){
                    hasEnptyLicense = true
                }
            }
            if (!hasEnptyLicense){  //不包含空的车牌号时添加
                infos!!.add(AccidentCarInfo("","","",null))
                mAdapter.refresh(infos)
            }
        }

        save.setOnClickListener {

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getTakePhoto().onCreate(savedInstanceState)
        setContentView(R.layout.list_unpull)
    }

    /**
     *  获取TakePhoto实例
     * @return
     */
    var mTakePhoto : TakePhoto? = null
    fun getTakePhoto() : TakePhoto {

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
        var type= PermissionManager.onRequestPermissionsResult(requestCode,permissions,grantResults)
        PermissionManager.handlePermissionsResult(this,type,invokeParam,this)

    }



    //获得照片的输出保存Uri
    private fun getImageCropUri(name:String): Uri {
        val file = File(Environment.getExternalStorageDirectory(), "/temp/$name.jpg")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        return Uri.fromFile(file)
    }
}
