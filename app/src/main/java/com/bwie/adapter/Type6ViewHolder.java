package com.bwie.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.R;
import com.bwie.bean.Bean;

/**
 * Created by Liuxiaoyu on 2016/11/15.
 */
public class Type6ViewHolder extends BaseViewHolder<Bean.DataBean> {
    RecyclerView mRecyclerView;

    public Type6ViewHolder(View itemView) {
        super(itemView);
        mRecyclerView= (RecyclerView) itemView.findViewById(R.id.homeitme6_rv);
    }

    @Override
    public void setData(final Context context, final Bean.DataBean dataBean) {

        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(context);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(new RecyclerView.Adapter<Type6_itmeViewHolder>() {
            @Override
            public Type6_itmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new Type6_itmeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item6_item,parent,false));
            }

            @Override
            public void onBindViewHolder(Type6_itmeViewHolder holder, int position) {
                holder.setData(context,dataBean.tag.get(position));
            }

            @Override
            public int getItemCount() {
                return dataBean.tag.size();
            }
        });
    }
    class  Type6_itmeViewHolder extends BaseViewHolder<Bean.DataBean.TagBean>{
        ImageView itmeImg;
        public Type6_itmeViewHolder(View itemView) {
            super(itemView);
            itmeImg=(ImageView)itemView.findViewById(R.id.type6_img);
        }
        @Override
        public void setData(Context context, Bean.DataBean.TagBean tagBean) {
            Glide.with(context)
                    .load("http://image1.suning.cn"+tagBean.picUrl)
                    .into(itmeImg);

        }
    }
}
