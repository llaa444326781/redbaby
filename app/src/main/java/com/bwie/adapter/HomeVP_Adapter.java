package com.bwie.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Liuxiaoyu on 2016/11/14.
 */
public class HomeVP_Adapter extends PagerAdapter {
    private List<ImageView> viewList;
    private Context context;
    private Handler handler;

    public HomeVP_Adapter(List viewList, Context context, Handler handler) {
        this.viewList = viewList;
        this.context = context;
        this.handler = handler;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 通过控件集合获取imageView 添加到容器中，并作为key 返回
        ImageView imageView = viewList.get(position%viewList.size());
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // 当按下的时候，停止轮播
                    case MotionEvent.ACTION_DOWN:
                        // 移除所有的回调和消息
                        handler.removeCallbacksAndMessages(null);
                        break;
                    // 抬起
                    case MotionEvent.ACTION_UP:
                        handler.sendEmptyMessageDelayed(0, 2000);
                        break;
                    // 取消的时候，继续开始录播
                    case MotionEvent.ACTION_CANCEL:
                        handler.sendEmptyMessageDelayed(0, 2000);
                        break;
                    default:
                        break;
                }
                //触摸事件不被消费，true 消费该触摸事件
                return true;
            }
        });
        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
