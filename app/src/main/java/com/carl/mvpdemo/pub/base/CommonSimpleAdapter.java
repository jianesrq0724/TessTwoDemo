package com.carl.mvpdemo.pub.base;

import com.carl.mvpdemo.R;

import java.util.List;

/**
 * @author Carl
 * version 1.0
 * @since 2018/5/31
 */
public class CommonSimpleAdapter extends CommonBaseAdapter<String> {
    public CommonSimpleAdapter(List<String> mDataList) {
        super(mDataList);
    }

    @Override
    public int getLayoutId() {
        return R.layout.pub_item_recyclerview;
    }

    @Override
    public void conner(CommonViewHolder holder, String content) {
        holder.setText(R.id.content_tv, content);
    }
}
