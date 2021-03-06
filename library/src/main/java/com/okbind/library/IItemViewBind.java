package com.okbind.library;


import java.util.List;

import androidx.annotation.LayoutRes;

/**
 * Created by chen.huarong on 2018/1/10.
 * 数据绑定接口
 */

public interface IItemViewBind<T> {

    void onBind(OkViewHold holder, int position, T item);

    void onBind(OkViewHold holder, int position, List data);

    @LayoutRes
    int getLayoutId(int viewType);

}
