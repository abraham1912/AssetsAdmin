package com.common.wangchong.commonutils.utils;

import android.support.annotation.NonNull;

import com.common.wangchong.commonutils.BuildConfig;

import io.reactivex.functions.Consumer;

/**
 * Created by Meiji on 2017/6/18.
 */

public class ErrorAction {

    @NonNull
    public static Consumer<Throwable> error() {
        return throwable -> {
            if (BuildConfig.DEBUG) {
                throwable.printStackTrace();
            }
        };
    }

    public static void print(@NonNull Throwable throwable) {
        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
    }
}
