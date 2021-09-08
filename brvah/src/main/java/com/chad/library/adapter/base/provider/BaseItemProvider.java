package com.chad.library.adapter.base.provider;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;

/**
 * https://github.com/chaychan
 *
 * @author ChayChan
 * @description: The base class of ItemProvider
 * @date 2018/3/21  10:41
 * modify by wenjie 2020-01-13 16:16
 * 新增子view的点击事件 以及长按事件 以及viewholder创建的回调方法onCreateItemProvider
 */
public abstract class BaseItemProvider<T, V extends BaseViewHolder> {

    public Context mContext;
    public List<T> mData;

    /**
     * add adapter ItemProvider有需要用到adapter可以调用getAdapter获取
     */
    private BaseQuickAdapter<T, V> adapter;

    //子类须重写该方法返回viewType
    //Rewrite this method to return viewType
    public abstract int viewType();

    //子类须重写该方法返回layout
    //Rewrite this method to return layout
    public abstract int layout();

    public void setAdapter(BaseQuickAdapter<T, V> adapter) {
        this.adapter = adapter;
    }

    @NonNull
    public BaseQuickAdapter<T, V> getAdapter() {
        return adapter;
    }

    /**
     * add  初始化viewholder的时候会调用 子类可以重写然后做一些操作
     */
    public void onViewHolderCreated(@NonNull V holder, @NonNull ViewGroup parent, int viewType) {

    }

    public abstract void convert(@NonNull V holder, @NonNull T item, int position);


    /**
     * 子类若想实现条目点击事件则重写该方法
     * 如果设置了adapter.setOnItemClickListener 此方法不会回调
     *
     * @param holder
     * @param item
     * @param position
     */
    public void onItemClick(@NonNull V holder, @NonNull T item, int position) {

    }


    /**
     * 子类若想实现条目长按事件则重写该方法
     * 如果设置了adapter.setOnItemLongClickListener 此方法不会回调
     *
     * @param holder
     * @param item
     * @param position
     * @return
     */
    public boolean onItemLongClick(@NonNull V holder, @NonNull T item, int position) {
        return false;
    }

    /**
     * add 子类若想实现条目的子view点击事件则重写该方法
     * 如果设置了adapter.setOnItemChildClickListener 此方法不会回调
     *
     * @param holder    viewHolder
     * @param item      数据
     * @param position  position
     * @param view 点击的子view
     */
    public void onChildClick(@NonNull V holder, @NonNull T item, int position, @NonNull View view) {

    }

    /**
     * add  子类若想实现条目的子view长按事件则重写该方法
     * 如果设置了adapter.setOnItemChildLongClickListener 此方法不会回调
     *
     * @param holder    viewHolder
     * @param item      数据
     * @param position  position
     * @param view 长按的子view
     * @return 注意这个方法在适配器中设置了事件之后不会回调，做过过滤
     */
    public boolean onChildLongClick(@NonNull V holder, @NonNull T item, int position, @NonNull View view) {
        return false;
    }
}
