package com.example.palo.beijinnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.palo.beijinnews.R;
import com.example.palo.beijinnews.domain.ShoppingCart;
import com.example.palo.beijinnews.domain.ShoppingPagerBean;
import com.example.palo.beijinnews.utils.CartProvider;

import java.util.List;

/**
 * Created by palo on 2016/10/27.
 *
 */
public class ShoppingPagerAdapter extends RecyclerView.Adapter<ShoppingPagerAdapter.ViewHolder> {
    private final Context context;
    private final List<ShoppingPagerBean.Wares> datas;
    private CartProvider cartProvider;


    public ShoppingPagerAdapter(Context context, List<ShoppingPagerBean.Wares> list) {
        this.context = context;
        this.datas = list;
        cartProvider = new CartProvider(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_shopping_pager,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShoppingPagerBean.Wares listEntity = datas.get(position);
        Glide
                .with(context)
                .load(listEntity.getImgUrl())
                .centerCrop()
                .placeholder(R.drawable.pic_item_list_default)
                .crossFade()
                .into(holder.iv_icon);
        holder.tv_name.setText(listEntity.getName());
        holder.tv_price.setText("￥"+listEntity.getPrice());
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
        notifyItemRangeRemoved(0,datas.size());
    }

    /**
     * 添加数据
     * @param count
     * @param list
     */
    public void addData(int count, List<ShoppingPagerBean.Wares> list) {
        datas.addAll(count,list);
        notifyItemRangeChanged(count, datas.size());
    }

    /**
     * 得到多少条数据
     * @return
     */
    public int getCount() {
        return  datas.size();
    }

    public void addData(List<ShoppingPagerBean.Wares> list) {
        addData(0,list);
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv_icon;
        private TextView tv_name;
        private TextView tv_price;
        private Button btn_buy;



        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            btn_buy = (Button) itemView.findViewById(R.id.btn_buy);

            btn_buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, "购买", Toast.LENGTH_SHORT).show();

                    ShoppingPagerBean.Wares wares = datas.get(getLayoutPosition());
                    ShoppingCart cart = cartProvider.conversion(wares) ;
                    cartProvider.addData(cart);
                }
            });
        }
    }
}
