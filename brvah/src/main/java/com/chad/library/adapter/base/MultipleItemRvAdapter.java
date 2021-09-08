package com.chad.library.adapter.base;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.chad.library.adapter.base.util.ProviderDelegate;

import java.util.List;

/**
 * https://github.com/chaychan
 *
 * @author ChayChan
 * @description: When there are multiple entries, avoid too much business logic in convert(),Put the logic of each item in the corresponding ItemProvider
 * 当有多种条目的时候，避免在convert()中做太多的业务逻辑，把逻辑放在对应的ItemProvider中
 * @date 2018/3/21  9:55
 * <p>
 * modify by wenjie 2019-12-10 16:16
 */
@SuppressWarnings("unchecked")
public abstract class MultipleItemRvAdapter<T, V extends BaseViewHolder> extends BaseQuickAdapter<T, V> {

    private SparseArray<BaseItemProvider> mItemProviders;
    protected ProviderDelegate mProviderDelegate;

    public MultipleItemRvAdapter(@Nullable List<T> data) {
        super(data);
    }

    public MultipleItemRvAdapter() {
        this(null);
    }

    /**
     * 用于adapter构造函数完成参数的赋值后调用
     * Called after the assignment of the argument to the adapter constructor
     */
    public void finishInitialize() {
        mProviderDelegate = new ProviderDelegate();

        setMultiTypeDelegate(new MultiTypeDelegate<T>() {

            @Override
            protected int getItemType(@NonNull T item) {
                return getViewType(item);
            }
        });

        registerItemProvider();

        mItemProviders = mProviderDelegate.getItemProviders();

        for (int i = 0; i < mItemProviders.size(); i++) {
            int key = mItemProviders.keyAt(i);
            BaseItemProvider provider = mItemProviders.get(key);
            provider.setAdapter(this);
            getMultiTypeDelegate().registerItemType(key, provider.layout());
        }
    }

    protected abstract int getViewType(@NonNull T item);

    public abstract void registerItemProvider();


    @Override
    protected void onItemClick(@NonNull V holder, @NonNull T item, int position) {
        int itemViewType = holder.getItemViewType();
        BaseItemProvider provider = mItemProviders.get(itemViewType);
        provider.onItemClick(holder, item, position);
    }

    @Override
    protected boolean onItemLongClick(@NonNull V holder, @NonNull T item, int position) {
        int itemViewType = holder.getItemViewType();
        BaseItemProvider provider = mItemProviders.get(itemViewType);
        return provider.onItemLongClick(holder, item, position);
    }

    @Override
    protected boolean onChildLongClick(@NonNull V holder, @NonNull T item, int position, @NonNull View view) {
        int itemViewType = holder.getItemViewType();
        BaseItemProvider provider = mItemProviders.get(itemViewType);
        return provider.onChildLongClick(holder, item, position, view);
    }

    @Override
    protected void onChildClick(@NonNull V holder, @NonNull T item, int position, @NonNull View view) {
        int itemViewType = holder.getItemViewType();
        BaseItemProvider provider = mItemProviders.get(itemViewType);
        provider.onChildClick(holder, item, position, view);
    }

    @Override
    protected V onCreateDefViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateDefViewHolder(parent, viewType);
    }

    @Override
    protected void onViewHolderCreated(@NonNull V holder, @NonNull ViewGroup parent, int viewType) {
        BaseItemProvider provider = mItemProviders.get(viewType);
        provider.mContext = parent.getContext();
        provider.onViewHolderCreated(holder, parent, viewType);
    }

    @Override
    protected void convert(@NonNull V holder, @NonNull T item, int position) {
        int itemViewType = holder.getItemViewType();
        BaseItemProvider provider = mItemProviders.get(itemViewType);
        provider.mData = mData;
        //执行子类的convert方法
        provider.convert(holder, item, position);
    }

}