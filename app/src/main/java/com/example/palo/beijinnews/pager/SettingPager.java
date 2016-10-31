package com.example.palo.beijinnews.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

import com.example.palo.beijinnews.base.BasePager;
import com.example.palo.beijinnews.utils.LogUtil;


/**
 * 作者：尚硅谷-杨光福 on 2016/10/17 09:06
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：设置页面
 */
public class SettingPager extends BasePager {
    public SettingPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("设置面数据加载了....");
        //设置标题
        tv_title.setText("设置");
        //创建子页面的视图
        TextView textView = new TextView(context);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        textView.setText("设置页面");

        //子页面的视图和FrameLayout结合在一起，形成一个新的页面
        fl_base_content.addView(textView);
    }
}
