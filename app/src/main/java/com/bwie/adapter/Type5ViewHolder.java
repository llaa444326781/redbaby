package com.bwie.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bwie.R;
import com.bwie.bean.Bean;

/**
 * Created by Liuxiaoyu on 2016/11/16.
 */
public class Type5ViewHolder extends BaseViewHolder<Bean.DataBean> {
    ImageView mImageView;

    public Type5ViewHolder(View itemView) {
        super(itemView);
        mImageView= (ImageView) itemView.findViewById(R.id.homeitme5_title);

    }

    @Override
    public void setData(final Context context, final Bean.DataBean dataBean) {
        //Bean.DataBean.TagBean tag = dataBean.tag.remove(0);
        Glide.with(context)
                .load("http://image1.suning.cn"+dataBean.tag.get(0).picUrl)
                .into(mImageView);

    }
}
