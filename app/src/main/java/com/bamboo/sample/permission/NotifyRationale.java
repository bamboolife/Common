package com.bamboo.sample.permission;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/4/27 3:27 PM
 * 描述：
 */
public class NotifyRationale implements Rationale<Void> {
    @Override
    public void showRationale(Context context, Void data, RequestExecutor executor) {
        new AlertDialog.Builder(context).setCancelable(false)
                .setTitle("我的标题")
                .setMessage("通知已开启")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        executor.execute();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        executor.cancel();
                    }
                })
                .show();
    }
}
