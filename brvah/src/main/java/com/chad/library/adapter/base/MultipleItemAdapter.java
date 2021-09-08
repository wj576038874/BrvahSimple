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
 * author: wenjie
 * date: 2020-04-12 15:51
 * descption:
 * sample：
 * class SampleAdapter(param: String) : MultipleItemAdapter<String, BaseViewHolder>() {
 * <p>
 * init {
 * //必须调用
 * addItemProvider(SampleItemProvider(param))
 * }
 * <p>
 * override fun getViewType(item: String): Int {
 * return 0
 * }
 * }
 * <p>
 * class SampleItemProvider(param: String) : BaseItemProvider<String, BaseViewHolder>() {
 * override fun viewType() = 0
 * <p>
 * override fun layout() = 0
 * <p>
 * override fun convert(helper: BaseViewHolder?, data: String?, position: Int) {
 * }
 * }
 */
@SuppressWarnings("unchecked")
public abstract class MultipleItemAdapter<T, V extends BaseViewHolder> extends BaseQuickAdapter<T, V> {

    private SparseArray<BaseItemProvider> mItemProviders;
    protected ProviderDelegate mProviderDelegate;

    public MultipleItemAdapter(@Nullable List<T> data) {
        super(data);

        mProviderDelegate = new ProviderDelegate();

        setMultiTypeDelegate(new MultiTypeDelegate<T>() {

            @Override
            protected int getItemType(@NonNull T item) {
                return getViewType(item);
            }
        });
    }

    public MultipleItemAdapter() {
        this(null);
    }

    public final void addItemProvider(@NonNull final BaseItemProvider... itemProviders) {
        for (BaseItemProvider itemProvider : itemProviders) {
            mProviderDelegate.registerProvider(itemProvider);
        }
        mItemProviders = mProviderDelegate.getItemProviders();
        for (int i = 0; i < mItemProviders.size(); i++) {
            int key = mItemProviders.keyAt(i);
            BaseItemProvider provider = mItemProviders.get(key);
            provider.setAdapter(this);
            getMultiTypeDelegate().registerItemType(key, provider.layout());
        }
    }

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
    protected void onViewHolderCreated(@NonNull V holder, @NonNull ViewGroup parent, int viewType) {
        BaseItemProvider provider = mItemProviders.get(viewType);
        provider.mContext = parent.getContext();

        provider.onViewHolderCreated(holder, parent, viewType);
    }

    protected abstract int getViewType(@NonNull T item);

    @Override
    protected void convert(@NonNull V holder, @NonNull T item, int position) {
        int itemViewType = holder.getItemViewType();
//        int itemViewType = getItemViewType(position);
        BaseItemProvider provider = mItemProviders.get(itemViewType);
        provider.mData = mData;
        //执行子类的convert方法
        provider.convert(holder, item, position);
    }

}
