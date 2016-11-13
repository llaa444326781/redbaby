package com.bwie.act;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bwie.R;
import com.bwie.adapter.Welcome_Adapter;

import java.util.ArrayList;
import java.util.List;

import baseactivity.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @Bind(R.id.vp)
    ViewPager vp;
    int i = 3;
    @Bind(R.id.btn_start)
    Button btn_Start;
    @Bind(R.id.btn_pass)
    Button btn_Pass;
    private List<ImageView> iamgelist;
    private SharedPreferences sp;

    // 引导页图片资源
    private static final int[] pics = {R.mipmap.guide_page1, R.mipmap.guide_page2, R.mipmap.guide_page3};

    // 底部小点图片
    private ImageView[] dots;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            i--;
            if (i == 0) {
                Intent intent = new Intent(WelcomeActivity.this,
                        HomeActivity.class);
                startActivity(intent);
                finish();
            } else {

                handler.sendEmptyMessageDelayed(0, 1000);
            }
            super.handleMessage(msg);
        }

    };

    @Override
    public int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void getData() {
        Log.d("tag", "getData");
        iamgelist = new ArrayList<ImageView>();
        sp = getSharedPreferences("isFirst", MODE_PRIVATE);
        boolean isFirst = sp.getBoolean("isFirst", true);
        if (isFirst) {
            iamgelist = new ArrayList<ImageView>();
            for (int i = 0; i < pics.length; i++) {
                ImageView image = new ImageView(this);
                // 设置图片填充父控件
                image.setScaleType(ImageView.ScaleType.FIT_XY);
                image.setImageResource(pics[i]);
                iamgelist.add(image);
            }
            // 给ViewPager设置适配器
            vp.setAdapter(new Welcome_Adapter(iamgelist));
            vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                if(position==2){
                    btn_Pass.setVisibility(View.INVISIBLE);
                    btn_Start.setVisibility(View.VISIBLE);
                }else{
                    btn_Pass.setVisibility(View.VISIBLE);
                    btn_Start.setVisibility(View.INVISIBLE);
                }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            //进入主界面
            btn_Start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sp.edit().putBoolean("isFirst", false).commit();
                    startActivity(HomeActivity.class);
                }
            });
            //跳过
            btn_Pass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sp.edit().putBoolean("isFirst", false).commit();
                    startActivity(HomeActivity.class);
                }
            });
        } else {
            btn_Pass.setVisibility(View.GONE);
            btn_Start.setVisibility(View.GONE);
            List<ImageView> list = new ArrayList<ImageView>();
            ImageView image = new ImageView(this);
            // 设置图片填充父控件
            image.setScaleType(ImageView.ScaleType.FIT_XY);
            image.setImageResource(R.mipmap.load1);
            list.add(image);
            vp.setAdapter(new Welcome_Adapter(list));
            handler.sendEmptyMessageDelayed(0, 0);
        }

    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("isrem", false);
        edit.commit();
        super.onDestroy();
    }
}
