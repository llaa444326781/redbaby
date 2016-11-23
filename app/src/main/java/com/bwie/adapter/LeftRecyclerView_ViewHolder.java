package com.bwie.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.R;

/**
 * Created by Liuxiaoyu on 2016/11/11.
 */
public class LeftRecyclerView_ViewHolder extends RecyclerView.ViewHolder {
    TextView tv_typename;
    View view;
    LinearLayout ll;
    public LeftRecyclerView_ViewHolder(View itemView) {
        super(itemView);
        ll= (LinearLayout) itemView.findViewById(R.id.linerLayout);
        tv_typename= (TextView) itemView.findViewById(R.id.tv_typename);
        view=itemView.findViewById(R.id.yellow);
    }
}
