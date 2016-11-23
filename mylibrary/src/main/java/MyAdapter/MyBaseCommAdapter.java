package MyAdapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by FanGuangjie on 2016/11/8.
 */
public abstract class MyBaseCommAdapter<T> extends BaseAdapter {
    private List<T> mDatas;

    public MyBaseCommAdapter(List<T> datas) {
        mDatas = datas;
        Log.d("msg", datas.size() + "小集合的长度");
    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder
                .newsInstance(convertView, parent.getContext(), getLayoutId());

        setUI(holder, position, parent.getContext());

        return holder.getConverView();
    }

    protected abstract void setUI(ViewHolder holder, int position, Context context);

    protected abstract int getLayoutId();

    /*
    *
    *使用的时候只需要继承BaseCommAdapter<T>，泛型中传入数据类型，重写setUI、getLayoutId俩个方法就可,下面是完整代码

public class UserAdapter extends MyBaseCommAdapter<User>
{
    public UserAdapter(List<User> datas)
    {
        super(datas);
    }

    @Override
    protected void setUI(ViewHolder holder, int position, Context context)
    {
        User item = getItem(position);

        TextView tv_name = holder.getItemView(R.id.tv_name_use_item);
        tv_name.setText(item.name);

        TextView tv_sex = holder.getItemView(R.id.tv_sex_use_item);
        tv_sex.setText(item.sex);

        ImageView iv_head = holder.getItemView(R.id.iv_head_use_item);
        iv_head.setImageResource(item.headId);
    }

    @Override
    protected int getLayoutId()
    {
        return R.layout.item_user_list;
    }
}

//参考网址
http://blog.csdn.net/q649381130/article/details/51781921
    *
    *
    * */


}
