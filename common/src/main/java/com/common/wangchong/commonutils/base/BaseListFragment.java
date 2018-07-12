package com.common.wangchong.commonutils.base;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.wangchong.commonutils.R;

/**
 * Created by wangchong on 2018/6/14 14:44
 */
public abstract class BaseListFragment<T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseListView<T>, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {
    public static final String TAG = "BaseListFragment";
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected int pageNo = 0 ;
    protected TextView  count ;

    @Override
    protected Integer attachLayoutId() {
        return R.layout.fragment_list ;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#ff4c00"));
        swipeRefreshLayout.setOnRefreshListener(this);
        ImageView choose = view.findViewById(R.id.choose);
        choose.setVisibility(View.VISIBLE);
        count = view.findViewById(R.id.count);
        count.setVisibility(View.VISIBLE);
        choose.setOnClickListener(this);

    }



    @Override
    public void fetchData() {

    }

    @Override
    public void onShowLoading() {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
    }

    @Override
    public void onHideLoading() {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(false));
    }

    @Override
    public void onRefresh() {
        pageNo = 0 ;
        fetchData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.choose){
            showChooseDialog();
        }
    }

    public void showChooseDialog(){}
}
