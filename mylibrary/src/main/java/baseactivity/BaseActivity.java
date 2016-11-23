package baseactivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.ButterKnife;
import uttils.OkHttpUtils;
import okhttp3.Callback;

/**
 * Created by Liuxiaoyu on 2016/11/8.
 */
public abstract class BaseActivity extends AppCompatActivity implements MyInterface {
    /**
     * 是否沉浸状态栏
     **/
    private boolean isSetStatusBar = true;
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getLayout() != 0) {
            int layout = getLayout();
            view = View.inflate(this, layout, null);
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(view);
            if (isSetStatusBar) {
                steepStatusBar();
            }
            //注入view
            ButterKnife.bind(this);
            initUI();
            getData();
            loadData();

        } else {

        }


    }

    /**
     * get异步请求网络数据
     *
     * @param path
     * @param callback
     */
    public void getAsyn(String path, int tag, Callback callback) {
        OkHttpUtils.get(path, callback);
    }

    /**
     * 沉浸状态栏
     */
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏

            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    /**
     * [是否设置沉浸状态栏]
     *
     * @param isSetStatusBar
     */

    public void setSteepStatusBar(boolean isSetStatusBar) {
        this.isSetStatusBar = isSetStatusBar;
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 简化Toast
     */
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 绑定控件
     *
     * @param resId
     */
    public <T extends View> T findView(int resId) {
        return (T) findViewById(resId);
    }

    /**
     * 添加fragment
     *
     * @param layoutId 原来的布局
     * @param fragment
     */

    public void addFragment(int layoutId, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = manager.beginTransaction();
        beginTransaction.add(layoutId, fragment);
        beginTransaction.commit();
    }
//public <T extends  View>

    /**
     * 移除fragment
     *
     * @param fragment
     */
    public void removeFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = manager.beginTransaction();
        beginTransaction.remove(fragment);
        beginTransaction.commit();
    }

    /**
     * 替换的 方法
     *
     * @param layoutId
     * @param fragment
     */
    public void replaceFragment(int layoutId, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction beginTransaction = manager.beginTransaction();
        beginTransaction.replace(layoutId, fragment);
        beginTransaction.commit();
    }

    public FragmentManager getManger() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        return fragmentManager;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
        view = null;
    }
}
