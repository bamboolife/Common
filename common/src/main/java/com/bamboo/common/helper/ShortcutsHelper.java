package com.bamboo.common.helper;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;

import com.bamboo.common.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 项目名称：Common
 *
 * @Author bamboolife
 * 邮箱：core_it@163.com
 * 创建时间：2020/4/3 3:41 PM
 * 描述：android快捷方式快捷键
 */
public class ShortcutsHelper {
    ShortcutManager mShortcutManager;
    Context mContext;

    public ShortcutsHelper(Context context) {
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            mShortcutManager = context.getSystemService(ShortcutManager.class);
        }
    }

    /**
     * 设置或者创建快捷方式
     * @param index
     * @param clazz
     */
    public void setShortcut(int index, Class clazz) {
        Intent intent = new Intent(mContext, clazz);
        intent.setAction(Intent.ACTION_VIEW);//必须设置
        //intent.putExtra("mag",""); //跳转参数
        List<ShortcutInfo> infos = new ArrayList<>();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            for (int i = 0; i < mShortcutManager.getMaxShortcutCountPerActivity(); i++) {
                ShortcutInfo info = new ShortcutInfo.Builder(mContext, "id" + index)
                        .setShortLabel("短提示语" + index)
                        .setLongLabel("长提示语" + index)
                        .setIcon(Icon.createWithResource(mContext, R.mipmap.ic_pets_white_48dp))
                        .setIntent(intent)
                        .build();
                infos.add(info);
                // mShortcutManager.addDynamicShortcuts(Arrays.asList(info));
            }
            mShortcutManager.setDynamicShortcuts(infos);


        }
    }

    /**
     * 删除快捷方式
     *
     * @param index
     */
    public void removeItem(int index) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            List<ShortcutInfo> infos = mShortcutManager.getPinnedShortcuts();
            for (ShortcutInfo info : infos) {
                if (info.getId().equals("id" + index)) {
                    mShortcutManager.disableShortcuts(Arrays.asList(info.getId()), "提示语");
                }
            }
            mShortcutManager.removeDynamicShortcuts(Arrays.asList("id" + index));
        }
    }

    /**
     * 更新创建快捷方式
     *
     * @param index
     * @param clazz
     */
    public void updItem(int index, Class clazz) {
        Intent intent = new Intent(mContext, clazz);
        intent.setAction(Intent.ACTION_VIEW);//必须设置
        //intent.putExtra("mag",""); //跳转参数
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N_MR1) {
            ShortcutInfo info = new ShortcutInfo.Builder(mContext, "id" + index)
                    .setShortLabel("短提示语" + index)
                    .setLongLabel("长提示语" + index)
                    .setIcon(Icon.createWithResource(mContext, R.mipmap.ic_pets_white_48dp))
                    .setIntent(intent)
                    .build();
            mShortcutManager.updateShortcuts(Arrays.asList(info));

        }
    }
}
