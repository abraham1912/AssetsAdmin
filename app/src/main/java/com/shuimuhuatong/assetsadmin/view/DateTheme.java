package com.shuimuhuatong.assetsadmin.view;

import android.graphics.Color;

import com.shuimuhuatong.assetsadmin.R;

import cn.aigestudio.datepicker.bizs.themes.DPTheme;

/**
 * Created by wangchong on 2018/6/28 17:58
 */
public class DateTheme extends DPTheme {
    /**
     * 月视图背景色
     *
     * Color of MonthView's background
     *
     * @return 16进制颜色值 hex color
     */
    @Override
    public int colorBG() {
        return Color.parseColor("#ffffffff");
    }

    @Override
    public int colorBGCircle() {
        return Color.parseColor("#00000000");
    }

    @Override
    public int colorTitleBG() {
        return Color.parseColor("#ff4c00");
    }

    @Override
    public int colorTitle() {
        return Color.parseColor("#ffffffff");
    }

    @Override
    public int colorToday() {
        return Color.parseColor("#ffffffff");
    }

    @Override
    public int colorG() {
        return Color.parseColor("#333333");
    }

    @Override
    public int colorF() {
        return Color.parseColor("#00000000");
    }

    @Override
    public int colorWeekend() {
        return Color.parseColor("#333333");
    }

    @Override
    public int colorHoliday() {
        return Color.parseColor("#333333");
    }

}
