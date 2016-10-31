package com.example.palo.beijinnews.domain;

/**
 * Created by palo on 2016/10/27.
 */
public class ShoppingCart extends ShoppingPagerBean.Wares {

    /**
     * 商品的个数
     */
    private int count = 1;

    /**
     * 商品是否被选中
     */
    private boolean isCheck = true;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }
}