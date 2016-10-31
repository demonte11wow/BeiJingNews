package com.example.palo.beijinnews.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.TintTypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.palo.beijinnews.R;
import com.example.palo.beijinnews.utils.Constants;

/**
 * 作者：尚硅谷-杨光福 on 2016/10/26 14:24
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：自定义增加删除按钮
 */
public class NumberAddSubView extends LinearLayout implements View.OnClickListener {

    private final Context context;
    private Button btn_sub;
    private TextView tv_value;
    private Button btn_add;

    /**
     * 默认值
     */
    private int value = 1;

    /**
     * 最小值
     */
    private int minValue = 1;

    /**
     * 最大值（库存）
     */
    private int maxValue = 1;

    public NumberAddSubView(Context context) {
        this(context, null);
    }

    public NumberAddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberAddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        //把布局R.layout.add_sub_number_view添加到NumberAddSubView类中
        View.inflate(context, R.layout.add_sub_number_view, NumberAddSubView.this);
        btn_sub = (Button) findViewById(R.id.btn_sub);
        tv_value = (TextView) findViewById(R.id.tv_value);
        btn_add = (Button) findViewById(R.id.btn_add);

        btn_sub.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        tv_value.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int tempValue = Integer.parseInt(tv_value.getText().toString());
                if(tempValue == Constants.mMinValue){
                    btn_sub.setEnabled(false);
                } else if(tempValue > Constants.mMinValue && tempValue < Constants.mMaxValue){
                    btn_sub.setEnabled(true);
                    btn_add.setEnabled(true);
                } else {
                    btn_add.setEnabled(false);
                }

            }
        });

        if(attrs != null){
            TintTypedArray typedArray = TintTypedArray.obtainStyledAttributes(context,attrs,R.styleable.NumberAddSubView);

            int value =  typedArray.getInt(R.styleable.NumberAddSubView_value, 0);
            if(value >0){
                setValue(value);
            }

            int minValue =  typedArray.getInt(R.styleable.NumberAddSubView_minValue,0);
            if(minValue >0){
                setMinValue(minValue);
            }

            int maxValue =  typedArray.getInt(R.styleable.NumberAddSubView_maxValue,0);
            if(maxValue >0){
                setMaxValue(maxValue);
            }


            Drawable number_background = typedArray.
            getDrawable(R.styleable.NumberAddSubView_number_background);
            if(number_background != null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    this.setBackground(number_background);
                }
            }


            Drawable sub_background = typedArray.
                    getDrawable(R.styleable.NumberAddSubView_sub_background);
            if(sub_background != null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btn_sub.setBackground(sub_background);
                }
            }

            Drawable add_background = typedArray.
                    getDrawable(R.styleable.NumberAddSubView_add_background);
            if(add_background != null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btn_add.setBackground(add_background);
                }
            }
        }

    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tv_value.setText(value+"");
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sub://减
//                Toast.makeText(context,"删除",Toast.LENGTH_SHORT).show();
                subNumber();
                if(listener != null){
                    listener.onButtonSubClick(v,value);
                }
                break;
            case R.id.btn_add://加
//                Toast.makeText(context,"添加",Toast.LENGTH_SHORT).show();
                addNumber();
                if(listener != null){
                    listener.onButtonAddClick(v, value);
                }
                break;
        }
    }

    /**
     * 添加产品数量
     */
    private void addNumber() {

        if(value < maxValue){
            value++;
        }

        setValue(value);
    }

    /**
     * 减少产品数量
     */
    private void subNumber() {
        if(value > minValue){
            value -=1;
        }
        setValue(value);
    }

    /**
     * 按钮点击的监听器
     */
    public interface OnButtonClickListener{
        /**
         * 当减按钮被点击的时候回调
         * @param v
         * @param value
         */
        public void onButtonSubClick(View v, int value);
        /**
         * 当加按钮被点击的时候回调
         * @param v
         * @param value
         */
        public void onButtonAddClick(View v, int value);
    }

    private OnButtonClickListener listener;

    /**
     * 设置增和减按钮的监听
     * @param listener
     */
    public void setOnButtonClickListener(OnButtonClickListener listener) {
        this.listener = listener;
    }
}
