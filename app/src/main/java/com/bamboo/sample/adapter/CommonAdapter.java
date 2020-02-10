package com.bamboo.sample.adapter;

import androidx.annotation.NonNull;

import com.bamboo.sample.R;
import com.bamboo.sample.bean.CommonBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-10 17:31
 * 描述：
 */
public class CommonAdapter extends BaseQuickAdapter<CommonBean, BaseViewHolder> {

    public CommonAdapter() {
        super(R.layout.bbl_home_item_layout);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CommonBean item) {
           helper.setText(R.id.tv_title,item.getTitle());
    }
}
