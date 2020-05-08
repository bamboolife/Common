package com.bamboo.sample;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bamboo.common.base.BaseActivity;
import com.bamboo.common.utils.LogUtil;
import com.bamboo.sample.permission.NotifyRationale;
import com.bamboo.sample.ui.aty.AACTestActivity;
import com.bamboo.sample.ui.aty.ToastActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import javax.inject.Inject;

import butterknife.BindView;

@Route(path = "/test/main")
public class MainActivity extends BaseActivity {
    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @Inject
    LogUtil mLogUtil;
    Button btn_floatView;
    WindowManager wm;
    WindowManager.LayoutParams params;
    boolean wmTag = false;

    @Override
    protected int getLayoutId() {
        return R.layout.bbl_main_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
      //  requestNotification();
      //  init();

//        initWindowManager();
//        createFloatView("test");
        requestNotification();
        //test
    }

    private void init() {
        Intent intent = new Intent(this, AACTestActivity.class);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this, "tag")
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setFullScreenIntent(pendingIntent, false)
                .setContentTitle("标题").setContentText("内容")
                .build();
        manager.notify(1, notification);

    }

    private void requestNotification() {
        AndPermission.with(this)
                .notification()
                .permission()
                .rationale(new NotifyRationale())
                .onGranted(new Action<Void>() {
                    @Override
                    public void onAction(Void data) {
                        //成功
                       // init();
                    }
                })
                .onDenied(new Action<Void>() {
                    @Override
                    public void onAction(Void data) {
                        //失败
                    }
                })
                .start();
    }

    private void initWindowManager() {
        wm = (WindowManager) getApplicationContext().getSystemService(
                Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        // 设置window type
        params.type = WindowManager.LayoutParams.TYPE_PHONE;
        /*
         * 如果设置为params.type = WindowManager.LayoutParams.TYPE_PHONE; 那么优先级会降低一些,
         * 即拉下通知栏不可见
         */

        params.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明

        // 设置Window flag
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
        /*
         * 下面的flags属性的效果形同“锁定”。 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
         * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |
         * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
         */

        // 设置悬浮窗的长得宽
        params.width = wm.getDefaultDisplay().getWidth();
        params.height = 200;
        params.gravity = Gravity.LEFT | Gravity.TOP;
    }


    private void createFloatView(String str) {
        if (btn_floatView == null) {
            btn_floatView = new Button(getApplicationContext());
            wmTag = true;
        }

        btn_floatView.setText(str);

        // Log.i(TAG, "createFloatView: " + str);

        // 设置悬浮窗的Touch监听
        btn_floatView.setOnTouchListener(new View.OnTouchListener() {
            int lastX, lastY;
            int paramX, paramY;

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Intent intent = new Intent(MainActivity.this, ToastActivity.class);
                        startActivity(intent);
                        wm.removeViewImmediate(btn_floatView);
                        btn_floatView = null;
                        break;
                    case MotionEvent.ACTION_MOVE:

                        break;
                }
                return true;
            }
        });

        if (wmTag) {
            wm.addView(btn_floatView, params);
            wmTag = false;
        } else {
            wm.updateViewLayout(btn_floatView, params);
        }
    }
}
