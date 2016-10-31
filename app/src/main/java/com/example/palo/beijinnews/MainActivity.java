package com.example.palo.beijinnews;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.example.palo.beijinnews.fragment.ContentFragment;
import com.example.palo.beijinnews.fragment.LeftMenuFragment;
import com.example.palo.beijinnews.utils.DensityUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {
    public static final String CONTENT_TAG = "content_tag";
    public static final String LEFTMENU_TAG = "leftmenu_tag";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//把标题隐藏
        super.onCreate(savedInstanceState);
        //1.设置主页面
        setContentView(R.layout.activity_main);

        //2.设置左侧菜单
        setBehindContentView(R.layout.leftmenu);

        //3.设置右侧菜单
        SlidingMenu slidingMenu = getSlidingMenu();
//        slidingMenu.setSecondaryMenu(R.layout.rightmenu);

        //4.设置支持滑动的模式：全屏滑动，边缘滑动，不可以滑动
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

        //5.设置页面模式：左侧菜单+主页面；左侧菜单+主页面+右侧菜单； 主页面+右侧菜单
        slidingMenu.setMode(SlidingMenu.LEFT);

        //6.设置主页面占的宽度
        slidingMenu.setBehindOffset(DensityUtil.dip2px(this, 200));
        initFragment();
    }

    private void initFragment() {

        //1.开启事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //2.添加LeftmenuFragment页面
        transaction.replace(R.id.fl_leftmenu,new LeftMenuFragment(), LEFTMENU_TAG);
        //3.添加主Fragemnt页面
        transaction.replace(R.id.fl_main_content, new ContentFragment(), CONTENT_TAG);

        //4.提交事务.


        transaction.commit();

    }

    /**
     * 得到左侧菜单
     * @return
     */
    public LeftMenuFragment getLeftMenuFragment() {
        return (LeftMenuFragment) getSupportFragmentManager().findFragmentByTag(LEFTMENU_TAG);
    }

    /**
     * 得到ContentFragment
     * @return
     */
    public ContentFragment getContentFragment() {
        return (ContentFragment) getSupportFragmentManager().findFragmentByTag(CONTENT_TAG);
    }
}
