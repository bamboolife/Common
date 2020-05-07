package com.bamboo.common.helper;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 项目名称：MVPFrame
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020-02-15 14:32
 * 描述：
 */
public class RecyclerViewHelper {

    public void initRecyclerView(RecyclerView mRecyclerView){
        LinearLayoutManager manager=new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(),DividerItemDecoration.VERTICAL));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
