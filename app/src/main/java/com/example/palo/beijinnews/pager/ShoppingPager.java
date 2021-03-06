package com.example.palo.beijinnews.pager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.palo.beijinnews.R;
import com.example.palo.beijinnews.adapter.ShoppingPagerAdapter;
import com.example.palo.beijinnews.base.BasePager;
import com.example.palo.beijinnews.domain.ShoppingPagerBean;
import com.example.palo.beijinnews.utils.CacheUtils;
import com.example.palo.beijinnews.utils.Constants;
import com.example.palo.beijinnews.utils.LogUtil;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;


/**
 * 作者：尚硅谷-杨光福 on 2016/10/17 09:06
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：商城页面
 */
public class ShoppingPager extends BasePager {
    private ShoppingPagerAdapter adpater;
    private MaterialRefreshLayout refresh;
    private RecyclerView recyclerview;
    private ProgressBar progressbar;
    private String url;
    /**
     * 每页要求10个数据
     */
    private int pageSize = 10;
    /**
     * 第几页
     */
    private int curPage = 1;
    /**
    总的多少页
    */
    private int totalPager;
    /**
     * 商城热卖的数据集合
     */

    /**
     * 默认状态
     */
    private static  final int STATE_NORMAL = 1;

    /**
     * 下拉刷新
     */
    private static  final int STATE_REFRESH = 2;

    /**
     * 上拉刷新
     */
    private static  final int STATE_LOADMORE = 3;
    /**
     * 当前状态
     */
    private int currentState = STATE_NORMAL;
    private List<ShoppingPagerBean.Wares> list;


    public ShoppingPager(Context context) {
        super(context);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("商城面数据加载了....");
        //设置标题
        //创建子页面的视图
        tv_title.setText("商城");
        View view = View.inflate(context, R.layout.shopping_pager, null);
        refresh = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        fl_base_content.removeAllViews();
        //子页面的视图和FrameLayout结合在一起，形成一个新的页面
        fl_base_content.addView(view);

        //设置下拉刷新和上拉刷新的监听
        initRefreshLayout();
        getDataFromNet();
    }

    private void initRefreshLayout() {

        refresh.setMaterialRefreshListener(new MyMaterialRefreshListener());
    }

    class MyMaterialRefreshListener extends MaterialRefreshListener {

        /**
         * 下拉刷新
         */
        @Override
        public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
            refreshData();
        }

        /**
         * 上拉刷新（加载更多）
         * @param materialRefreshLayout
         */
        @Override
        public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
            loadMoreData();
        }
    }

    private void loadMoreData() {
        currentState = STATE_LOADMORE;
        if(curPage < totalPager){
            curPage +=1;
            //联网请求
            url = Constants.WARES_HOT_URL + "pageSize=" + pageSize + "&curPage=" + curPage;
            RequestParams params = new RequestParams(url);
            x.http().get(params, new Callback.CommonCallback<String>() {
                @Override
                public void onSuccess(String result) {
                    LogUtil.e("联网成功==" + result);

                    //数据保存起来
                    CacheUtils.putString(context, Constants.WARES_HOT_URL, result);
                    processData(result);
                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback) {
                    LogUtil.e("联网失败==" + ex.getMessage());
                }

                @Override
                public void onCancelled(CancelledException cex) {
                    LogUtil.e("onCancelled==" + cex.getMessage());
                }

                @Override
                public void onFinished() {
                    LogUtil.e("onFinished==");
                }
            });


        }else{
            //没有更多页面
            refresh.finishRefreshLoadMore();
            Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
        }


    }

    private void refreshData() {
        curPage = 1;
        currentState = STATE_REFRESH;
        url = Constants.WARES_HOT_URL + "pageSize=" + pageSize + "&curPage=" + curPage;
        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("联网成功==" + result);

                //数据保存起来
                CacheUtils.putString(context, Constants.WARES_HOT_URL, result);

                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
            }
        });
    }


    private void getDataFromNet() {
        currentState = STATE_NORMAL;
        curPage = 1;
        url = Constants.WARES_HOT_URL + "pageSize=" + pageSize + "&curPage=" + curPage;
        String saveJson = CacheUtils.getString(context, Constants.WARES_HOT_URL);
        if (!TextUtils.isEmpty(saveJson)) {
            processData(saveJson);
        }

        RequestParams params = new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("联网成功==" + result);

                //数据保存起来
                CacheUtils.putString(context, Constants.WARES_HOT_URL, result);

                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("联网失败==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                LogUtil.e("onCancelled==" + cex.getMessage());
            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
            }
        });


    }

    /**
     * 解析和显示数据
     *
     * @param json
     */
    private void processData(String json) {

        ShoppingPagerBean bean = new Gson().fromJson(json, ShoppingPagerBean.class);
        curPage = bean.getCurrentPage();
        pageSize = bean.getPageSize();
        totalPager = bean.getTotalPage();
        list = bean.getList();
        if (list != null && list.size() > 0) {
            LogUtil.e("curPage==" + curPage + ",pageSize==" + pageSize + ",totalPager==" + totalPager + ",name==" + bean.getList().get(0).getName());
            showData();
//            //有数据
//            adpater = new ShoppingPagerAdapter(context, list);
//            //设置适配器
//            recyclerview.setAdapter(adpater);
//            //设置ListView样式
//            recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        }
        progressbar.setVisibility(View.GONE);

    }

    private void showData() {

        switch (currentState) {
            case STATE_NORMAL:
                //有数据
                adpater = new ShoppingPagerAdapter(context, list);
                //设置适配器
                recyclerview.setAdapter(adpater);
                //设置ListView样式
                recyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                break;
            case STATE_REFRESH:

                //清除数据
                adpater.clearData();
                adpater.addData(list);
                refresh.finishRefresh();//下拉刷新状态还原


                break;
            case STATE_LOADMORE:
                adpater.addData(adpater.getCount(), list);
                refresh.finishRefreshLoadMore();//加载更多状态还原

                break;
        }
    }
}
