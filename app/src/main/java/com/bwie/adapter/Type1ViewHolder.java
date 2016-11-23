package com.bwie.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.R;
import com.bwie.bean.Bean;

/**
 * Created by Liuxiaoyu on 2016/11/15.
 */
public class Type1ViewHolder extends BaseViewHolder<Bean.DataBean> {
    ImageView mImageView;
    RecyclerView mRecyclerView;

    public Type1ViewHolder(View itemView) {
        super(itemView);
        mImageView= (ImageView) itemView.findViewById(R.id.homeitme1_title);
        mRecyclerView= (RecyclerView) itemView.findViewById(R.id.homeitme1_rv);
    }

    @Override
    public void setData(final Context context, final Bean.DataBean dataBean) {
       // System.out.println("dataBean:"+dataBean.tag.size());
        Bean.DataBean.TagBean tag = dataBean.tag.remove(0);
        Glide.with(context)
                .load("http://image1.suning.cn"+tag.picUrl)
                .into(mImageView);

        LinearLayoutManager mLinearLayoutManager=new LinearLayoutManager(context);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(new RecyclerView.Adapter<Type1_itmeViewHolder>() {
            @Override
            public Type1_itmeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new Type1_itmeViewHolder(LayoutInflater.from(context).inflate(R.layout.home_item1_item,parent,false));
            }

            @Override
            public void onBindViewHolder(Type1_itmeViewHolder holder, int position) {
                holder.setData(context,dataBean.tag.get(position));
            }

            @Override
            public int getItemCount() {
                return dataBean.tag.size();
            }
        });
    }
    class  Type1_itmeViewHolder extends BaseViewHolder<Bean.DataBean.TagBean>{
        ImageView itmeImg;
        public Type1_itmeViewHolder(View itemView) {
            super(itemView);
            itmeImg=(ImageView)itemView.findViewById(R.id.type1_img);
        }
        @Override
        public void setData(Context context, Bean.DataBean.TagBean tagBean) {
            Glide.with(context)
                    .load("http://image1.suning.cn"+tagBean.picUrl)
                    .into(itmeImg);

        }
    }
}
