package com.okbind.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Locale;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by chen.huarong on 2017/12/26.
 */

public class OkBindingAdapter extends RecyclerView.Adapter<OkViewHold> {

    @NonNull
    private
    List mDatas;
    private WeakReference<RecyclerView> mRecyclerView;
    @NonNull
    private IClass2Bind mClass2Bind;

    public OkBindingAdapter(List datas) {
        this(datas, new Class2Bind());
    }

    public OkBindingAdapter(@NonNull List datas
            , @NonNull IClass2Bind iClass2Bind) {
        this.mDatas = datas;
        this.mClass2Bind = iClass2Bind;
    }

    @NonNull
    @Override
    public final OkViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemViewBind itemViewBind = this.mClass2Bind.getBind(viewType);
        View view = inflate(parent, itemViewBind.getLayoutId(viewType));
        final OkViewHold holder = new OkViewHold(view);
        ViewDataBinding itemBinding = DataBindingUtil.bind(view);
        holder.setItemBinding(itemBinding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OkViewHold holder, int position) {
        throw new IllegalAccessError("use onBindViewHolder(OkViewHold holder, int position, List " +
                "datas) instead.");
    }

    @Override
    public void onBindViewHolder(@NonNull final OkViewHold holder, int position,
                                 @NonNull List datas) {
        final ItemViewBind itemViewBind = getItemViewBind(position);
        itemViewBind.onBind(holder, position, datas);
        itemViewBind.onBind(holder, position, getItem(position));
    }

    @Override
    public final int getItemViewType(int position) {
        int viewType = mClass2Bind.indexOfViewBind(getItem(position).getClass());
        if (viewType == -1) {
            throw new IllegalAccessError("please register " + getItem(position).getClass() + " " +
                    "type first!");
        }
        return viewType;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = new WeakReference<>(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        if (mRecyclerView != null) {
            mRecyclerView.clear();
            mRecyclerView = null;
        }
    }

    /**
     * 注册类型
     *
     * @param clazz        类型
     * @param itemViewBind bind类
     */
    public void register(Class clazz, ItemViewBind itemViewBind) {
        itemViewBind.attachAdapter(this);
        mClass2Bind.register(clazz, itemViewBind);
    }

    public <T> T getItem(int position) {
        if (position < mDatas.size()) {
            return (T) mDatas.get(position);
        } else {
            throw new IndexOutOfBoundsException(String.format(Locale.CHINESE, "IndexOutOfBounds, " +
                    "position:%d", position));
        }
    }

    RecyclerView getRecyclerView() {
        if (mRecyclerView == null) {
            throw new NullPointerException("recyclerView is null, adapter need set to " +
                    "recyclerView first!");
        }
        return mRecyclerView.get();
    }

    private ItemViewBind getItemViewBind(int position) {
        return mClass2Bind.getBind(getItem(position).getClass());
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @NonNull
    public IClass2Bind getClass2Bind() {
        return mClass2Bind;
    }

    @NonNull
    public List getDatas() {
        return mDatas;
    }

    public boolean add(Object item) {
        return mDatas.add(item);
    }

    public boolean addAll(List items) {
        return mDatas.addAll(items);
    }

    public boolean remove(Object item) {
        return mDatas.remove(item);
    }

    public boolean removeAll(List items) {
        return mDatas.removeAll(items);
    }

    private View inflate(ViewGroup parent, @LayoutRes int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }
}
