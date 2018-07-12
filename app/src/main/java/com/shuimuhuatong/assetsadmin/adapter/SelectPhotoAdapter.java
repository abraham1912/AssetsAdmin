package com.shuimuhuatong.assetsadmin.adapter;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shuimuhuatong.assetsadmin.R;

import java.util.List;

/**
 * Created by wangchong on 2018/6/14 17:37
 */
public class SelectPhotoAdapter extends BaseQuickAdapter<Bitmap,BaseViewHolder> {
    private List<Bitmap> images = null ;
    public SelectPhotoAdapter(int layoutResId, @Nullable List<Bitmap> data) {
        super(layoutResId, data);
        images = data ;
    }

    @Override
    protected void convert(BaseViewHolder helper, Bitmap bitmap) {
        int position = helper.getLayoutPosition();
        ImageView delete = helper.getView(R.id.deletePhoto);
        if (position==images.size()-1){
            delete.setVisibility(View.GONE);
        }else {
            delete.setVisibility(View.VISIBLE);
        }
        helper.setImageBitmap(R.id.addPhoto,bitmap);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                images.remove(bitmap);
                notifyDataSetChanged();
            }
        });

    }

}
