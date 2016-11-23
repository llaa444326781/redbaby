package com.bwie.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.R;
import com.bwie.bean.Bean;

/**
 * Created by Liuxiaoyu on 2016/11/16.
 */
public class Type2ViewHolder extends BaseViewHolder<Bean.DataBean> {
    ImageView mImageView;
    RecyclerView mRecyclerView;
    public Type2ViewHolder(View itemView) {
        super(itemView);
        mImageView= (ImageView) itemView.findViewById(R.id.homeitme2_title);
        mRecyclerView= (RecyclerView) itemView.findViewById(R.id.homeitme2_rv);
    }

    @Override
    public void setData(final Context context, final Bean.DataBean dataBean) {
        Bean.DataBean.TagBean tag = dataBean.tag.remove(0);
        String url="http://image1.suning.cn"+tag.picUrl;
        Glide.with(context)
                .load(url)
                .into(mImageView);


        GridLayoutManager mGridLayoutManager=new GridLayoutManager(context,2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(new RecyclerView.Adapter<Type2_itmeViewHolder>() {
            @Override
            public Type2_itmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new Type2_itmeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item2_item,parent,false));
            }

            @Override
            public void onBindViewHolder(Type2_itmeViewHolder holder, int position) {
                holder.setData(context,dataBean.tag.get(position));
            }

            @Override
            public int getItemCount() {
                return dataBean.tag.size();
            }
        });
    }
    class  Type2_itmeViewHolder extends BaseViewHolder<Bean.DataBean.TagBean>{
        ImageView itmeImg;
        public Type2_itmeViewHolder(View itemView) {
            super(itemView);
            itmeImg=(ImageView)itemView.findViewById(R.id.type2_img);
        }
        @Override
        public void setData(Context context, Bean.DataBean.TagBean tagBean) {
            String url = "http://image1.suning.cn"+tagBean.picUrl;
            Glide.with(context)
                    .load(url)
                    .into(itmeImg);

        }
    }
}
