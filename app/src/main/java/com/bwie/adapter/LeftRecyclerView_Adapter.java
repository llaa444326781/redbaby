package com.bwie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.R;
import com.bwie.bean.RedBaby;
import com.bwie.bean.RsBean;

import java.util.List;

/**
 * Created by Liuxiaoyu on 2016/11/11.
 */
public class LeftRecyclerView_Adapter extends RecyclerView.Adapter<LeftRecyclerView_ViewHolder> {
    Context context;
    List<RsBean> rsBeen;
    OnRecyclerItemClickListener onRecyclerItemClickListener;

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public LeftRecyclerView_Adapter(Context context, List<RsBean> rsBeen) {
        this.context = context;
        this.rsBeen = rsBeen;
    }

    @Override
    public LeftRecyclerView_ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LeftRecyclerView_ViewHolder holder = new LeftRecyclerView_ViewHolder(LayoutInflater.from(context).inflate(R.layout.recaycalerview_leftitem, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(LeftRecyclerView_ViewHolder holder, final int position) {
        holder.tv_typename.setText(rsBeen.get(position).getDirName());
        if (onRecyclerItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRecyclerItemClickListener.onItemClick(view, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rsBeen.size();
    }

    public interface OnRecyclerItemClickListener {
        /**
         * @param view     被点击的ittem
         * @param position 点击索引
         */
        void onItemClick(View view, int position);
    }
}
