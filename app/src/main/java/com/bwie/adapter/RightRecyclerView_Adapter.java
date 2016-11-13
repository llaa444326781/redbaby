package com.bwie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bwie.R;
import com.bwie.bean.ChildrenBean;
import com.bwie.bean.RedBaby;

import java.util.List;

/**
 * Created by Liuxiaoyu on 2016/11/12.
 */

public class RightRecyclerView_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<ChildrenBean> alllist;
    Context context;
    public static final int TYPE_LIST = 0, TYPE_GRID = 1;

    public RightRecyclerView_Adapter(List<ChildrenBean> alllist, Context context) {
        this.alllist = alllist;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RightRecyclerView_ViewHolder1 holder1 = new RightRecyclerView_ViewHolder1(LayoutInflater.from(context).inflate(R.layout.recyclerview_rightitem1, parent,
                false));
        RightRecyclerView_ViewHolder2 holder2 = new RightRecyclerView_ViewHolder2(LayoutInflater.from(context).inflate(R.layout.recyclerview_rightitem2, parent,
                false));
        if (viewType == TYPE_LIST) {
            return holder1;
        } else {
            return  holder2;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof RightRecyclerView_ViewHolder1){
        RightRecyclerView_ViewHolder1 view_viewHolder1= (RightRecyclerView_ViewHolder1) holder;
            view_viewHolder1.tv_name.setText(alllist.get(position).getDirName());
        }else{
            RightRecyclerView_ViewHolder2 view_viewHolder2= (RightRecyclerView_ViewHolder2) holder;
            view_viewHolder2.godname.setText(alllist.get(position).getDirName());
            Glide.with(context)
                    .load(alllist.get(position).getImgApp())
                    .into(view_viewHolder2.img);
        }
    }

    @Override
    public int getItemCount() {
        return alllist.size();
    }

    @Override
    public int getItemViewType(int position) {
        return alllist.get(position).isHeader ? 0: 1;
    }
}
