package com.bamboo.sample.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bamboo.sample.R;
import com.bamboo.sample.bean.NewsData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/4/12 12:57 AM
 * 描述：
 */
public class NewsAdapter extends BaseQuickAdapter<NewsData, BaseViewHolder> {

    public NewsAdapter(@Nullable List<NewsData> data) {
        super(R.layout.bbl_news_item_layout, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, NewsData item) {
        helper.setText(R.id.title_tv,item.getNewsTitle());
        helper.setText(R.id.content_tv,item.getNewsContent());
        helper.setText(R.id.read_num_tv,item.getReadNum()+"人阅读");
    }
}
