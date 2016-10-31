package com.example.palo.beijinnews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 作者：尚硅谷-杨光福 on 2016/10/15 14:57
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：Fragment的基类
 * LeftMenuFragemtn和ContentFragment会继承它
 */
public abstract class BaseFragment extends Fragment {

    public Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();//MainActivity
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView();
    }

    /**
     * 交给子类实现，让子类实现自己特有的效果
     * @return
     */
    public abstract View initView();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当子类需要绑定数据到ui的时候，重写该方法
     * 1.绑定数据
     * 2.联网请求
     *
     */
    public void initData() {

    }
}
