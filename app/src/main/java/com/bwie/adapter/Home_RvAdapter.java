package com.bwie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bwie.R;
import com.bwie.bean.Bean;

import java.util.List;

/**
 * Created by Liuxiaoyu on 2016/11/15.
 */
public class Home_RvAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private List<Bean.DataBean> list;
    private LayoutInflater mLayoutInflater;

    private static final int TYPE0 = 0;
    private static final int TYPE1 = 1;
    private static final int TYPE2 = 2;
    private static final int TYPE3 = 3;
    private static final int TYPE4 = 4;
    private static final int TYPE5 = 5;
    private static final int TYPE6 = 6;
    private static final int TYPE7 = 7;
    private static final int TYPE8 = 8;
    private static final int TYPE9 = 9;
    private static final int TYPE10 = 10;
    private static final int TYPE11 = 11;
    private static final int TYPE12 = 12;
    private static final int TYPE13 = 13;
    private static final int TYPE14 = 14;
    private static final int TYPE15 = 15;
    private static final int TYPE16 = 16;
    private static final int TYPE17 = 17;
    private static final int TYPE18 = 18;
    public Home_RvAdapter(Context mContext, List<Bean.DataBean> list) {
        this.mContext = mContext;
        this.list = list;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        return getType( position) ;
    }

    public int getType(int position) {
        switch (position) {
            case 0:
                return TYPE0;
            case 1:
                return TYPE1;
            case 2:
                return TYPE2;
            case 3:
                return TYPE3;
            case 4:
                return TYPE4;
            case 5:
                return TYPE5;
            case 6:
                return TYPE6;
            case 7:
                return TYPE7;
            case 8:
                return TYPE8;
            case 9:
                return TYPE9;
            case 10:
                return TYPE10;
            case 11:
                return TYPE11;
            case 12:
                return TYPE12;
            case 13:
                return TYPE13;
            case 14:
                return TYPE14;
            case 15:
                return TYPE15;
            case 16:
                return TYPE16;
            case 17:
                return TYPE17;
            case 18:
                return TYPE18;
        }
        return TYPE0;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE0:
                return new Type1ViewHolder(mLayoutInflater.inflate(R.layout.home_itme1, parent, false));
            case TYPE1:
                return new Type2ViewHolder(mLayoutInflater.inflate(R.layout.home_itme2, parent, false));
            case TYPE2:
                return new Type3ViewHolder(mLayoutInflater.inflate(R.layout.home_itme3, parent, false));
            case TYPE3:
                return new Type4ViewHolder(mLayoutInflater.inflate(R.layout.home_itme4, parent, false));
            case TYPE4:
                return new Type5ViewHolder(mLayoutInflater.inflate(R.layout.home_itme5, parent, false));
            case TYPE5:
                return new Type6ViewHolder(mLayoutInflater.inflate(R.layout.home_itme6, parent, false));
            case TYPE6:
                return new Type5ViewHolder(mLayoutInflater.inflate(R.layout.home_itme5, parent, false));
            case TYPE7:
                return new Type6ViewHolder(mLayoutInflater.inflate(R.layout.home_itme6, parent, false));
            case TYPE8:
                return new Type5ViewHolder(mLayoutInflater.inflate(R.layout.home_itme5, parent, false));
            case TYPE9:
                return new Type6ViewHolder(mLayoutInflater.inflate(R.layout.home_itme6, parent, false));
            case TYPE10:
                return new Type5ViewHolder(mLayoutInflater.inflate(R.layout.home_itme5, parent, false));
            case TYPE11:
                return new Type6ViewHolder(mLayoutInflater.inflate(R.layout.home_itme6, parent, false));
            case TYPE12:
                return new Type4ViewHolder(mLayoutInflater.inflate(R.layout.home_itme4, parent, false));
            case TYPE13:
                return new Type5ViewHolder(mLayoutInflater.inflate(R.layout.home_itme5, parent, false));
            case TYPE14:
                return new Type5ViewHolder(mLayoutInflater.inflate(R.layout.home_itme5, parent, false));
            case TYPE15:
                return new Type5ViewHolder(mLayoutInflater.inflate(R.layout.home_itme5, parent, false));
            case TYPE16:
                return new Type5ViewHolder(mLayoutInflater.inflate(R.layout.home_itme5, parent, false));
            case TYPE17:
                return new Type5ViewHolder(mLayoutInflater.inflate(R.layout.home_itme5, parent, false));
            case TYPE18:
                return new Type4ViewHolder(mLayoutInflater.inflate(R.layout.home_itme4, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.setData(mContext, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
