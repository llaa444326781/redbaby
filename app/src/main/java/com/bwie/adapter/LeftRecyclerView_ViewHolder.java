package com.bwie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bwie.R;

/**
 * Created by Liuxiaoyu on 2016/11/11.
 */
public class LeftRecyclerView_ViewHolder extends RecyclerView.ViewHolder {
    TextView tv_typename;
    public LeftRecyclerView_ViewHolder(View itemView) {
        super(itemView);
        tv_typename= (TextView) itemView.findViewById(R.id.tv_typename);
    }
}
