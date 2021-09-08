package com.chad.library.adapter.base.loadmore;


import androidx.annotation.NonNull;

import com.chad.library.R;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by BlingBling on 2016/10/11.
 * <p>
 * modify by wenjie 2020-01-13 16:16
 */

public final class SimpleLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.brvah_quick_view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    public void convert(@NonNull BaseViewHolder holder) {
        super.convert(holder);
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

    /**
     * add getLoadCompleteId方法
     *
     * @return 子类需要重写
     */
    @Override
    protected int getLoadCompleteId() {
        return R.id.load_more_load_complete_view;
    }
}