package com.example.palo.beijinnews.pager.detailpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.palo.beijinnews.R;
import com.example.palo.beijinnews.base.MenuDetailBasePager;
import com.example.palo.beijinnews.domain.NewsCenterPagerBean2;
import com.example.palo.beijinnews.domain.TabDetailPagerBean;
import com.example.palo.beijinnews.utils.CacheUtils;
import com.example.palo.beijinnews.utils.Constants;
import com.example.palo.beijinnews.utils.LogUtil;
import com.example.palo.beijinnews.view.HorizontalScrollViewPager;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.extras.SoundPullEventListener;

import org.xutils.common.Callback;
import org.xutils.common.util.DensityUtil;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 作者：尚硅谷-杨光福 on 2016/10/18 09:42
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：12个页签页面
 */
public class TopicTabDetailPager extends MenuDetailBasePager {
    private final NewsCenterPagerBean2.NewsCenterPagerData.ChildrenData childrenData;

    @ViewInject(R.id.viewpager)
    private HorizontalScrollViewPager viewpager;

    @ViewInject(R.id.tv_title)
    private TextView tv_title;

    @ViewInject(R.id.ll_point_group)
    private LinearLayout ll_point_group;

    @ViewInject(R.id.pull_refresh_list)
    private PullToRefreshListView pull_refresh_list;

    private ListView listview;

    /**
     * 之前被高亮显示的点
     */
    private int prePosition;
    /**
     * 顶部新闻数据集合
     */
    private List<TabDetailPagerBean.DataEntity.TopnewsEntity> topnews;
    /**
     * 新闻列表的数据
     */
    private List<TabDetailPagerBean.DataEntity.NewsEntity> news;

    private TabDetailPagerListAdapter adapter;
    private TabDetailPagerBean.DataEntity.NewsEntity newsEntity;
    private String url;
    /**
     * 加载更多的url
     */
    private String moreUrl;
    /**
     * 是否加载更多
     */
    private boolean isLoadMore = false;

    public TopicTabDetailPager(Context context, NewsCenterPagerBean2.NewsCenterPagerData.ChildrenData childrenData) {
        super(context);
        this.childrenData = childrenData;
    }


    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.topic_tabdetail_pager, null);
        x.view().inject(TopicTabDetailPager.this, view);

        View topnews = View.inflate(context, R.layout.topnews, null);
        x.view().inject(TopicTabDetailPager.this, topnews);
//

        listview = pull_refresh_list.getRefreshableView();//ListView

//        listview.addHeaderView(button);
        //以头的方式添加顶部轮播图
        listview.addHeaderView(topnews);


//        listview.addTopNewsView(topnews);
//
//        //设置刷新的监听
//        listview.setOnRefreshListener(new MyOnRefreshListener());
        pull_refresh_list.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //请求网络
                getDataFromNet(url);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (TextUtils.isEmpty(moreUrl)){
                    //没有更多
                    Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
//                    listview.onFinishRefrsh(false);
                    pull_refresh_list.onRefreshComplete();
                }else {
                    //加载更多
                    getModeDataFromNet();

                }
            }
        });

        /**
         * Add Sound Event Listener
         */
        SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(context);
        soundListener.addSoundEvent(PullToRefreshBase.State.PULL_TO_REFRESH, R.raw.pull_event);
        soundListener.addSoundEvent(PullToRefreshBase.State.RESET, R.raw.reset_sound);
        soundListener.addSoundEvent(PullToRefreshBase.State.REFRESHING, R.raw.refreshing_sound);
        pull_refresh_list.setOnPullEventListener(soundListener);

        return view;
    }

