package com.example.palo.beijinnews.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palo.beijinnews.R;
import com.example.palo.beijinnews.utils.LogUtil;


public class NewsDetailActivity extends Activity implements View.OnClickListener {

    private WebView webview;
    private ProgressBar progressbar;
    private ImageButton ibMenu;
    private TextView tvTitle;
    private ImageButton ibBack;
    private ImageButton iconTextsize;
    private ImageButton iconShare;

    private int tempSize = 2;//正常
    private int realSize = tempSize;
    private WebSettings webSettings;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-10-21 09:40:15 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        setContentView(R.layout.activity_news_detail);
        ibMenu = (ImageButton) findViewById(R.id.ib_menu);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        ibBack = (ImageButton) findViewById(R.id.ib_back);
        iconTextsize = (ImageButton) findViewById(R.id.icon_textsize);
        iconShare = (ImageButton) findViewById(R.id.icon_share);
        webview = (WebView) findViewById(R.id.webview);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

        tvTitle.setVisibility(View.GONE);
        ibBack.setVisibility(View.VISIBLE);
        iconTextsize.setVisibility(View.VISIBLE);
        iconShare.setVisibility(View.VISIBLE);


        ibMenu.setOnClickListener(this);
        ibBack.setOnClickListener(this);
        iconTextsize.setOnClickListener(this);
        iconShare.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2016-10-21 09:40:15 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == iconShare) {
            // Handle clicks for ibMenu
            Toast.makeText(NewsDetailActivity.this, "分享", Toast.LENGTH_SHORT).show();
        } else if (v == ibBack) {
            // Handle clicks for ibBack
            finish();
        } else if (v == iconTextsize) {
            // Handle clicks for iconTextsize
//            Toast.makeText(NewsDetailActivity.this, "设置文字大小", Toast.LENGTH_SHORT).show();
            showChangeTextSizeDialog();
        }
    }

    private void showChangeTextSizeDialog() {
        AlertDialog.Builder dialog =  new AlertDialog.Builder(this);
        dialog.setTitle("设置文字大小");
        String[] items  = {"超大字体","大字体","正常字体","小字体","超小字体"};
        dialog.setSingleChoiceItems(items, realSize, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tempSize = which;

            }
        });
        dialog.setNegativeButton("取消", null);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                realSize = tempSize;
                changeTextSize(realSize);
            }
        });
        dialog.show();
    }

    /**
     * 0~4
     * @param realSize
     */
    private void changeTextSize(int realSize) {

        switch (realSize){
            case 0://超大字体
//                webSettings.setTextSize(WebSettings.TextSize.LARGEST);
                webSettings.setTextZoom(200);
                break;
            case 1://大字号
//                webSettings.setTextSize(WebSettings.TextSize.LARGER);
                webSettings.setTextZoom(150);
                break;
            case 2://正常
//                webSettings.setTextSize(WebSettings.TextSize.NORMAL);
                webSettings.setTextZoom(100);
                break;
            case 3://小字号
//                webSettings.setTextSize(WebSettings.TextSize.SMALLER);
                webSettings.setTextZoom(75);
                break;
            case 4://超小字体
//                webSettings.setTextSize(WebSettings.TextSize.SMALLEST);
                webSettings.setTextZoom(50);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        getData();
    }

    private void getData() {
        Uri uri = getIntent().getData();
        LogUtil.e("uri=="+uri);
         webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);//设置支持javaScript
        //设置
        webSettings.setBuiltInZoomControls(true);//设置缩放按钮
        webSettings.setUseWideViewPort(true);//设置支持双击页面变大变小，页面要支持

        //这个监听有一个作业，点击页面的连接不会打开到系统的浏览器打开页面
         webview.setWebViewClient(new WebViewClient() {
            //当页面加载完成的时候回调
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressbar.setVisibility(View.GONE);
            }
        });

        webview.loadUrl(uri.toString());
//        webview.loadUrl("http://atguigu.com/teacher.shtml");
    }
}
