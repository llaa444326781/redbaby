package com.bwie.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.R;
import com.bwie.bean.Bean;

import java.util.List;

import MyAdapter.MyBaseCommAdapter;
import MyAdapter.ViewHolder;

/**
 * Created by Liuxiaoyu on 2016/11/15.
 */
public class Home_MyGridAdapter extends MyBaseCommAdapter<Bean.DataBean.TagBean> {
    public Home_MyGridAdapter(List<Bean.DataBean.TagBean> datas) {
        super(datas);
    }

    @Override
    protected void setUI(ViewHolder holder, int position, Context context) {
        Bean.DataBean.TagBean tagBean=getItem(position);
        ImageView img=holder.getItemView(R.id.home_gridimg);
        TextView textView=holder.getItemView(R.id.home_gridname);
        Glide.with(context)
                .load("http://image1.suning.cn"+tagBean.picUrl)
                .into(img);
        textView.setText(tagBean.elementName);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.home_griditem;
    }
}
