package com.shuimuhuatong.assetsadmin.view;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shuimuhuatong.assetsadmin.R;
import com.shuimuhuatong.assetsadmin.adapter.SelectItemAdapter;
import com.shuimuhuatong.assetsadmin.adapter.SelectStatusAdapter;
import com.shuimuhuatong.assetsadmin.adapter.SelectTypeAdapter;
import com.shuimuhuatong.assetsadmin.bean.AppInit;
import com.shuimuhuatong.assetsadmin.bean.OrderStatus;
import com.shuimuhuatong.assetsadmin.bean.SelectItem;
import com.shuimuhuatong.assetsadmin.bean.VehicleType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchong on 2018/7/2 17:39
 */
public class SelectDialog extends Dialog implements View.OnClickListener {
    private List<SelectItem> milesItems = new ArrayList<>();
    private List<SelectItem> timeItems = new ArrayList<>();
    private List<OrderStatus> orderStatus = new ArrayList<>();
    private List<VehicleType> vehicleTypes = new ArrayList<>();
    private OnSeleteFinished onSeleteFinished ;
    public void setOnSeleteFinished(OnSeleteFinished onSeleteFinished){
        this.onSeleteFinished = onSeleteFinished ;
    }
    public interface OnSeleteFinished{
        void seleteFinished( List<VehicleType>  vehicleTypes, List<OrderStatus>  orderStatus,
                             List<SelectItem> milesItems,List<SelectItem> timeItems);
    }

    RecyclerView carTypeRecyclerView,  workStateRecyclerView, milesRecyclerView, timeRecyclerView;
    private SelectItemAdapter timeAdapter, mileAdapter;
    private SelectTypeAdapter vehicleTypeAdapter;
    private SelectStatusAdapter  orderStatusAdapter;


    public SelectDialog(@NonNull Context context) {
        super(context);

    }

    public SelectDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public SelectDialog(@NonNull Context context, int themeResId, List<SelectItem> milesItems, List<SelectItem> timeItems, boolean isPreDo,
                        List<OrderStatus> orderStatus, List<VehicleType> vehicleTypes) {
        super(context, themeResId);
        this.milesItems = milesItems;
        this.timeItems = timeItems;
        this.orderStatus = orderStatus;
        this.vehicleTypes = vehicleTypes;

        Window window = getWindow();
        int width = window.getWindowManager().getDefaultDisplay().getWidth();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = width;
        params.horizontalWeight = 1f;
        window.setAttributes(params);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View view = View.inflate(context, R.layout.choose_dialog, null);
        carTypeRecyclerView = view.findViewById(R.id.carTypeRecyclerView);

        workStateRecyclerView = view.findViewById(R.id.workStateRecyclerView);
        milesRecyclerView = view.findViewById(R.id.milesRecyclerView);
        timeRecyclerView = view.findViewById(R.id.timeRecyclerView);


        carTypeRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));

        workStateRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        milesRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        timeRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        setCarTypeAdapter();
        if (orderStatus != null) {
            setOrderStatusAdapter();
        }
        setMileAdapter();

        setTimeAdapter();


        if (!isPreDo) {  //待领取任务没有工单状态筛选
            view.findViewById(R.id.workStateTV).setVisibility(View.GONE);
            workStateRecyclerView.setVisibility(View.GONE);
        }
        setContentView(view);

        view.findViewById(R.id.dialog).setOnClickListener(this);
        view.findViewById(R.id.reset).setOnClickListener(this);
        view.findViewById(R.id.submit).setOnClickListener(this);
    }

    private void setCarTypeAdapter() {
        vehicleTypeAdapter = new SelectTypeAdapter(R.layout.item_select_checkbox, vehicleTypes);
        carTypeRecyclerView.setAdapter(vehicleTypeAdapter);
        carTypeClicked();
    }


    private void setOrderStatusAdapter() {
        orderStatusAdapter = new SelectStatusAdapter(R.layout.item_select_checkbox, orderStatus);
        workStateRecyclerView.setAdapter(orderStatusAdapter);
        orderStatusClicked();
    }


    private void setTimeAdapter() {
        timeAdapter = new SelectItemAdapter(R.layout.item_select_checkbox, timeItems);
        timeRecyclerView.setAdapter(timeAdapter);
        timeItemClicked();
    }

    private void setMileAdapter() {
        mileAdapter = new SelectItemAdapter(R.layout.item_select_checkbox, milesItems);
        milesRecyclerView.setAdapter(mileAdapter);
        mileItemClicked();
    }

    private void carTypeClicked() {
        vehicleTypeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                vehicleTypes.get(position).isChecked = !vehicleTypes.get(position).isChecked;
                vehicleTypeAdapter.notifyDataSetChanged();
                selectedCallback();
            }


        });
    }
    private void selectedCallback() {
        if (onSeleteFinished!=null){
            onSeleteFinished.seleteFinished(vehicleTypes,orderStatus,milesItems,timeItems);
        }
    }


    private void orderStatusClicked() {
        orderStatusAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                orderStatus.get(position).isChecked = !orderStatus.get(position).isChecked;
                orderStatusAdapter.notifyDataSetChanged();
                selectedCallback();
            }
        });
    }

    private void mileItemClicked() {
        mileAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                milesItems.get(position).isChecked = !milesItems.get(position).isChecked;
                for (int i = 0; i < milesItems.size(); i++) {
                    if (i != position) {
                        milesItems.get(i).isChecked = false;
                    }
                }
                mileAdapter.notifyDataSetChanged();
                selectedCallback();
            }
        });
    }

    private void timeItemClicked() {
        timeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                timeItems.get(position).isChecked = !timeItems.get(position).isChecked;
                for (int i = 0; i < timeItems.size(); i++) {
                    if (i != position) {
                        timeItems.get(i).isChecked = false;
                    }
                }
                timeAdapter.notifyDataSetChanged();
                selectedCallback();
            }
        });
    }


    protected SelectDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dialog) {
            dismiss();
        } else if (v.getId() == R.id.reset) {
            for (VehicleType item : vehicleTypes) {
                item.isChecked = false;
            }
            for (OrderStatus item : orderStatus) {
                item.isChecked = false;
            }

            for (SelectItem item : milesItems) {
                item.isChecked = false;
            }
            for (SelectItem item : timeItems) {
                item.isChecked = false;
            }
            vehicleTypeAdapter.notifyDataSetChanged();
            orderStatusAdapter.notifyDataSetChanged();
            timeAdapter.notifyDataSetChanged();
            mileAdapter.notifyDataSetChanged();
            selectedCallback();
            //setMileAdapter();
            // setTimeAdapter();

        } else if (v.getId() == R.id.submit) {
            dismiss();
        }
    }

}
