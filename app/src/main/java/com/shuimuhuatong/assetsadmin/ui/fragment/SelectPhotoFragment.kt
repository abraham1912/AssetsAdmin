package com.shuimuhuatong.assetsadmin.ui.fragment


import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.common.wangchong.commonutils.base.BaseFragment
import com.shuimuhuatong.assetsadmin.R
import com.shuimuhuatong.assetsadmin.adapter.SelectPhotoAdapter
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
import org.jetbrains.anko.support.v4.toast
import java.io.File

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * 选择上传照片
 */
class SelectPhotoFragment : BaseFragment(), TakePhoto.TakeResultListener, InvokeListener, BaseQuickAdapter.OnItemClickListener {
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        if (images.size<7){
            if (position == images.size - 1) {
                mTakePhoto?.onPickFromCaptureWithCrop(imageUri,cropOptions)
            }
        }else{
            toast("最多上传6张照片")
        }

    }

    private var invokeParam: InvokeParam? = null
    private var imageUri: Uri? = null //图片保存路径'
    private var compressConfig: CompressConfig? = null //压缩参数

    var images = arrayListOf<Bitmap>()

    override fun takeSuccess(result: TResult?) {
        var iconPath = result?.image?.originalPath
        var mPhoto = BitmapFactory.decodeFile(iconPath)
        images.add(images.size - 1, mPhoto)
        mAdapter?.notifyDataSetChanged()
    }

    override fun takeCancel() {

    }

    override fun takeFail(result: TResult?, msg: String?) {
        toast(msg!!)
    }

    override fun invoke(invokeParam: InvokeParam?): PermissionManager.TPermissionType {
        var type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam!!.method)
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam
        }
        return type
    }

    override fun attachLayoutId(): Int {
        return R.layout.fragment_select_photo
    }

    var mAdapter: SelectPhotoAdapter? = null
    var recyclerView : RecyclerView? = null

    private var cropOptions: CropOptions? = null  //裁剪参数
    override fun initView(view: View?) {
        mTakePhoto = getTakePhoto()
        recyclerView = view!!.find<RecyclerView>(R.id.recyclerView)
        var manager = LinearLayoutManager(mContext)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerView!!.layoutManager = manager
        mAdapter = SelectPhotoAdapter(R.layout.item_select_photo, images)
        recyclerView!!.adapter = mAdapter
        mAdapter!!.onItemClickListener = this

    }

    //获得照片的输出保存Uri
    private fun getImageCropUri(): Uri {
        val file = File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg")
        if (!file.parentFile.exists()) file.parentFile.mkdirs()
        return Uri.fromFile(file)
    }

    fun getPhotos(): List<Bitmap>{
        images.removeAt(images.size-1)
        return images
    }

    override fun initData() {
        imageUri = getImageCropUri()
        //设置裁剪参数
        cropOptions =  CropOptions.Builder().setAspectX(3).setAspectY(4).setWithOwnCrop(false).create()
        //设置压缩参数
        compressConfig= CompressConfig.Builder().setMaxSize(5*1024).setMaxPixel(800).create()
        mTakePhoto?.onEnableCompress(compressConfig,true) //设置为需要压缩
        images.add(BitmapFactory.decodeResource(resources, R.mipmap.add_photo))
    }

    /**
     *  获取TakePhoto实例
     * @return
     */
    var mTakePhoto: TakePhoto? = null

    fun getTakePhoto(): TakePhoto {

        if (mTakePhoto == null) {
            mTakePhoto = TakePhotoInvocationHandler.of(this).bind(TakePhotoImpl(this, this)) as TakePhoto?
        }
        return mTakePhoto!!

    }

    private var param1: String? = null
    private var param2: String? = null


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SelectPhotoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getTakePhoto().onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        getTakePhoto().onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //以下代码为处理Android6.0、7.0动态权限所需
        var type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionManager.handlePermissionsResult(activity, type, invokeParam, this)

    }
}