//    class MyOnRefreshListener implements RefreshListView.OnRefreshListener {
//
//        @Override
//        public void onPullDownRefresh() {
//            //请求网络
//            getDataFromNet(url);
//        }
//
//        @Override
//        public void onLoadeMore() {
//
//            if (TextUtils.isEmpty(moreUrl)){
//                //没有更多
//                Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
//                listview.onFinishRefrsh(false);
//            }else {
//                //加载更多
//                getModeDataFromNet();
//
//            }
//        }
//    }

    @Override
    public void initData() {
        super.initData();
        url = Constants.BASE_URL + childrenData.getUrl();
        LogUtil.e(TopicTabDetailPager.this + ":" + url);

        String saveJson = CacheUtils.getString(context, url);
        if (!TextUtils.isEmpty(saveJson)) {
            processData(saveJson);
        }

        //请求网络
        getDataFromNet(url);
    }

    private void getModeDataFromNet() {
        RequestParams params = new RequestParams(moreUrl);
        params.setConnectTimeout(4000);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("TabDetailPager加载更多联网请求成功==" + result);
                isLoadMore = true;
                processData(result);
//                listview.onFinishRefrsh(false);
                pull_refresh_list.onRefreshComplete();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("TabDetailPager加载更多联网请求失败==" + ex.getMessage());
//                listview.onFinishRefrsh(false);
                pull_refresh_list.onRefreshComplete();
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

    private void getDataFromNet(final String url) {
        RequestParams params = new RequestParams(url);
        params.setConnectTimeout(4000);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("TabDetailPager联网请求成功==" + result);
                CacheUtils.putString(context, url, result);
                processData(result);
//                listview.onFinishRefrsh(true);
                pull_refresh_list.onRefreshComplete();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("TabDetailPager联网请求失败==" + ex.getMessage());
//                listview.onFinishRefrsh(false);
                pull_refresh_list.onRefreshComplete();
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

    private void processData(String json) {

        TabDetailPagerBean pagerBean = paraseJson(json);
        //顶部新闻数据集合
        topnews = pagerBean.getData().getTopnews();

        moreUrl = pagerBean.getData().getMore();//""

        if (TextUtils.isEmpty(moreUrl)) {
            moreUrl = "";
        } else {
            moreUrl = Constants.BASE_URL + pagerBean.getData().getMore();
        }

        if(!isLoadMore){
            //原来的请求

            if (topnews != null && topnews.size() > 0) {

                viewpager.setAdapter(new TabDetailPagerAdapter());

                //监听页面的变化
                viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

                tv_title.setText(topnews.get(prePosition).getTitle());

                //把之前的红点全部移除
                ll_point_group.removeAllViews();
                for (int i = 0; i < topnews.size(); i++) {

                    ImageView imageView = new ImageView(context);
                    imageView.setBackgroundResource(R.drawable.point_selector);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(5), DensityUtil.dip2px(5));
                    if (i != 0) {
                        imageView.setEnabled(false);
                        params.leftMargin = DensityUtil.dip2px(5);
                    } else {
                        imageView.setEnabled(true);
                    }
                    imageView.setLayoutParams(params);

                    //把点添加到线性布局
                    ll_point_group.addView(imageView);
                }

//            ll_point_group.getChildAt(prePosition).setEnabled(true);

            }

            //设置ListView的适配器
            news = pagerBean.getData().getNews();
            if (news != null && news.size() > 0) {
                adapter = new TabDetailPagerListAdapter();
                listview.setAdapter(adapter);

            }

//        LogUtil.e(pagerBean.getData().getTopnews().get(1).getTitle());
        }else{
            //加载更多
            isLoadMore = false;
            //把得到的更多数据加载到原来的集合中
            news.addAll(pagerBean.getData().getNews());
            adapter.notifyDataSetChanged();

        }



    }

    class TabDetailPagerListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHodler viewHodler = null;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_tabdetail_pager, null);
                viewHodler = new ViewHodler();
                viewHodler.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHodler.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                viewHodler.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
                convertView.setTag(viewHodler);
            } else {
                viewHodler = (ViewHodler) convertView.getTag();
            }

            //根据位置得到对应的数据
            newsEntity = news.get(position);
            viewHodler.tv_title.setText(newsEntity.getTitle());
            viewHodler.tv_time.setText(newsEntity.getPubdate());

            //请求图片
            x.image().bind(viewHodler.iv_icon, Constants.BASE_URL + newsEntity.getListimage());


            return convertView;
        }
    }

    static class ViewHodler {
        ImageView iv_icon;
        TextView tv_title;
        TextView tv_time;
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //把之前高亮的点设置为默认
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            //把当前的位置对应的点设置高亮
            ll_point_group.getChildAt(position).setEnabled(true);

            prePosition = position;
        }

        @Override
        public void onPageSelected(int position) {
            tv_title.setText(topnews.get(position).getTitle());

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    class TabDetailPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(context);
            imageView.setBackgroundResource(R.drawable.pic_item_list_default);

            x.image().bind(imageView, Constants.BASE_URL + topnews.get(position).getTopimage());
            container.addView(imageView);

            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return topnews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    /**
     * json解析数据
     *
     * @param json
     * @return
     */
    private TabDetailPagerBean paraseJson(String json) {
        return new Gson().fromJson(json, TabDetailPagerBean.class);
    }
}
