package com.example.palo.beijinnews.pager.detailpager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.example.palo.beijinnews.R;
import com.example.palo.beijinnews.activity.ActivityTransitionToActivity;
import com.example.palo.beijinnews.base.MenuDetailBasePager;
import com.example.palo.beijinnews.domain.NewsCenterPagerBean2;
import com.example.palo.beijinnews.domain.PhotosDetailPagerBean;
import com.example.palo.beijinnews.utils.CacheUtils;
import com.example.palo.beijinnews.utils.Constants;
import com.example.palo.beijinnews.utils.LogUtil;
import com.example.palo.beijinnews.volley.VolleyManager;
import com.google.gson.Gson;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.util.List;


/**
 * 作者：尚硅谷-杨光福 on 2016/10/17 11:27
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：图组详情页面
 */
public class PhotosDeatailPager extends MenuDetailBasePager {

    private final NewsCenterPagerBean2.NewsCenterPagerData newsCenterPagerData;
    @ViewInject(R.id.listview)
    private ListView listview;
    @ViewInject(R.id.gridview)
    private GridView gridview;
    private String photosUrl;
    private List<PhotosDetailPagerBean.DataEntity.NewsEntity> news;


    public PhotosDeatailPager(Context context, NewsCenterPagerBean2.NewsCenterPagerData newsCenterPagerData) {
        super(context);
        this.newsCenterPagerData = newsCenterPagerData;
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.photos_detail_pager, null);
        x.view().inject(this, view);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imageUrl = Constants.BASE_URL + news.get(position).getListimage();
                transition(view, imageUrl);
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imageUrl = Constants.BASE_URL + news.get(position).getListimage();
                transition(view, imageUrl);
            }
        });

        return view;
    }


    private void transition(View view,String iamgeUrl) {
        if (Build.VERSION.SDK_INT < 21) {
            Toast.makeText(context, "21+ only, keep out", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(context, ActivityTransitionToActivity.class);
            intent.setData(Uri.parse(iamgeUrl));
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, view, "test");
            context.startActivity(intent, options.toBundle());
        }
    }


    @Override
    public void initData() {
        super.initData();
        photosUrl = Constants.BASE_URL + newsCenterPagerData.getUrl();

       String saveJson =  CacheUtils.getString(context,photosUrl);
        if (!TextUtils.isEmpty(saveJson)){
            processData(saveJson);
        }

        StringRequest stringQues = new StringRequest(Request.Method.GET, photosUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                LogUtil.e("图组请求数据成功=="+s);
                //解析json数据
                CacheUtils.putString(context,photosUrl,s);
                processData(s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtil.e("图组请求数据失败=="+volleyError.getMessage());
            }
        }) {

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String parsed;
                try {
                    parsed = new String(response.data, "UTF-8");
                    return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException var4) {
                    parsed = new String(response.data);
                }

                return super.parseNetworkResponse(response);
            }
        };
        VolleyManager.addRequest(stringQues, "photos");
    }

    private void processData(String json) {

        PhotosDetailPagerBean bean = new Gson().fromJson(json, PhotosDetailPagerBean.class);
        news =bean.getData().getNews();
        if(news != null && news.size() >0){

            //设置适配器
            listview.setAdapter(new PhotosDetailPagerAdapter());
        }
    }

    class PhotosDetailPagerAdapter extends BaseAdapter {

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
            ViewHolder viewHolder = null;
            if(convertView ==null){
                convertView =View.inflate(context,R.layout.item_photos_detail_pager,null);
                viewHolder = new ViewHolder();
                viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
                viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //根据位置得到对应的数据
            PhotosDetailPagerBean.DataEntity.NewsEntity item = news.get(position);
            viewHolder.tv_title.setText(item.getTitle());
            loaderImager(viewHolder,Constants.BASE_URL+item.getListimage());
            return convertView;
        }
    }

    /**
     *
     * @param viewHolder
     * @param imageurl
     */
    private void loaderImager(final ViewHolder viewHolder, String imageurl) {

        viewHolder.iv_icon.setTag(imageurl);
        //直接在这里请求会乱位置
        ImageLoader.ImageListener listener = new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                if (imageContainer != null) {

                    if (viewHolder.iv_icon != null) {
                        if (imageContainer.getBitmap() != null) {
                            viewHolder.iv_icon.setImageBitmap(imageContainer.getBitmap());
                        } else {
                            viewHolder.iv_icon.setImageResource(R.drawable.pic_item_list_default);
                        }
                    }
                }
            }
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //如果出错，则说明都不显示（简单处理），最好准备一张出错图片
                viewHolder.iv_icon.setImageResource(R.drawable.pic_item_list_default);
            }
        };
        VolleyManager.getImageLoader().get(imageurl, listener);
    }



    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_title;
    }

    /**
     * true:显示ListView,但隐藏GridView
     * false:显示GridView，但隐藏ListView
     */
    private boolean isListview = true;

    public void swicheListAndGrid(ImageButton ib_swich_list_grid) {
        if (isListview) {
            isListview = false;
            //显示GridView
            gridview.setVisibility(View.VISIBLE);
            gridview.setAdapter(new PhotosDetailPagerAdapter());
            //隐藏ListView
            listview.setVisibility(View.GONE);

            //按钮状态 =ListView
            ib_swich_list_grid.setImageResource(R.drawable.icon_pic_list_type);
        } else {
            isListview = true;
            //显示ListView
            listview.setVisibility(View.VISIBLE);
            listview.setAdapter(new PhotosDetailPagerAdapter());
            //隐藏GridView
            gridview.setVisibility(View.GONE);

            //按钮显示-GridView
            ib_swich_list_grid.setImageResource(R.drawable.icon_pic_grid_type);
        }

    }
}
