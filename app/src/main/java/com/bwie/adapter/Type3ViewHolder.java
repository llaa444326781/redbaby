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
public class Type3ViewHolder extends BaseViewHolder<Bean.DataBean> {
    ImageView mImageView;
    ImageView img1;
    ImageView img2;
    RecyclerView mRecyclerView;
    public Type3ViewHolder(View itemView) {
        super(itemView);
        mImageView= (ImageView) itemView.findViewById(R.id.homeitme3_title);
        img1= (ImageView) itemView.findViewById(R.id.item3_img1);
        img2= (ImageView) itemView.findViewById(R.id.item3_img2);
        mRecyclerView= (RecyclerView) itemView.findViewById(R.id.homeitme3_rv);
    }

    @Override
    public void setData(final Context context, final Bean.DataBean dataBean) {
        Bean.DataBean.TagBean tag = dataBean.tag.remove(0);
        Glide.with(context)
                .load("http://image1.suning.cn"+tag.picUrl)
                .into(mImageView);

        Bean.DataBean.TagBean tag1 = dataBean.tag.remove(0);
        Glide.with(context)
                .load("http://image1.suning.cn"+tag1.picUrl)
                .into(img1);

        Bean.DataBean.TagBean tag2 = dataBean.tag.remove(0);
        Glide.with(context)
                .load("http://image1.suning.cn"+tag2.picUrl)
                .into(img2);

        GridLayoutManager mGridLayoutManager=new GridLayoutManager(context,4);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(new RecyclerView.Adapter<Type3_itmeViewHolder>() {
            @Override
            public Type3_itmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new Type3_itmeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item3_item,parent,false));
            }

            @Override
            public void onBindViewHolder(Type3_itmeViewHolder holder, int position) {
                holder.setData(context,dataBean.tag.get(position));
            }

            @Override
            public int getItemCount() {
                return dataBean.tag.size();
            }
        });
    }
    class  Type3_itmeViewHolder extends BaseViewHolder<Bean.DataBean.TagBean>{
        ImageView itmeImg;
        public Type3_itmeViewHolder(View itemView) {
            super(itemView);
            itmeImg=(ImageView)itemView.findViewById(R.id.type3_img);
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
