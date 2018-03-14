package com.okbind.library;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by chen.huarong on 2017/12/26.
 *
 */

public class OkViewHold extends RecyclerView.ViewHolder {

    private ViewDataBinding mItemBinding;


    public OkViewHold(View itemView) {
        super(itemView);
    }

    public ViewDataBinding getItemBinding() {
        return mItemBinding;
    }

    public void setItemBinding(ViewDataBinding itemBinding) {
        this.mItemBinding = itemBinding;
    }


}
