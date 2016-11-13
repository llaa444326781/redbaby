package baseactivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import uttils.OkHttpUtils;
import okhttp3.Callback;

/**
 * Created by Liuxiaoyu on 2016/11/11.
 */
public abstract class BaseFragment extends Fragment implements MyInterface{

    public Activity mActivity;//这个activity就是MainActivity
    private View view;

    /**
     * Fragment创建
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();// 获取当前fragment所依赖的activity
    }

    /**
     * 初始化fragment的布局
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getLayout() != 0) {
            view = View.inflate(mActivity, getLayout(), null);
            initUI();
            getData();
            loadData();
        } else {
            Log.e("Activity", "onCreate: 返回0");
        }

        return view;
    }

    /**
     * fragment所依赖的activity的onCreate方法执行结束
     */

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * 返回根布局View
     *
     * @return
     */
    public View getmRootView() {
        return view;
    }

    /**
     * 找控件
     */

    public <T extends View> T findview(int viewId) {
        return (T) view.findViewById(viewId);
    }

    /**
     * 吐司
     *
     * @param text
     */
    public void ToastShow(String text) {
        Toast.makeText(getContext(), text + "", Toast.LENGTH_SHORT).show();
    }

    /**
     * 异步网络请求
     */
    public void getNetAsyn(String url, Callback callback, int tag) {
        OkHttpUtils.get(url, callback);
    }

    /**
     * 同步get请求
     *
     * @param url
     * @param tag
     */
    public void getNetSyn(String url, int tag) {
        OkHttpUtils.get(url);
    }


    /**
     * 销毁的时候制为空
     */
    @Override
    public void onDestroy() {
        view = null;
        super.onDestroy();
    }
}

