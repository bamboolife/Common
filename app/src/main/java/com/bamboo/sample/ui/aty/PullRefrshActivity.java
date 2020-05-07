package com.bamboo.sample.ui.aty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bamboo.common.base.BaseActivity;
import com.bamboo.common.view.PullToRefrshLinearLayout;
import com.bamboo.sample.R;

import butterknife.BindView;

/**
 * 项目名称：
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/4/28 9:11 PM
 * 描述：
 */
public class PullRefrshActivity extends BaseActivity {
    @BindView(R.id.refresh)
    PullToRefrshLinearLayout mRefrsh;

    @Override
    protected int getLayoutId() {
        return R.layout.bbl_pull_refrsh_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
       mRefrsh.setOnHeaderRefreshListener(new PullToRefrshLinearLayout.OnHeaderRefreshListener() {
           @Override
           public void onHeaderRefresh(PullToRefrshLinearLayout view) {

           }
       });

       mRefrsh.setOnFooterRefreshListener(new PullToRefrshLinearLayout.OnFooterRefreshListener() {
           @Override
           public void onFooterRefresh(PullToRefrshLinearLayout view) {

           }
       });
    }
}
