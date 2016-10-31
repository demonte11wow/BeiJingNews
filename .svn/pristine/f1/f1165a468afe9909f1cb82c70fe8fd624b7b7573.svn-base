package com.example.palo.beijinnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.palo.beijinnews.R;
import com.example.palo.beijinnews.domain.ShoppingCart;
import com.example.palo.beijinnews.utils.CartProvider;
import com.example.palo.beijinnews.utils.Constants;
import com.example.palo.beijinnews.view.NumberAddSubView;

import java.util.List;

/**
 * 作者：尚硅谷-杨光福 on 2016/10/26 10:34
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：适配器
 */
public class ShoppingCartPagerAdpater extends RecyclerView.Adapter<ShoppingCartPagerAdpater.ViewHolder> {


    private final Context context;
    private  List<ShoppingCart> datas;
    private CartProvider cartProvider;
    private  CheckBox checkbox_all;
    private TextView tv_total_price;

    public ShoppingCartPagerAdpater(Context context, List<ShoppingCart> list, CheckBox checkbox_alls, TextView tv_total_price) {
        this.context = context;
        this.datas = list;
        cartProvider = new CartProvider(context);
        this.checkbox_all = checkbox_alls;
        this.tv_total_price = tv_total_price;

        showTotalPrice();


        //设置item的点击事件
        setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //1.状态要变化
                ShoppingCart cart = datas.get(position);
                cart.setIsCheck(!cart.isCheck());//状态取反
                notifyItemChanged(position);//刷新状态

                //保持状态
                cartProvider.update(cart);

                //2.设置全选和反选
                checkAll_none();

                //3.计算总价格
                showTotalPrice();
            }
        });



        //校验是否全选
        checkAll_none();
        //设置CheckBox的点击事件
        checkbox_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.得到CheckBox的状态和设置全选和非全选
                checkAll_none(checkbox_all.isChecked());

                //2.显示总价格
                showTotalPrice();

            }
        });
    }


    public void checkAll_none() {
        if(datas != null && datas.size() >0){

            int number = 0;
            for (int i=0;i<datas.size();i++){
                ShoppingCart cart = datas.get(i);
                if(!cart.isCheck()){//设置非全选
                    checkbox_all.setChecked(false);
                }else{
                    //选中
                    number++;
                }
            }

            if(number == datas.size()){
                checkbox_all.setChecked(true);
            }
        }
    }
    public void checkAll_none(boolean isCheck) {
        if(datas != null && datas.size() >0){

            for (int i=0;i<datas.size();i++){
                ShoppingCart cart = datas.get(i);
                cart.setIsCheck(isCheck);//设置某条的状态
                notifyItemChanged(i);//刷新适配器
            }

        }
    }

    public void deleteData() {
        if (datas != null && datas.size() >0 ){
            for (int i=0;i<datas.size();i++){

                ShoppingCart cart = datas.get(i);//删除选中的
                if(cart.isCheck()){
                    datas.remove(cart);
                    cartProvider.deleteDeta(cart);
                    notifyItemRemoved(i);
                    i--;
                }

            }
        }
    }

    /**
     * 显示总价格
     */
    public void showTotalPrice() {
        tv_total_price.setText("合计￥" + getTotalPrice());
    }

    /**
     * 循环把总价格给计算出来
     *
     * @return
     */
    public float getTotalPrice() {
        float result = 0;
        if (datas != null && datas.size() > 0) {
            for (int i = 0; i < datas.size(); i++) {
                ShoppingCart cart = datas.get(i);//购物车类：是否被选中和多少个
                //是否被勾选
                if(cart.isCheck()){
                    //得到价格，并且和之前的相加
//                    result = result + cart.getCount()*cart.getPrice();

                    result += cart.getCount()*cart.getPrice();
                }
            }
        }
        return result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_shopping_cart_pager, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //1.根据位置得到对应的数据
       final ShoppingCart cart = datas.get(position);

        //2.绑定数据
        Glide.with(context)
                .load(cart.getImgUrl())
                .centerCrop()
                .placeholder(R.drawable.news_pic_default)
                .crossFade()
                .into(holder.iv_icon);
        holder.tv_name.setText(cart.getName());
        holder.tv_price.setText("￥" + cart.getPrice());
        holder.number_add_sub_view.setValue(cart.getCount());
        holder.number_add_sub_view.setMaxValue(Constants.mMaxValue);
        //是否选中
        holder.checkbox.setChecked(cart.isCheck());//设置是否选中状态

        holder.number_add_sub_view.setOnButtonClickListener(new NumberAddSubView.OnButtonClickListener() {
            @Override
            public void onButtonSubClick(View v, int value) {
                //1.把这个值设置一下
                cart.setCount(value);
                //2.保持到内存中和本地
                cartProvider.update(cart);
                //3.显示总价格
                showTotalPrice();
            }

            @Override
            public void onButtonAddClick(View v, int value) {
                //1.把这个值设置一下
                cart.setCount(value);

                //2.保持到内存中和本地
                cartProvider.update(cart);

                //3.显示总价格
                showTotalPrice();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 清除数据
     */
    public void clearData() {
        datas.clear();
        notifyItemRangeRemoved(0, datas.size());
    }

    /**
     * 添加数据
     *
     * @param count
     * @param list
     */
    public void addData(int count, List<ShoppingCart> list) {
        datas.addAll(count, list);
        notifyItemRangeChanged(count, datas.size());
    }

    /**
     * 得到多少条数据
     *
     * @return
     */
    public int getCount() {
        return datas.size();
    }

    public void addData(List<ShoppingCart> list) {
        addData(0, list);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkbox;

        private ImageView iv_icon;
        private TextView tv_name;
        private TextView tv_price;
        private NumberAddSubView number_add_sub_view;


        public ViewHolder(View itemView) {
            super(itemView);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            number_add_sub_view = (NumberAddSubView) itemView.findViewById(R.id.number_add_sub_view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener != null){
                        itemClickListener.onItemClick(v,getLayoutPosition());
                    }
                }
            });
        }
    }

    /**
     * 点击某个的监听器
     */
    public interface  OnItemClickListener{
        /**
         * 当点击某个的时候回调这个方法
         * @param view 被点击的视图
         * @param position 点击的位置
         */
        public void onItemClick(View view,int position);
    }
    private  OnItemClickListener itemClickListener;

    /**
     * 设置点击某条的监听
     * @param itemClickListener
     */
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}

