package com.okbind.library;

import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by chen.huarong on 2017/12/26.
 */

public class OkViewHold extends RecyclerView.ViewHolder {

    private ViewDataBinding mItemBinding;


    OkViewHold(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("TypeParameterUnusedInFormals")
    public <T extends ViewDataBinding> T getItemBinding() {
        return (T) mItemBinding;
    }

    void setItemBinding(ViewDataBinding itemBinding) {
        this.mItemBinding = itemBinding;
    }


}
