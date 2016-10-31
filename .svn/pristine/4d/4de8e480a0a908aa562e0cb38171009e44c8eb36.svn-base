package com.example.palo.beijinnews.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.palo.beijinnews.MainActivity;
import com.example.palo.beijinnews.R;
import com.example.palo.beijinnews.base.BaseFragment;
import com.example.palo.beijinnews.domain.NewsCenterPagerBean2;
import com.example.palo.beijinnews.pager.NewsCenterPager;
import com.example.palo.beijinnews.utils.DensityUtil;
import com.example.palo.beijinnews.utils.LogUtil;

import java.util.List;


/**
 * 作者：尚硅谷-杨光福 on 2016/10/15 14:56
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：xxxx
 */
public class LeftMenuFragment extends BaseFragment {


    private ListView listView;
    private LeftMenuFragmentAdpater adpater;

    /**
     * 左侧菜单对应的数据
     */
    private List<NewsCenterPagerBean2.NewsCenterPagerData> leftdata;

    /**
     * 被点击过的位置
     */
    private int selectPosition;

    @Override
    public View initView() {
        LogUtil.e("左侧菜单的视图被初始化了...");
        listView = new ListView(context);
        listView.setPadding(0, DensityUtil.dip2px(context,40),0,0);
        listView.setDividerHeight(0);
        //按下某个设置为没有效果
        listView.setSelector(android.R.color.transparent);
        //屏蔽ListView在低版本的手机上，整个会变灰
        listView.setCacheColorHint(Color.TRANSPARENT);

        //设置点击某一条
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                //1.点击的时候，设置被点击的高亮
                selectPosition = position;
                adpater.notifyDataSetChanged();//getCount()-->getView();

                //2.左侧菜单收起
                MainActivity mainActivity = (MainActivity) context;
                mainActivity.getSlidingMenu().toggle();//开<-->关

                //3.点击的时候
                swichPager(selectPosition);


            }
        });
        return listView;
    }

    /**
     * 根据位置切换到对应的详情页面
     * @param selectPosition
     */
    private void swichPager(int selectPosition) {
        MainActivity mainActivity = (MainActivity) context;
        //3.切换到不同的页面
        ContentFragment contentFragment = mainActivity.getContentFragment();
        //得到新闻中心
        NewsCenterPager newsCenterPager = contentFragment.getNewsCenterPager();
        //调用新闻中心的方法切换到对应的页面
        newsCenterPager.swichPager(selectPosition);
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("左侧菜单的数据被初始化了...");
    }

    public void setData(List<NewsCenterPagerBean2.NewsCenterPagerData> leftdata) {
        this.leftdata = leftdata;
//        for(int i=0;i<leftdata.size();i++){
//            LogUtil.e(leftdata.get(i).getTitle());
//        }

        adpater = new LeftMenuFragmentAdpater();
        //设置适配器
        listView.setAdapter(adpater);

        swichPager(selectPosition);

    }

    class LeftMenuFragmentAdpater extends BaseAdapter {

        @Override
        public int getCount() {
            return leftdata.size();
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

            TextView textView = (TextView) View.inflate(context, R.layout.item_leftmenufragment,null);

            textView.setText(leftdata.get(position).getTitle());

//            if(position ==selectPosition){
//                textView.setEnabled(true);
//            }else{
//                textView.setEnabled(false);
//            }
            textView.setEnabled(position ==selectPosition);
            return textView;
        }
    }
}