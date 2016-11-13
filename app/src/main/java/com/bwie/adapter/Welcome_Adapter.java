package com.bwie.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by Liuxiaoyu on 2016/11/9.
 */
public class Welcome_Adapter extends PagerAdapter {
    List<ImageView> list;

    public Welcome_Adapter(List<ImageView> list) {
        this.list = list;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView mImageView=list.get(position);
        container.addView(mImageView);
        return mImageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
