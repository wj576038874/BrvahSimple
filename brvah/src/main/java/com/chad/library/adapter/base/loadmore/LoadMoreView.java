package com.chad.library.adapter.base.loadmore;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import com.chad.library.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by BlingBling on 2016/11/11.
 * modify by wenjie 2020-01-13 16:16
 * 新增加载完成的布局 点击加载更多 子类可以重写自行修改显示逻辑
 */

public abstract class LoadMoreView {

    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_LOADING = 2;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_END = 4;

    protected int mLoadMoreStatus = STATUS_DEFAULT;

    private boolean mLoadMoreEndGone = false;

    public void setLoadMoreStatus(int loadMoreStatus) {
        this.mLoadMoreStatus = loadMoreStatus;
    }

    public int getLoadMoreStatus() {
        return mLoadMoreStatus;
    }

    /**
     * 可重写此方式，实行自定义逻辑
     *
     * @param holder BaseViewHolder
     */
    public void convert(@NonNull BaseViewHolder holder) {
        switch (mLoadMoreStatus) {
            case STATUS_LOADING:
                visibleLoading(holder, true);
                visibleLoadComplete(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
            case STATUS_FAIL:
                visibleLoading(holder, false);
                visibleLoadComplete(holder, false);
                visibleLoadFail(holder, true);
                visibleLoadEnd(holder, false);
                break;
            case STATUS_END:
                visibleLoading(holder, false);
                visibleLoadComplete(holder, false);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, true);
                break;
            case STATUS_DEFAULT:
                visibleLoading(holder, false);
                visibleLoadComplete(holder, true);
                visibleLoadFail(holder, false);
                visibleLoadEnd(holder, false);
                break;
            default:
                break;
        }
    }

    private void visibleLoading(BaseViewHolder holder, boolean visible) {
        holder.setGone(getLoadingViewId(), visible);
    }

    private void visibleLoadFail(BaseViewHolder holder, boolean visible) {
        holder.setGone(getLoadFailViewId(), visible);
    }

    private void visibleLoadEnd(BaseViewHolder holder, boolean visible) {
        final int loadEndViewId = getLoadEndViewId();
        if (loadEndViewId != 0) {
            holder.setGone(loadEndViewId, visible);
        }
    }

    private void visibleLoadComplete(BaseViewHolder holder, boolean visible) {
        final int loadCompleteId = getLoadCompleteId();
        if (loadCompleteId != 0) {
            holder.setGone(loadCompleteId, visible);
        }
    }

    public final void setLoadMoreEndGone(boolean loadMoreEndGone) {
        this.mLoadMoreEndGone = loadMoreEndGone;
    }

    public final boolean isLoadEndMoreGone() {
        if (getLoadEndViewId() == 0) {
            return true;
        }
        return mLoadMoreEndGone;
    }

    /**
     * No more data is hidden
     *
     * @return true for no more data hidden load more
     * @deprecated Use {@link BaseQuickAdapter#loadMoreEnd(boolean)} instead.
     */
    @Deprecated
    public boolean isLoadEndGone() {
        return mLoadMoreEndGone;
    }

    /**
     * load more layout
     *
     * @return
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * loading view
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadingViewId();

    /**
     * load fail view
     *
     * @return
     */
    protected abstract
    @IdRes
    int getLoadFailViewId();

    /**
     * load end view, you can return 0
     *
     * @return
     */
    protected abstract @IdRes
    int getLoadEndViewId();

    /**
     * 返回加载完成的布局 可自行点击进行加载
     * @return
     */
    protected abstract @IdRes
    int getLoadCompleteId();
}
