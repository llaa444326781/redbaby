package com.bwie.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.R;
import com.bwie.adapter.HomeVP_Adapter;
import com.bwie.adapter.Home_MyGridAdapter;
import com.bwie.adapter.Home_RvAdapter;
import com.bwie.bean.Bean;
import com.google.gson.Gson;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import baseactivity.BaseFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import uttils.OkHttpUtils;

public class FragmentHome extends BaseFragment {
    private ViewPager mViewpPager;
    private LinearLayout mLinearLayout;
    private GridView mGridView;
    private RecyclerView mRecyclerView;
    List<Bean.DataBean.TagBean> tagBeen=new ArrayList<>();
    private List<Bean.DataBean> list;
    private List<ImageView> viewList;
    private ArrayList<ImageView> dotList;
    private Button btn_goback;
    private ScrollView mScrollView;
    private ImageView btn_top_scan;
    public static final int REQUEST_CODE = 111;
    //屏幕高度 //默认高度为500
    private int screenHeight=500;
    List<Bean.DataBean> mylist=new ArrayList<>();

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 先去获取到当前条目索引
            int currentItem = mViewpPager.getCurrentItem();
            // 条目索引+1
            currentItem++;
            // 重新设置给viewPager
            mViewpPager.setCurrentItem(currentItem);
            // 再调用发延时消息的方法
            sendDelayMessage();
        }


    };
    private Home_RvAdapter home_rvAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ZXingLibrary.initDisplayOpinion(getActivity());
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initUI() {
        mViewpPager = findview(R.id.home_vp);
        mLinearLayout = findview(R.id.dot_ll);
        mGridView=findview(R.id.home_gridview);
        mRecyclerView=findview(R.id.home_rv);
        btn_goback=findview(R.id.home_goback);
        mScrollView=findview(R.id.home_scrollView);
        btn_top_scan=findview(R.id.top_scan);

        btn_top_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        btn_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
        /*mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int l, int t, int oldl, int oldt) {
               // 当前的左上角距离顶点距离 大于某个值的时候就显现置顶按钮出来 如果小于某个值就隐藏
                if (screenHeight != 0) {
                    if (t > screenHeight) {
                        btn_goback.setVisibility(View.VISIBLE);
                    } else {
                        btn_goback.setVisibility(View.GONE);
                    }
                }
            }
        });*/
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void getData() {

    }

    public void setViewPager() {
        // 初始化图片
        initViewList();
        // 初始化小圆点
        initDots();
        // 设置数据适配器
        mViewpPager.setAdapter(new HomeVP_Adapter(viewList, getActivity(), handler));
        // 设置初始的展示条目
        mViewpPager.setCurrentItem(viewList.size() * 100000);
        // 设置延时切换
        sendDelayMessage();

        /*// 监听viewPager的一个滑动事件
        mViewpPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 遍历小点的集合
                for (int i = 0; i < dotList.size(); i++) {
                    // 如果当前的索引值和i相等
                    if (position % dotList.size() == i) {
                        // 设置小点为亮色
                        dotList.get(i).setImageResource(R.drawable.dot_focuse);
                    } else {
                        // 否则暗色
                        dotList.get(i).setImageResource(R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });*/
    }

    /**
     * 发送延时消息
     */
    private void sendDelayMessage() {
        handler.sendEmptyMessageDelayed(0, 2000);
    }


    public  void initViewList() {
        // 先使用xutils的工具类
        viewList = new ArrayList<>();
        for (int i = 0; i < tagBeen.size(); i++) {
            // 创建imageView 并通过工具类将图片设置到控件上
            ImageView imageView = new ImageView(getActivity());
            Glide.with(getActivity())
                    .load("http://image1.suning.cn"+tagBeen.get(i).picUrl)
                    .into(imageView);
            viewList.add(imageView);
        }
    }

    public  void initDots() {
        // 创建一个装小点控件的集合
        dotList = new ArrayList<>();
        dotList.clear();
        for (int i = 0; i < tagBeen.size(); i++) {
            ImageView imageView = new ImageView(getActivity());

            if (i == 0) {
                // 如果是第一张，默认给一个亮的小点
                imageView.setImageResource(R.drawable.dot_focuse);
            } else {
                // 如果不是滴一个，默认给一个暗的小点
                imageView.setImageResource(R.drawable.dot_normal);
            }
            // 设置小点的默认宽高为20dp
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            // 设置小点的间距
            params.setMargins(5, 0, 5, 0);
            mLinearLayout.addView(imageView, params);
            // 往小点集合中添加view
            dotList.add(imageView);
        }
    }


    @Override
    public void loadData() {
        /*OkHttpUtils.get("http://lib.suning.com/app/redbaby/80000_5.0.0-459.json", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Bean bean = new Gson().fromJson(json, Bean.class);

                        tagBeen.addAll(bean.data.get(0).tag);
                        //轮播
                        setViewPager();
                        //签到领钱
                        mGridView.setAdapter(new Home_MyGridAdapter(bean.data.get(1).tag));

                        //十点秒杀
                        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(getActivity());
                        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                        mRecyclerView.setLayoutManager(mLinearLayoutManager);
                        mylist.add(bean.data.get(2));
                        home_rvAdapter = new Home_RvAdapter(getActivity(),mylist);
                        mRecyclerView.setAdapter(home_rvAdapter);

//                        List<Bean.DataBean> list=new ArrayList<Bean.DataBean>();
//                        list.add(bean.data.get(4));
//                        list.add(bean.data.get(5));
//                        list.add(bean.data.get(6));
//                        list.add(bean.data.get(7));
//                        mylist.addAll(list);
                        //傲娇品牌
                        Bean.DataBean beanPinPai = bean.data.get(4);
                        beanPinPai.tag.addAll(bean.data.get(5).tag);
                        beanPinPai.tag.addAll(bean.data.get(6).tag);
                        beanPinPai.tag.addAll(bean.data.get(7).tag);
                        mylist.add(beanPinPai);
                        home_rvAdapter.notifyDataSetChanged();
                        //母婴专区
                        Bean.DataBean beanMuYing = bean.data.get(9);
                        beanMuYing.tag.addAll(bean.data.get(10).tag);
                        beanMuYing.tag.addAll(bean.data.get(11).tag);
                        mylist.add(beanMuYing);
                        home_rvAdapter.notifyDataSetChanged();
                        //主题特卖
                        mylist.add(bean.data.get(13));
                        home_rvAdapter.notifyDataSetChanged();
                        //洋货超值购
                        mylist.add(bean.data.get(14));
                        home_rvAdapter.notifyDataSetChanged();
                        //洋货下
                        mylist.add(bean.data.get(15));
                        home_rvAdapter.notifyDataSetChanged();
                        //聚优
                        mylist.add(bean.data.get(16));
                        home_rvAdapter.notifyDataSetChanged();
                        //聚优下
                        mylist.add(bean.data.get(17));
                        home_rvAdapter.notifyDataSetChanged();
                        //加量
                        mylist.add(bean.data.get(18));
                        home_rvAdapter.notifyDataSetChanged();
                        //加量下
                        mylist.add(bean.data.get(19));
                        home_rvAdapter.notifyDataSetChanged();
                        //省钱
                        mylist.add(bean.data.get(20));
                        home_rvAdapter.notifyDataSetChanged();
                        //省钱下
                        mylist.add(bean.data.get(21));
                        home_rvAdapter.notifyDataSetChanged();
                        //辣妈拼团
                        mylist.add(bean.data.get(23));
                        home_rvAdapter.notifyDataSetChanged();

                        mylist.add(bean.data.get(24));
                        home_rvAdapter.notifyDataSetChanged();

                        mylist.add(bean.data.get(26));
                        home_rvAdapter.notifyDataSetChanged();

                        mylist.add(bean.data.get(28));
                        home_rvAdapter.notifyDataSetChanged();
                        mylist.add(bean.data.get(30));
                        home_rvAdapter.notifyDataSetChanged();
                        mylist.add(bean.data.get(32));
                        home_rvAdapter.notifyDataSetChanged();
                        //查看更多
                        mylist.add(bean.data.get(33));
                        home_rvAdapter.notifyDataSetChanged();
                    }
                });


            }
        });*/
        new Thread(){

            @Override
            public void run() {
                super.run();
                InputStream inputStream;
                try {
                    inputStream = getActivity().getAssets().open("redbabyjson.txt");
                    byte[] bytes = new byte[1024];
                    int len;
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    while ((len = inputStream.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, len);
                    }
                    final String json = outputStream.toString("gbk");
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bean bean = new Gson().fromJson(json, Bean.class);

                            tagBeen.addAll(bean.data.get(0).tag);
                            //轮播
                            setViewPager();
                            //签到领钱
                            mGridView.setAdapter(new Home_MyGridAdapter(bean.data.get(1).tag));

                            //十点秒杀
                            LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(getActivity());
                            mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            mRecyclerView.setLayoutManager(mLinearLayoutManager);
                            mylist.add(bean.data.get(2));
                            home_rvAdapter = new Home_RvAdapter(getActivity(),mylist);
                            mRecyclerView.setAdapter(home_rvAdapter);

//                        List<Bean.DataBean> list=new ArrayList<Bean.DataBean>();
//                        list.add(bean.data.get(4));
//                        list.add(bean.data.get(5));
//                        list.add(bean.data.get(6));
//                        list.add(bean.data.get(7));
//                        mylist.addAll(list);
                            //傲娇品牌
                            Bean.DataBean beanPinPai = bean.data.get(4);
                            beanPinPai.tag.addAll(bean.data.get(5).tag);
                            beanPinPai.tag.addAll(bean.data.get(6).tag);
                            beanPinPai.tag.addAll(bean.data.get(7).tag);
                            mylist.add(beanPinPai);
                            home_rvAdapter.notifyDataSetChanged();
                            //母婴专区
                            Bean.DataBean beanMuYing = bean.data.get(9);
                            beanMuYing.tag.addAll(bean.data.get(10).tag);
                            beanMuYing.tag.addAll(bean.data.get(11).tag);
                            mylist.add(beanMuYing);
                            home_rvAdapter.notifyDataSetChanged();
                            //主题特卖
                            mylist.add(bean.data.get(13));
                            home_rvAdapter.notifyDataSetChanged();
                            //洋货超值购
                            mylist.add(bean.data.get(14));
                            home_rvAdapter.notifyDataSetChanged();
                            //洋货下
                            mylist.add(bean.data.get(15));
                            home_rvAdapter.notifyDataSetChanged();
                            //聚优
                            mylist.add(bean.data.get(16));
                            home_rvAdapter.notifyDataSetChanged();
                            //聚优下
                            mylist.add(bean.data.get(17));
                            home_rvAdapter.notifyDataSetChanged();
                            //加量
                            mylist.add(bean.data.get(18));
                            home_rvAdapter.notifyDataSetChanged();
                            //加量下
                            mylist.add(bean.data.get(19));
                            home_rvAdapter.notifyDataSetChanged();
                            //省钱
                            mylist.add(bean.data.get(20));
                            home_rvAdapter.notifyDataSetChanged();
                            //省钱下
                            mylist.add(bean.data.get(21));
                            home_rvAdapter.notifyDataSetChanged();
                            //辣妈拼团
                            mylist.add(bean.data.get(23));
                            home_rvAdapter.notifyDataSetChanged();

                            mylist.add(bean.data.get(24));
                            home_rvAdapter.notifyDataSetChanged();

                            mylist.add(bean.data.get(26));
                            home_rvAdapter.notifyDataSetChanged();

                            mylist.add(bean.data.get(28));
                            home_rvAdapter.notifyDataSetChanged();
                            mylist.add(bean.data.get(30));
                            home_rvAdapter.notifyDataSetChanged();
                            mylist.add(bean.data.get(32));
                            home_rvAdapter.notifyDataSetChanged();
                            //查看更多
                            mylist.add(bean.data.get(33));
                            home_rvAdapter.notifyDataSetChanged();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
}
