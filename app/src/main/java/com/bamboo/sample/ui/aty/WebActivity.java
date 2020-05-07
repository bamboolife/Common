package com.bamboo.sample.ui.aty;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bamboo.common.base.BaseActivity;
import com.bamboo.sample.R;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = "/test/wv")
public class WebActivity extends BaseActivity {
    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.button)
    Button mButton;

    @Override
    protected int getLayoutId() {
        return R.layout.bbl_web_layout;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        initWebView();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 先载入JS代码
        // 格式规定为:file:///android_asset/文件名.html
      //  mWebView.loadUrl("file:///android_asset/test.html");
        mWebView.loadUrl("https://www.baidu.com");

        method1();
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

    }

    private void method1() {
        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(WebActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });
    }

    private void method2() {
        // 只需要将第一种方法的loadUrl()换成下面该方法即可
        mWebView.evaluateJavascript("javascript:callJS()", new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                //此处为 js 返回的结果
                mButton.setText(value);
            }
        });
    }

    @OnClick(R.id.button)
    public void onEvent() {
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                // 注意调用的JS方法名要对应上
                // 调用javascript的callJS()方法
                // mWebView.loadUrl("javascript:callJS()");

                method2();
            }
        });
    }
}
