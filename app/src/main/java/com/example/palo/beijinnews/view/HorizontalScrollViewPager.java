package com.example.palo.beijinnews.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.palo.beijinnews.utils.LogUtil;

/**
 * 作者：尚硅谷-杨光福 on 2016/10/18 15:29
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class HorizontalScrollViewPager extends ViewPager {
    public HorizontalScrollViewPager(Context context) {
        super(context);
    }

    public HorizontalScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private float startX;
    private float startY;

    /**
     * 1.水平方向滑动
     * a,第0个位置，并且是从左到右滑动
     * getParent().requestDisallowInterceptTouchEvent(false);
     * b,滑动到最后一个，并且是从右到左滑动
     * getParent().requestDisallowInterceptTouchEvent(false);
     * c,其他中间部分
     * getParent().requestDisallowInterceptTouchEvent(true);
     * 2.竖直方向滑动
     * <p/>
     * getParent().requestDisallowInterceptTouchEvent(false);
     *
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //反拦截-请求父类禁用拦截方法

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //优先要把事件给当前控件
                getParent().requestDisallowInterceptTouchEvent(true);
                //1.记录按下的坐标
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.e("getCurrentItem()"+getCurrentItem());
                //2.来到新的坐标
                float endX = ev.getX();
                float endY = ev.getY();

                //3.计算距离
                float distancX = Math.abs(endX - startX);
                float distancY= Math.abs(endY- startY);

                if(distancX > distancY){
                    //水平方向滑动
//                    a,第0个位置，并且是从左到右滑动
                    if(getCurrentItem()==0&&endX - startX>0){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
//                    b,滑动到最后一个，并且是从右到左滑动
                    else if(getCurrentItem()==getAdapter().getCount()-1 && endX - startX <0){
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
//                    c,其他中间部分
                    else{
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
//
                }else{
                    //竖直方向滑动
                    getParent().requestDisallowInterceptTouchEvent(false);
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
